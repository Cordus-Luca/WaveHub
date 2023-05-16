package com.example.WaveHub.Interfaces.Playlist;

import com.example.WaveHub.Models.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPlaylistRepository{

    List<Playlist> findPlaylistByName(String name);
    Playlist getPlaylistById(Long playlistId);
    List<Playlist> getAllPlaylists();
    void addNewPlaylist(Playlist playlist);
    void updatePlaylist(Playlist playlist);
    void deletePlaylistById(Long playlistId);
}
