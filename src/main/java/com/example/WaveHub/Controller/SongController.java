package com.example.WaveHub.Controller;

import com.example.WaveHub.Controller.DTOs.AddSongToPlaylistDTO;
import com.example.WaveHub.Controller.DTOs.CreateSongDTO;
import com.example.WaveHub.Controller.DTOs.RemoveSongFromPlaylistDTO;
import com.example.WaveHub.Controller.DTOs.SongDTOConverter;
import com.example.WaveHub.DataBaseLayer.Entities.SongEntity;
import com.example.WaveHub.Interfaces.Song.ISongService;
import com.example.WaveHub.Models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/song")
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = {"*"})
public class SongController {

    private final ISongService songService;

    @Autowired
    public SongController(ISongService songService) {this.songService = songService;}

    @GetMapping
    public ResponseEntity<List<Song>> getSongs(){

        return ResponseEntity.ok(songService.getSongs());
    }

    @GetMapping(path = "{songId}")
    public Song getSongsById(@PathVariable("songId") Long songId) {
        return songService.getSongById(songId);
    }

    @PostMapping()
    public @ResponseBody ResponseEntity<Void> registerNewSong(@RequestParam("mp3Link") MultipartFile songFile,
                                                              @RequestParam("imgLink") MultipartFile imgFile,
                                                              @RequestParam("name") String name,
                                                              @RequestParam("artist") String artist,
                                                              @RequestParam("album") String album,
                                                              @RequestParam(value = "isDeleted", required = false) Integer isDeleted
                                                              ) throws IOException {
        CreateSongDTO createSongDTO = new CreateSongDTO(name, artist, album, isDeleted);
        Song song = new SongDTOConverter().convertFromCreateSongRequestToModel(createSongDTO);
        songService.addNewSong(song, songFile, imgFile);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "{songId}")
    public ResponseEntity<Void> deleteSong(@PathVariable("songId") Long songId) {
        songService.deleteSong(songId);

        return ResponseEntity.noContent().build();
    }


    @PutMapping()
    public ResponseEntity<Void> addSongToPlaylist(@RequestBody AddSongToPlaylistDTO addSongToPlaylistDTO) {
        songService.addSongToPlaylist(addSongToPlaylistDTO.songId(), addSongToPlaylistDTO.playlistId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/playlist/{playlistId}")
    public ResponseEntity<List<Song>> getPlaylistSongs(@PathVariable(name = "playlistId") Long playlistId) {
        return ResponseEntity.ok(songService.getPlaylistSongs(playlistId));
    }

    @PutMapping(path = "{songId}")
    public ResponseEntity<Void> updateSong(@RequestBody SongEntity songEntity) {
        Song song = new Song();
        song.setId(songEntity.getId());
        song.setName(songEntity.getName());
        song.setAlbum(songEntity.getAlbum());
        song.setArtist(songEntity.getArtist());
        song.setMp3Link(songEntity.getMp3Link());
        song.setImgLink(songEntity.getImgLink());
        songService.updateSong(song);

        return ResponseEntity.noContent().build();
    }
}
