package com.example.WaveHub.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/songplaylist")
@CrossOrigin("http://localhost:5173")
public class SongPlaylistController {
}
