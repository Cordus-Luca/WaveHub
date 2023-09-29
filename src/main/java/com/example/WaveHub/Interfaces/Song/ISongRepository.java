package com.example.WaveHub.Interfaces.Song;

import com.example.WaveHub.Models.Song;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Repository
public interface ISongRepository{

    List<Song> getAllSongs();
    Song getSongById(Long songId);
    void addNewSong(Song song, MultipartFile songFile, MultipartFile imgFile) throws IOException;
    void deleteSong(Long songId);

    void updateSong(Song song);
    void addSongToPlaylist(Long songId, Long playlistId);

    List<Song> getPlaylistSongs(Long playlistId);

}
