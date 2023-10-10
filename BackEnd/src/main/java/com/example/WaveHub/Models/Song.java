package com.example.WaveHub.Models;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Set;

@Entity
@Table
@Builder
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String artist;
    private String album;
    private String imgLink;
    private String mp3Link;
    private Integer isDeleted;

    public Song(Long id, String name, String artist, String album, String link) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.imgLink = link;
    }

    public Song(Long id, String name, String artist, String album, String imgLink, String mp3Link) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.imgLink = imgLink;
        this.mp3Link = mp3Link;
    }

    public Song() {
    }

    public Song(String name, String artist, String album) {
        this.name = name;
        this.artist = artist;
        this.album = album;
    }

    public Song(Long id, String name, String artist, String album) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.album = album;
    }

    public Song(Long id, String name, String artist, String album, String imgLink, String mp3Link, Integer isDeleted) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.imgLink = imgLink;
        this.mp3Link = mp3Link;
        this.isDeleted = isDeleted;
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

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String link) {
        this.imgLink = link;
    }

    public String getMp3Link() {
        return mp3Link;
    }

    public void setMp3Link(String mp3Link) {
        this.mp3Link = mp3Link;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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
