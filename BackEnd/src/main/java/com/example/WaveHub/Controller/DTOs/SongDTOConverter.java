package com.example.WaveHub.Controller.DTOs;

import com.example.WaveHub.Models.Song;

public final class SongDTOConverter {

    public Song convertFromCreateSongRequestToModel(CreateSongDTO createSongRequestDTO)
    {
        return Song.builder().name(createSongRequestDTO.name())
                .artist(createSongRequestDTO.artist())
                .album(createSongRequestDTO.album())
                .isDeleted(createSongRequestDTO.isDeleted())
                .build();
    }
}
