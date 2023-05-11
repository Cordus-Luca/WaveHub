package com.example.WaveHub.DataBaseLayer.Entities;

import com.example.WaveHub.Interfaces.Song.ISongRepoJPA;
import com.example.WaveHub.Interfaces.Song.ISongRepository;
import com.example.WaveHub.Models.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SongRepository implements ISongRepository {

    // TODO: MOVE METHODS FROM REPOJPA TO REPOSITORY
    private final ISongRepoJPA songRepoJPA;

    public SongRepository(ISongRepoJPA songRepoJPA) {
        this.songRepoJPA = songRepoJPA;
    }

    @Override
    public List<Song> getAllSongs() {
        List<Song> songs = songRepoJPA.findAll();
        return songs;
    }

    @Override
    public Optional<Song> getSongById(Long songId) {
        Optional<Song> song = songRepoJPA.findById(songId);
        return song;
    }

}
