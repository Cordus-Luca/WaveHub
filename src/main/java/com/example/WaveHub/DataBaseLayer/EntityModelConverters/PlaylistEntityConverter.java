package com.example.WaveHub.DataBaseLayer.EntityModelConverters;

import com.example.WaveHub.DataBaseLayer.Entities.PlaylistEntity;
import com.example.WaveHub.Models.Playlist;

public class PlaylistEntityConverter {

    public Playlist convertFromEntityToModel(PlaylistEntity playlistEntity) {
        return new Playlist(playlistEntity.getId(),
                playlistEntity.getName(),
                playlistEntity.getDescription(),
                playlistEntity.getImgLink(),
        playlistEntity.getIsDeleted());
    }

    public PlaylistEntity convertFromModelToEntity(Playlist playlist) {
        return PlaylistEntity.builder()
                .name(playlist.getName())
                .description(playlist.getDescription())
                .imgLink(playlist.getImgLink())
                .isDeleted(playlist.getIsDeleted())
                .build();
    }
}
