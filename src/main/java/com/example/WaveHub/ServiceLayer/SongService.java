package com.example.WaveHub.ServiceLayer;

import com.example.WaveHub.Interfaces.ISongRepository;
import com.example.WaveHub.Interfaces.ISongService;
import com.example.WaveHub.Models.Song;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SongService implements ISongService {
    private final ISongRepository songRepository;

    @Autowired
    public SongService(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> getSongs() {
        return songRepository.findAll();
    }

    public Optional<Song> getSongById(Long songId) {return songRepository.findById(songId);}

    public void addNewSong(Song song) {
//        Optional<Song> songByName = songRepository
//                .findSongByName(song.getName());
//        if (songByName.isPresent()) {
//            throw new IllegalStateException("name taken");
//        }
        songRepository.save(song);
    }

    public void deleteSong(Long songId) {
        boolean exists = songRepository.existsById(songId);

        if (!exists) {
            throw new IllegalStateException("song with id " + songId + " does not exist");
        }

        songRepository.deleteById(songId);
    }

    @Transactional
    public void updateSong(Long songId, String name, String artist) {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new IllegalStateException(
                        "song with id " + songId + " does not exist"
                ));

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(song.getName(), name)) {
            song.setName(name);
        }

        if ((artist != null) &&
                (artist.length() > 0) &&
                !Objects.equals(song.getArtist(), artist)) {
            song.setArtist(artist);
        }
    }
}
