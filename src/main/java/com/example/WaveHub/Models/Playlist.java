package com.example.WaveHub.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private List<Long> songsId;
    @Transient
    private LocalDate dateAdded;
    @ManyToMany(mappedBy = "inPlaylist")
    Set<Song> hasSong;

    public Playlist() {
    }

    public Playlist(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<Long> getSongsId() {
        return songsId;
    }

    public void setSongsId(List<Long> songsId) {
        this.songsId = songsId;
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

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }
}
