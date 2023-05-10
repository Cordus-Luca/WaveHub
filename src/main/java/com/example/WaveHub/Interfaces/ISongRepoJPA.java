package com.example.WaveHub.Interfaces;

import com.example.WaveHub.Models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISongRepoJPA extends JpaRepository<Song, Long> {
}
