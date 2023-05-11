package com.example.WaveHub.Models;

import jakarta.persistence.*;
import jdk.jfr.Enabled;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Set;

@Entity
@Table
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String artist;
    private String album;
    @ManyToMany
    @JoinTable(
            name = "song_in_playlist",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id"))
    Set<Playlist> inPlaylist;


    public Song(Long id, String name, String artist, String album, LocalDate dateAdded) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.album = album;
    }

    public Song() {
    }

    public Song(Long id, String name, String artist, String album) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.album = album;
    }

    public Song(String name, String artist, String album) {
        this.name = name;
        this.artist = artist;
        this.album = album;
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                '}';
    }
}
