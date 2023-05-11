package com.example.WaveHub.Interfaces.Song;

import com.example.WaveHub.Models.Song;

import java.util.List;
import java.util.Optional;

public interface ISongService {
    List<Song> getSongs();
    Optional<Song> getSongById(Long songId);
    void addNewSong(Song song);
    void deleteSong(Long songId);
    void updateSong(Long songId, String name, String artist);

}
