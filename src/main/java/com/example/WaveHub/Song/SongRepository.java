package com.example.WaveHub.Song;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SongRepository
        extends JpaRepository<Song, Long> {

    @Query("SELECT s FROM Song s Where s.name = ?1")
    Optional<Song> findSongByName(String name);

}
