package com.example.WaveHub.DataBaseLayer.Entities;

import com.example.WaveHub.Models.Song;
import com.example.WaveHub.Models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

@Entity
@Table(name = "playlist")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description = "No description";

    @Column(name = "imgLink")
    private String imgLink;

    @Column(name = "isDeleted")
    private Integer isDeleted = 0;

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

    public PlaylistEntity(Long id, String name, String description, String imgLink, Collection<SongEntity> playlistSongs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgLink = imgLink;
        this.playlistSongs = playlistSongs;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
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

    public Collection<SongEntity> getPlaylistSongs() {
        return playlistSongs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public void setPlaylistSongs(Collection<SongEntity> playlistSongs) {
        this.playlistSongs = playlistSongs;
    }
}
