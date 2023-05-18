package com.example.WaveHub.Controller;

import com.example.WaveHub.Controller.DTOs.AddSongToPlaylistDTO;
import com.example.WaveHub.Interfaces.Song.ISongService;
import com.example.WaveHub.Models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/song")
public class SongController {

    private final ISongService songService;

    @Autowired
    public SongController(ISongService songService) {this.songService = songService;}

    @GetMapping()
    public List<Song> getSongs() {
        return songService.getSongs();
    }

    @GetMapping(path = "{songId}")
    public Song getSongsById(@PathVariable("songId") Long songId) {
        return songService.getSongById(songId);
    }

    @PostMapping()
    public void registerNewSong(@RequestBody Song song) {
        songService.addNewSong(song);
    }

    @DeleteMapping(path = "{songId}")
    public void deleteSong(@PathVariable("songId") Long songId) {
        songService.deleteSong(songId);
    }

    @PutMapping()
    public ResponseEntity<Void> addSongToPlaylist(@RequestBody AddSongToPlaylistDTO addSongToPlaylistDTO) {
        songService.addSongToPlaylist(addSongToPlaylistDTO.songId(), addSongToPlaylistDTO.playlistId());
        return ResponseEntity.ok().build();
    }
}
