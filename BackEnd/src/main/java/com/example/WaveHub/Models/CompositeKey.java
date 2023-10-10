package com.example.WaveHub.Models;

import java.io.Serializable;

public class CompositeKey implements Serializable {
    private Long songId;
    private Long playlistId;


    public CompositeKey(Long songId, Long playlistId) {
        this.songId = songId;
        this.playlistId = playlistId;
    }
}
