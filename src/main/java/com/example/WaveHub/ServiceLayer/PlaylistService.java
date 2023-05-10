package com.example.WaveHub.ServiceLayer;

import com.example.WaveHub.Interfaces.IPlaylistRepoJPA;
import com.example.WaveHub.Interfaces.IPlaylistRepository;
import com.example.WaveHub.Interfaces.IPlaylistService;
import com.example.WaveHub.Models.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService implements IPlaylistService {
    private final IPlaylistRepoJPA playlistRepository;

    @Autowired
    public PlaylistService(IPlaylistRepoJPA IPlaylistRepository) {this.playlistRepository = IPlaylistRepository;}

    public List<Playlist> getPlaylists() {return playlistRepository.findAll();}

    public void addNewPlaylist(Playlist playlist) {

        playlistRepository.save(playlist);
    }

    public void deletePlaylist(Long playlistId) {
        boolean exists = playlistRepository.existsById(playlistId);

        if (!exists) {
            throw new IllegalStateException("playlist with id " + playlistId + " does not exist");
        }

        playlistRepository.deleteById(playlistId);
    }
}
