package com.example.WaveHub.Interfaces;

import com.example.WaveHub.Models.Song;

import java.util.List;

public interface ISongService {
    List<Song> getSongs();
    void addNewSong(Song song);
    void deleteSong(Long songId);
    void updateSong(Long songId, String name, String artist);
}
