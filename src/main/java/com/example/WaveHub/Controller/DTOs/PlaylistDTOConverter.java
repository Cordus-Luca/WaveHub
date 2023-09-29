package com.example.WaveHub.Controller.DTOs;

import com.example.WaveHub.Models.Playlist;

public class PlaylistDTOConverter {

    public Playlist convertFromCreateRequestToModel(CreatePlaylistDTO createPlaylistRequestDTO)
    {
        return Playlist.builder()
                .name(createPlaylistRequestDTO.name())
                .description(createPlaylistRequestDTO.description())
                .isDeleted(createPlaylistRequestDTO.isDeleted())
                .build();
    }

    public Playlist convertFromUpdateRequestToModel(UpdatePlaylistDTO updatePlaylistDTO) {

        return Playlist.builder()
                .id(updatePlaylistDTO.id())
                .name(updatePlaylistDTO.name())
                .description(updatePlaylistDTO.description())
                .build();
    }
}
