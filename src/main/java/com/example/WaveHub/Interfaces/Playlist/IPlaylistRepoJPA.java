package com.example.WaveHub.Interfaces.Playlist;

import com.example.WaveHub.DataBaseLayer.Entities.PlaylistEntity;
import com.example.WaveHub.Models.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlaylistRepoJPA extends JpaRepository<PlaylistEntity, Long> {
}
