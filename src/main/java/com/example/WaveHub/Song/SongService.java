package com.example.WaveHub.Song;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SongService {
    private final SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> getSongs() {
        return songRepository.findAll();
    }

    public void addNewSong(Song song) {
        Optional<Song> songByName = songRepository
                .findSongByName(song.getName());
        if (songByName.isPresent()) {
            throw new IllegalStateException("name taken");
        }
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
