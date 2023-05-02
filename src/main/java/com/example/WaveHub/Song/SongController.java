package com.example.WaveHub.Song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/song")
public class SongController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping()
    public List<Song> getSongs() {
        return songService.getSongs();
    }

    @PostMapping()
    public void registerNewSong(@RequestBody Song song) {
        songService.addNewSong(song);
    }

    @DeleteMapping(path = "{songId}")
    public void deleteSong(@PathVariable("songId") Long songId) {
        songService.deleteSong(songId);
    }

    @PutMapping(path = "{songId}")
    public void updateSong(
            @PathVariable("songId") Long songId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String artist) {
        songService.updateSong(songId, name, artist);
    }

}
