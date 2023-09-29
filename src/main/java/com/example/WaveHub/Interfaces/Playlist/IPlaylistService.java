package com.example.WaveHub.Interfaces.Playlist;

import com.example.WaveHub.Models.Playlist;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IPlaylistService {
    List<Playlist> getPlaylists();
    void addNewPlaylist(Playlist playlist, MultipartFile imgFile) throws IOException;
    void deletePlaylist(Long playlistId);
    void updatePlaylist(Playlist playlist);

    Playlist getPlaylistById(Long playlistId);
}
