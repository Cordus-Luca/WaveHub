package com.example.WaveHub.Interfaces.Song;

import com.example.WaveHub.DataBaseLayer.Entities.SongEntity;
import com.example.WaveHub.Models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface ISongRepoJPA extends JpaRepository<SongEntity, Long> {

}
