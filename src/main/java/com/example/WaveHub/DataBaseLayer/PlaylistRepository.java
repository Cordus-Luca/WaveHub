package com.example.WaveHub.DataBaseLayer;

import com.example.WaveHub.DataBaseLayer.Entities.PlaylistEntity;
import com.example.WaveHub.DataBaseLayer.EntityModelConverters.PlaylistEntityConverter;
import com.example.WaveHub.Interfaces.Playlist.IPlaylistRepoJPA;
import com.example.WaveHub.Interfaces.Playlist.IPlaylistRepository;
import com.example.WaveHub.Models.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

        Optional<PlaylistEntity> playlistDataEntity = playlistRepoJPA.findById(playlistId);
        if(playlistDataEntity.isEmpty())
        {
            throw new IllegalStateException("Playlist with id: " + playlistId + " does not exist");
        }

        return new PlaylistEntityConverter().convertFromEntityToModel(playlistDataEntity.get());
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
        List<Playlist> playlists = new ArrayList<>();
        List<PlaylistEntity> playlistEntities = playlistRepoJPA.findAll();

        for (PlaylistEntity playlistEnt:
                playlistEntities) {
            playlists.add(new PlaylistEntityConverter().convertFromEntityToModel(playlistEnt));
        }

        return playlists;
    }

    @Override
    public void addNewPlaylist(Playlist playlist) {
        PlaylistEntity playlistEntity = new PlaylistEntityConverter().convertFromModelToEntity(playlist);
        playlistRepoJPA.save(playlistEntity);
    }

    // TODO: UPDATE PLAYLIST METHOD
    @Override
    public void updatePlaylist(Playlist playlist) {
//        Optional<Playlist> optionalPlaylist = playlistRepoJPA.findById((playlist.getId()));
//
//        if(optionalPlaylist.isEmpty()){
//            throw new IllegalStateException("playlist with id " + playlist.getId() + " does not exist");
//        }
//
//        playlist.setName(optionalPlaylist.get().getName());
//        playlist.setHasSong(optionalPlaylist.get().getHasSong());
//        playlistRepoJPA.save(playlist);
    }

}
