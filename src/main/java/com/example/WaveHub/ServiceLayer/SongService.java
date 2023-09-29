package com.example.WaveHub.ServiceLayer;

import com.example.WaveHub.Interfaces.Song.ISongRepoJPA;
import com.example.WaveHub.Interfaces.Song.ISongRepository;
import com.example.WaveHub.Interfaces.Song.ISongService;
import com.example.WaveHub.Models.Song;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class SongService implements ISongService {
    private final ISongRepository songRepository;

    @Autowired
    public SongService(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> getSongs() {
        return songRepository.getAllSongs();
    }

    public Song getSongById(Long songId) {return songRepository.getSongById(songId);}

    public void addNewSong(Song song, MultipartFile songFile, MultipartFile imgFile) throws IOException {
        String songLink = String.valueOf(UUID.randomUUID());
        String imgLink = String.valueOf(UUID.randomUUID());
        song.setMp3Link(songLink);
        song.setImgLink(imgLink);

        songRepository.addNewSong(song, songFile, imgFile);
    }

    public void deleteSong(Long songId) {
        songRepository.deleteSong(songId);
    }

    @Transactional
    public void updateSong(Song song) {
        songRepository.updateSong(song);
    }

    @Transactional
    public void addSongToPlaylist(Long songId, Long playlistId) {
        songRepository.addSongToPlaylist(songId, playlistId);
    }

    @Transactional
    public List<Song> getPlaylistSongs(Long playlistId) {
        return songRepository.getPlaylistSongs(playlistId);
    }

}
