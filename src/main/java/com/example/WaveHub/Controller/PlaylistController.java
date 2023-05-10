package com.example.WaveHub.Controller;

import com.example.WaveHub.Interfaces.IPlaylistService;
import com.example.WaveHub.Models.Playlist;
import com.example.WaveHub.ServiceLayer.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/playlist")
public class PlaylistController{

    private final IPlaylistService playlistService;

    @Autowired
    public PlaylistController(IPlaylistService playlistService){this.playlistService = playlistService;}

    @GetMapping()
    public List<Playlist> getPlaylists() {return playlistService.getPlaylists();}

    @PostMapping()
    public void registerNewPlaylist(@RequestBody Playlist playlist){playlistService.addNewPlaylist(playlist);}


}
