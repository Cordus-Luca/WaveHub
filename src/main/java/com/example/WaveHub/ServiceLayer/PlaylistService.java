package com.example.WaveHub.ServiceLayer;

import com.example.WaveHub.Interfaces.Playlist.IPlaylistRepository;
import com.example.WaveHub.Interfaces.Playlist.IPlaylistService;
import com.example.WaveHub.Models.Playlist;
import com.example.WaveHub.Models.Song;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@Service
public class PlaylistService implements IPlaylistService {
    private final IPlaylistRepository playlistRepository;

    public List<Playlist> getPlaylists() {
        return playlistRepository.getAllPlaylists();
    }

    public void addNewPlaylist(Playlist playlist) {
        playlistRepository.addNewPlaylist(playlist);
    }

    public void deletePlaylist(Long playlistId) {

        playlistRepository.deletePlaylistById(playlistId);
    }

    @Transactional
    public void updatePlaylist(Long playlistId, String name) {
        Playlist playlist = playlistRepository.getPlaylistById(playlistId);;
        if (name != null &&
            name.length() > 0 &&
            !Objects.equals(playlist.getName(), name)) {
            playlist.setName(name);
        }

    }

    @Override
    public Playlist getPlaylistById(Long playlistId) {
        return playlistRepository.getPlaylistById(playlistId);
    }
}
