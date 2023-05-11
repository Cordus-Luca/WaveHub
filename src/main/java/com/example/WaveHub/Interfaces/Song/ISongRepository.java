package com.example.WaveHub.Interfaces.Song;

import com.example.WaveHub.Models.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ISongRepository{

    List<Song> getAllSongs();
    Optional<Song> getSongById(Long songId);
}
