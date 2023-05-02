package com.example.WaveHub.Song;

import jakarta.persistence.*;
import jdk.jfr.Enabled;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Entity
@Table
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String artist;
    private String album;
    @Transient
    private LocalDate dateAdded;



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

    public LocalDate getDateAdded() {
        return LocalDate.now();
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
