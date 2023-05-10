package com.example.WaveHub.DataBaseLayer.Entities;

import com.example.WaveHub.Interfaces.ISongRepoJPA;
import com.example.WaveHub.Interfaces.ISongRepository;
import com.example.WaveHub.Models.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public abstract class SongRepository implements ISongRepository {

    private final ISongRepoJPA songRepoJPA;

    public SongRepository(ISongRepoJPA songRepoJPA) {
        this.songRepoJPA = songRepoJPA;
    }

    @Override
    public List<Song> findAll() {

        List<Song> songs = songRepoJPA.findAll();

        return songs;
    }
}
