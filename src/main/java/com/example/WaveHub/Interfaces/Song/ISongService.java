package com.example.WaveHub.Interfaces.Song;

import com.example.WaveHub.Models.Song;

import java.util.List;
import java.util.Optional;

public interface ISongService {
    List<Song> getSongs();
    Song getSongById(Long songId);
    void addNewSong(Song song);
    void deleteSong(Long songId);
    void updateSong(Song song);
    void addSongToPlaylist(Long songId, Long playlistId);

}
