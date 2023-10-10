package com.example.WaveHub.ServiceLayer;

import com.example.WaveHub.Interfaces.Playlist.IPlaylistRepository;
import com.example.WaveHub.Interfaces.Playlist.IPlaylistService;
import com.example.WaveHub.Models.Playlist;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@Service
public class PlaylistService implements IPlaylistService {
    private final IPlaylistRepository playlistRepository;

    public List<Playlist> getPlaylists() {
        return playlistRepository.getAllPlaylists();
    }

    public void addNewPlaylist(Playlist playlist, MultipartFile imgFile) throws IOException {
        String imgLink = String.valueOf(UUID.randomUUID());
        playlist.setImgLink(imgLink);

        playlistRepository.addNewPlaylist(playlist, imgFile);
    }

    public void deletePlaylist(Long playlistId) {

        playlistRepository.deletePlaylistById(playlistId);
    }

    @Transactional
    public void updatePlaylist(Playlist playlist) {
        playlistRepository.updatePlaylist(playlist);

    }

    @Override
    public Playlist getPlaylistById(Long playlistId) {
        return playlistRepository.getPlaylistById(playlistId);
    }


}
