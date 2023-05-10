package com.example.WaveHub.Interfaces;

import com.example.WaveHub.Models.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ISongRepository{

    List<Song> findAll();
    List<Song> findSongByName(String name);
}