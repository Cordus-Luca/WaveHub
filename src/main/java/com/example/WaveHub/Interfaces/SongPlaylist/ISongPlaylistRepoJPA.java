package com.example.WaveHub.Interfaces.SongPlaylist;

import com.example.WaveHub.DataBaseLayer.Entities.SongPlaylistEntity;
import com.example.WaveHub.Models.CompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface ISongPlaylistRepoJPA extends JpaRepository<SongPlaylistEntity, CompositeKey> {
}
