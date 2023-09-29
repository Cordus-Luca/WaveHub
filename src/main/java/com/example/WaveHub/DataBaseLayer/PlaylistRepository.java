package com.example.WaveHub.DataBaseLayer;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.WaveHub.DataBaseLayer.Entities.PlaylistEntity;
import com.example.WaveHub.DataBaseLayer.Entities.SongEntity;
import com.example.WaveHub.DataBaseLayer.EntityModelConverters.PlaylistEntityConverter;
import com.example.WaveHub.Interfaces.Playlist.IPlaylistRepoJPA;
import com.example.WaveHub.Interfaces.Playlist.IPlaylistRepository;
import com.example.WaveHub.Models.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PlaylistRepository implements IPlaylistRepository {

    private final IPlaylistRepoJPA playlistRepoJPA;
    private final AmazonS3 fileSpace;


    @Autowired
    public PlaylistRepository(IPlaylistRepoJPA playlistRepoJPA) {

        this.playlistRepoJPA = playlistRepoJPA;

        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials("DO00JGRDKCFTEPCEH66J","Bc6JRflEP5iQMaKGKy3pO3BfCuRNyoNFU7LeszQqrqc")
        );

        fileSpace = AmazonS3ClientBuilder
                .standard()
                .withCredentials(awsCredentialsProvider)
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration("fra1.digitaloceanspaces.com","fra1")
                )
                .build();}

    @Override
    public List<Playlist> findPlaylistByName(String name) {
        return null;
    }

    @Override
    public Playlist getPlaylistById(Long playlistId) {

        Optional<PlaylistEntity> playlistDataEntity = playlistRepoJPA.findById(playlistId);

        if(playlistDataEntity.isEmpty())
        {
            throw new IllegalStateException("Playlist with id: " + playlistId + " does not exist");
        }

        return new PlaylistEntityConverter().convertFromEntityToModel(playlistDataEntity.get());
    }

    @Override
    public void deletePlaylistById(Long playlistId) {
        boolean exists = playlistRepoJPA.existsById(playlistId);

        if (!exists) {
            throw new IllegalStateException("playlist with id " + playlistId + " does not exist");
        }

        PlaylistEntity playlistEntity = playlistRepoJPA.findById(playlistId).get();
        playlistEntity.setIsDeleted(1);
        playlistRepoJPA.deleteById(playlistId);
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        List<Playlist> playlists = new ArrayList<>();
        List<PlaylistEntity> playlistEntities = playlistRepoJPA.findAll();

        for (PlaylistEntity playlistEnt:
                playlistEntities) {
            Integer isDeleted = playlistEnt.getIsDeleted();
            if(isDeleted != null && isDeleted.intValue()==0){
            playlists.add(new PlaylistEntityConverter().convertFromEntityToModel(playlistEnt));
            }
        }

        return playlists;
    }

    @Override
    public void addNewPlaylist(Playlist playlist, MultipartFile imgFile) throws IOException {
        PlaylistEntity playlistEntity = new PlaylistEntityConverter().convertFromModelToEntity(playlist);
        playlistEntity.setIsDeleted(0);
        playlistRepoJPA.save(playlistEntity);

        uploadPlaylist(imgFile, playlistEntity.getImgLink());
    }

    private void uploadPlaylist(MultipartFile imgFile, String keyOfFile) throws IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(imgFile.getContentType());
        fileSpace.putObject(new PutObjectRequest(
                "wavehub",
                keyOfFile,
                imgFile.getInputStream(),
                objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead)
        );
    }

    @Override
    public void updatePlaylist(Playlist playlist) {
        Optional<PlaylistEntity> optionalPlaylist = playlistRepoJPA.findById((playlist.getId()));

        if(optionalPlaylist.isEmpty()){
            throw new IllegalStateException("playlist with id " + playlist.getId() + " does not exist");
        }

        PlaylistEntity playlistEntity = optionalPlaylist.get();
        playlistEntity.setName(playlist.getName());
        playlistEntity.setDescription(playlist.getDescription());
        playlistRepoJPA.save(playlistEntity);
    }

}
