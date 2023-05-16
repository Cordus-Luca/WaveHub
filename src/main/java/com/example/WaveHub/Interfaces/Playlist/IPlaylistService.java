package com.example.WaveHub.Interfaces.Playlist;

import com.example.WaveHub.Models.Playlist;
import com.example.WaveHub.Models.Song;

import java.util.List;
import java.util.Set;

public interface IPlaylistService {
    List<Playlist> getPlaylists();
    void addNewPlaylist(Playlist playlist);
    void deletePlaylist(Long playlistId);
    void updatePlaylist(Long playlistId, String name, Set<Song> songsId);
}
