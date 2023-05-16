package com.example.WaveHub.DataBaseLayer.EntityModelConverters;

import com.example.WaveHub.DataBaseLayer.Entities.PlaylistEntity;
import com.example.WaveHub.Models.Playlist;

public class PlaylistEntityConverter {

    public Playlist convertFromEntityToModel(PlaylistEntity playlistEntity) {
        return new Playlist(playlistEntity.getId(),
                playlistEntity.getName());
    }

    public PlaylistEntity convertFromModelToEntity(Playlist playlist) {
        return PlaylistEntity.builder()
                .name(playlist.getName())
                .build();
    }
}
