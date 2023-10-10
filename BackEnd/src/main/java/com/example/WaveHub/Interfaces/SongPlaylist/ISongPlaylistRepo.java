package com.example.WaveHub.Interfaces.SongPlaylist;

import org.springframework.stereotype.Repository;

@Repository
public interface ISongPlaylistRepo {

    void removeSongFromPlaylist(Long songId, Long playlistId);

}
