package com.example.WaveHub.DataBaseLayer;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.WaveHub.DataBaseLayer.Entities.PlaylistEntity;
import com.example.WaveHub.DataBaseLayer.Entities.SongEntity;
import com.example.WaveHub.DataBaseLayer.EntityModelConverters.SongEntityConverter;
import com.example.WaveHub.Interfaces.Playlist.IPlaylistRepoJPA;
import com.example.WaveHub.Interfaces.SongPlaylist.ISongPlaylistRepoJPA;
import com.example.WaveHub.Interfaces.Song.ISongRepoJPA;
import com.example.WaveHub.Interfaces.Song.ISongRepository;
import com.example.WaveHub.Models.CompositeKey;
import com.example.WaveHub.Models.Song;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Repository
public class SongRepository implements ISongRepository {

    private final ISongRepoJPA songRepoJPA;
    private final IPlaylistRepoJPA playlistRepoJPA;
    private final ISongPlaylistRepoJPA songPlaylistRepoJPA;
    private final AmazonS3 fileSpace;

    public SongRepository(ISongRepoJPA songRepoJPA, IPlaylistRepoJPA playlistRepoJPA, ISongPlaylistRepoJPA songPlaylistRepoJPA) {
        this.songRepoJPA = songRepoJPA;
        this.playlistRepoJPA = playlistRepoJPA;
        this.songPlaylistRepoJPA = songPlaylistRepoJPA;

        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials("DO00JGRDKCFTEPCEH66J","Bc6JRflEP5iQMaKGKy3pO3BfCuRNyoNFU7LeszQqrqc")
        );

        fileSpace = AmazonS3ClientBuilder
                .standard()
                .withCredentials(awsCredentialsProvider)
                        .withEndpointConfiguration(
                                new AwsClientBuilder.EndpointConfiguration("fra1.digitaloceanspaces.com","fra1")
                        )
                .build();
    }

    @Override
    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        List<SongEntity> songDataEntities = songRepoJPA.findAll();


        for (SongEntity songEnt:
                songDataEntities) {
            Integer isDeleted = songEnt.getIsDeleted();
            if(isDeleted != null && isDeleted.intValue()==0){
            songs.add(new SongEntityConverter().convertFromEntityToModel(songEnt) );
            }
        }

        return songs;
    }

    @Override
    public Song getSongById(Long songId) {
        Optional<SongEntity> songDataEntity = songRepoJPA.findById(songId);
        if(songDataEntity.isPresent()) {
            return new SongEntityConverter().convertFromEntityToModel(songDataEntity.get());
        }
        else{
            throw new IllegalStateException("Song with id: " + songId + " does not exist");
        }
    }


    @Modifying
    @Query("DELETE FROM song_in_playlist ps WHERE ps.song_id = :songId")
    public void deleteSongInPlaylist(Long songId) {
    }

    @Transactional
    @Override
    public void deleteSong(Long songId) {
        boolean exists = songRepoJPA.existsById(songId);

        if (!exists) {
            throw new IllegalStateException("song with id " + songId + " does not exist");
        }
        SongEntity songDataEntity = songRepoJPA.findById(songId).get();
        songDataEntity.setIsDeleted(1);
    }

    @Override
    public void addSongToPlaylist(Long songId, Long playlistId) {
        Optional<SongEntity> optionalSongEntity = songRepoJPA.findById(songId);
        // Check if song exists
        if(optionalSongEntity.isEmpty()) {
            throw new IllegalStateException("Song with id: " + songId + " does not exist");
        }

        Optional<PlaylistEntity> optionalPlaylistEntity = playlistRepoJPA.findById(playlistId);
        // Check if playlist exists
        if(optionalSongEntity.isEmpty()) {
            throw new IllegalStateException("Song with id: " + playlistId + " does not exist");
        }

        // Create playlist entity to save for later
        PlaylistEntity playlistEntity = optionalPlaylistEntity.get();

        // Getting many-to-many collection
        Collection<SongEntity> songs = playlistEntity.getPlaylistSongs();

        if(songs.contains(optionalSongEntity.get())) {
            throw new IllegalStateException("Song already in playlist");
        }

        songs.add(optionalSongEntity.get());
        playlistEntity.setPlaylistSongs(songs);
        playlistRepoJPA.save(playlistEntity);
    }

    @Override
    public List<Song> getPlaylistSongs(Long playlistId) {
        Optional<PlaylistEntity> playlistEntity = playlistRepoJPA.findById(playlistId);

        Collection<SongEntity> playlistSongs = playlistEntity.get().getPlaylistSongs();
        List<Song> songs = new ArrayList<>();
        for (SongEntity songEnt :
                playlistSongs) {
            Integer isDeleted = songEnt.getIsDeleted();
            if(isDeleted != null && isDeleted.intValue()==0){
                songs.add(new SongEntityConverter().convertFromEntityToModel(songEnt) );
            }
        }
        return songs;
    }

    @Override
    public void updateSong(Song song) {
        Optional<SongEntity> optionalSongEntity = songRepoJPA.findById(song.getId());

        if(optionalSongEntity.isEmpty()) {
            throw new IllegalStateException("Song with id: " + song.getId() + " does not exist");
        }
        SongEntity songEntity = optionalSongEntity.get();
        songEntity.setName(songEntity.getName());
        songEntity.setAlbum(songEntity.getAlbum());
        songEntity.setArtist(songEntity.getArtist());
        songEntity.setImgLink(songEntity.getImgLink());
        songEntity.setMp3Link(songEntity.getMp3Link());
        songRepoJPA.save(songEntity);
    }

    @Override
    public void addNewSong(Song song, MultipartFile songFile, MultipartFile imgFile) throws IOException {
        SongEntity songEntity = new SongEntityConverter().convertFromModelToEntity(song);
        System.out.println("Test Add song");
        songEntity.setIsDeleted(0);
        songRepoJPA.save(songEntity);

        uploadSong(songFile, songEntity.getMp3Link(), imgFile, songEntity.getImgLink());
    }
    private void uploadSong(MultipartFile file, String keyOfFile, MultipartFile imgFile, String keyOfImgFile) throws IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        fileSpace.putObject(new PutObjectRequest(
                "wavehub",
                keyOfFile,
                file.getInputStream(),
                objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead)
        );
        fileSpace.putObject(new PutObjectRequest(
                "wavehub",
                keyOfImgFile,
                imgFile.getInputStream(),
                objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead)
        );
    }
}
