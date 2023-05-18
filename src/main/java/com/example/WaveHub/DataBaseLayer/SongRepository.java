package com.example.WaveHub.DataBaseLayer;

import com.example.WaveHub.DataBaseLayer.Entities.PlaylistEntity;
import com.example.WaveHub.DataBaseLayer.Entities.SongEntity;
import com.example.WaveHub.DataBaseLayer.EntityModelConverters.SongEntityConverter;
import com.example.WaveHub.Interfaces.Playlist.IPlaylistRepoJPA;
import com.example.WaveHub.Interfaces.Song.ISongRepoJPA;
import com.example.WaveHub.Interfaces.Song.ISongRepository;
import com.example.WaveHub.Models.Song;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SongRepository implements ISongRepository {

    private final ISongRepoJPA songRepoJPA;
    private final IPlaylistRepoJPA playlistRepoJPA;

    public SongRepository(ISongRepoJPA songRepoJPA, IPlaylistRepoJPA playlistRepoJPA) {
        this.songRepoJPA = songRepoJPA;
        this.playlistRepoJPA = playlistRepoJPA;
    }

    @Override
    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        List<SongEntity> songDataEntities = songRepoJPA.findAll();

        for (SongEntity songEnt:
                songDataEntities) {
            songs.add(new SongEntityConverter().convertFromEntityToModel(songEnt) );
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

    @Override
    public void addNewSong(Song song) {
        SongEntity songEntity = new SongEntityConverter().convertFromModelToEntity(song);

        songRepoJPA.save(songEntity);
    }

    @Override
    public void deleteSong(Long songId) {
        boolean exists = songRepoJPA.existsById(songId);

        if (!exists) {
            throw new IllegalStateException("song with id " + songId + " does not exist");
        }

        songRepoJPA.deleteById(songId);
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
    public void updateSong(Song song) {
        Optional<SongEntity> optionalSongEntity = songRepoJPA.findById(song.getId());

        if(optionalSongEntity.isEmpty()) {
            throw new IllegalStateException("Song with id: " + song.getId() + " does not exist");
        }
        SongEntity songEntity = optionalSongEntity.get();
        songEntity.setName(songEntity.getName());
        songEntity.setAlbum(songEntity.getAlbum());
        songEntity.setArtist(songEntity.getArtist());
        songRepoJPA.save(songEntity);
    }
}
