package com.example.WaveHub.Controller;

//import com.example.WaveHub.Controller.DTOs.PlaylistDTOConverter;
import com.example.WaveHub.Controller.DTOs.CreatePlaylistDTO;
import com.example.WaveHub.Controller.DTOs.PlaylistDTOConverter;
import com.example.WaveHub.Controller.DTOs.UpdatePlaylistDTO;
import com.example.WaveHub.Interfaces.Playlist.IPlaylistService;
import com.example.WaveHub.Models.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping(path = "/api/v1/playlist")
public class PlaylistController{

    private final IPlaylistService playlistService;

    @Autowired
    public PlaylistController(IPlaylistService playlistService){this.playlistService = playlistService;}

    @GetMapping
    public List<Playlist> getPlaylists() {return playlistService.getPlaylists();}

    @GetMapping(path = "{playlistId}")
    public Playlist getPlaylistById(@PathVariable("playlistId") Long playlistId) {
        return playlistService.getPlaylistById(playlistId);
    }

    @PostMapping()
    public @ResponseBody ResponseEntity<Void> registerNewPlaylist(@RequestParam("imgLink") MultipartFile imgFile,
                                                                  @RequestParam("name") String name,
                                                                  @RequestParam("description") String description,
                                                                  @RequestParam(value = "isDeleted", required = false) Integer isDeleted
                                                                    ) throws IOException {

        CreatePlaylistDTO createPlaylistDTO = new CreatePlaylistDTO(name, description, isDeleted);
        Playlist playlist = new PlaylistDTOConverter().convertFromCreateRequestToModel(createPlaylistDTO);
        playlistService.addNewPlaylist(playlist, imgFile);

        return ResponseEntity.noContent().build();
    }

//    @DeleteMapping(path = "{playlistId}")
//    public void deletePlaylist(@PathVariable("playlistId") Long playlistId) {playlistService.deletePlaylist(playlistId);}

    @DeleteMapping(path = "{playlistId}")
    public @ResponseBody ResponseEntity<Void> deletePlaylist(@PathVariable("playlistId") Long playlistId) {
        playlistService.deletePlaylist(playlistId);

        return ResponseEntity.noContent().build();
    }


    @PutMapping(path = "{playlistId}")
    public @ResponseBody ResponseEntity<Void> updatePlaylist(@PathVariable("playlistId") Long playlistId,
                                                             @RequestParam("name") String name,
                                                             @RequestParam("description") String description
                                                                 ) throws IOException {

        UpdatePlaylistDTO updatePlaylistDTO = new UpdatePlaylistDTO(playlistId, name, description);
        Playlist playlist = new PlaylistDTOConverter().convertFromUpdateRequestToModel(updatePlaylistDTO);
        playlistService.updatePlaylist(playlist);

        return ResponseEntity.noContent().build();

    }

}