package com.example.WaveHub.Interfaces;

import com.example.WaveHub.Models.Playlist;

import java.util.List;

public interface IPlaylistService {
    List<Playlist> getPlaylists();
    void addNewPlaylist(Playlist playlist);
    void deletePlaylist(Long playlistId);
}
