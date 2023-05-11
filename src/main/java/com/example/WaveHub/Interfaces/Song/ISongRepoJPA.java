package com.example.WaveHub.Interfaces.Song;

import com.example.WaveHub.Models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISongRepoJPA extends JpaRepository<Song, Long> {

}
