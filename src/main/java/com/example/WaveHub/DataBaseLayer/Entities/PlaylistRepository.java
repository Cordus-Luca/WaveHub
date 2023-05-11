package com.example.WaveHub.DataBaseLayer.Entities;

import com.example.WaveHub.Interfaces.Playlist.IPlaylistRepoJPA;
import com.example.WaveHub.Interfaces.Playlist.IPlaylistRepository;
import com.example.WaveHub.Models.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class PlaylistRepository implements IPlaylistRepository {

    private final IPlaylistRepoJPA playlistRepoJPA;

    @Autowired
    public PlaylistRepository(IPlaylistRepoJPA playlistRepoJPA) {this.playlistRepoJPA = playlistRepoJPA;}

    @Override
    public List<Playlist> findPlaylistByName(String name) {
        return null;
    }

    @Override
    public Playlist getPlaylistById(Long playlistId) {
        Playlist playlist = playlistRepoJPA.findById(playlistId)
                .orElseThrow(() -> new IllegalStateException(
                        "playlist with id " + playlistId + " does not exist"
                ));
        return playlist;
    }

    @Override
    public void deletePlaylistById(Long playlistId) {
        boolean exists = playlistRepoJPA.existsById(playlistId);

        if (!exists) {
            throw new IllegalStateException("playlist with id " + playlistId + " does not exist");
        }

        playlistRepoJPA.deleteById(playlistId);
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        List<Playlist> playlists= playlistRepoJPA.findAll();

        return playlists;
    }

    @Override
    public void createPlaylist(Playlist playlist) {
        playlistRepoJPA.save(playlist);
    }

    @Override
    public void updatePlaylist(Playlist playlist) {
        Optional<Playlist> optionalPlaylist = playlistRepoJPA.findById((playlist.getId()));

        if(optionalPlaylist.isEmpty()){
            throw new IllegalStateException("playlist with id " + playlist.getId() + " does not exist");
        }

        playlist.setName(optionalPlaylist.get().getName());
        playlist.setSongsId(optionalPlaylist.get().getSongsId());
        playlistRepoJPA.save(playlist);
    }
}
