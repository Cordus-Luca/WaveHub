package com.example.WaveHub.DataBaseLayer.Entities;

import com.example.WaveHub.Interfaces.IPlaylistRepoJPA;
import com.example.WaveHub.Interfaces.IPlaylistRepository;
import com.example.WaveHub.Models.Playlist;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public abstract class PlaylistRepository{

    private final IPlaylistRepoJPA playlistRepoJPA;

    public PlaylistRepository(IPlaylistRepoJPA playlistRepoJPA) {this.playlistRepoJPA = playlistRepoJPA;}

    public List<Playlist> findPlaylistByName(String name) {
        List<Playlist> playlists = playlistRepoJPA.findAll();
        return playlists;
    }

}
