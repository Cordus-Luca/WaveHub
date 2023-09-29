package com.example.WaveHub.DataBaseLayer.Entities;

import com.example.WaveHub.Models.CompositeKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "song_in_playlist")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CompositeKey.class)
public class SongPlaylistEntity {

    @Id
    @Column(name = "song_Id")
    private Long songId;

    @Id
    @Column(name = "playlist_Id")
    private Long playlistId;

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public Long getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(Long playlistId) {
        this.playlistId = playlistId;
    }
}
