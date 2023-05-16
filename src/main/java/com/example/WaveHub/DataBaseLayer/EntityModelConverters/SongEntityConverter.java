package com.example.WaveHub.DataBaseLayer.EntityModelConverters;

import com.example.WaveHub.DataBaseLayer.Entities.SongEntity;
import com.example.WaveHub.Models.Song;

public class SongEntityConverter {

    public Song convertFromEntityToModel(SongEntity songEnt) {
        return new Song(songEnt.getId(),
                songEnt.getName(),
                songEnt.getArtist(),
                songEnt.getAlbum());
    }

    public SongEntity convertFromModelToEntity(Song song) {
        return SongEntity.builder()
                .name(song.getName())
                .artist(song.getArtist())
                .album(song.getAlbum())
                .build();
    }
}
