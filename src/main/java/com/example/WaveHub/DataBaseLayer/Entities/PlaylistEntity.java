package com.example.WaveHub.DataBaseLayer.Entities;

import com.example.WaveHub.Models.Song;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Table(name = "playlist")
@Builder
@NoArgsConstructor
public class PlaylistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany()
    @JoinTable(
            name = "song_in_playlist",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id"))
    private Collection<SongEntity> playlistSongs;

    public PlaylistEntity(Long id, String name, Collection<SongEntity> playlistSongs) {
        this.id = id;
        this.name = name;
        this.playlistSongs = playlistSongs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<SongEntity> getPlaylistSongs() {
        return playlistSongs;
    }

    public void setPlaylistSongs(Collection<SongEntity> playlistSongs) {
        this.playlistSongs = playlistSongs;
    }
}
