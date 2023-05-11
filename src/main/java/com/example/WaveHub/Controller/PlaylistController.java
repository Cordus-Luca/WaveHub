package com.example.WaveHub.Controller;

import com.example.WaveHub.Interfaces.Playlist.IPlaylistService;
import com.example.WaveHub.Models.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/playlist")
public class PlaylistController{

    //  TODO: FINISH CRUD FOR PLAYLIST
    private final IPlaylistService playlistService;

    @Autowired
    public PlaylistController(IPlaylistService playlistService){this.playlistService = playlistService;}

    @GetMapping()
    public List<Playlist> getPlaylists() {return playlistService.getPlaylists();}

    @PostMapping()
    public void registerNewPlaylist(@RequestBody Playlist playlist){
        playlistService.addNewPlaylist(playlist);
    }

    @DeleteMapping(path = "{playlistId}")
    public void deletePlaylist(@PathVariable("playlistId") Long playlistId) {playlistService.deletePlaylist(playlistId);}

    @PutMapping(path = "{playlistId}")
    public void updatePlaylist(
            @PathVariable("playlistId") Long playlistId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<Long> songsId) {
        playlistService.updatePlaylist(playlistId, name, songsId);}
}
