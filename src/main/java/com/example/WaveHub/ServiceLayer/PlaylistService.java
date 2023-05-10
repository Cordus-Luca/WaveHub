package com.example.WaveHub.ServiceLayer;

import com.example.WaveHub.Interfaces.IPlaylistRepoJPA;
import com.example.WaveHub.Interfaces.IPlaylistRepository;
import com.example.WaveHub.Interfaces.IPlaylistService;
import com.example.WaveHub.Models.Playlist;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

//  TODO: MAKE EXCEPTION FOR "SONG DOES NOT EXIST"
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

    @Transactional
    public void updatePlaylist(Long playlistId, String name, List<Long> songsId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new IllegalStateException(
                        "playlist with id " + playlistId + " does not exist"
                ));

        if (name != null &&
            name.length() > 0 &&
            !Objects.equals(playlist.getName(), name)) {
            playlist.setName(name);
        }

        playlist.setSongsId(songsId);

    }
}
