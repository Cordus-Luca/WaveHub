package com.example.WaveHub.DataBaseLayer;

import com.example.WaveHub.Interfaces.SongPlaylist.ISongPlaylistRepo;
import com.example.WaveHub.Interfaces.SongPlaylist.ISongPlaylistRepoJPA;
import com.example.WaveHub.Models.CompositeKey;

public class SongPlaylistRepo implements ISongPlaylistRepo {
    private final ISongPlaylistRepoJPA songPlaylistRepoJPA;

    public SongPlaylistRepo(ISongPlaylistRepoJPA songPlaylistRepoJPA) {
        this.songPlaylistRepoJPA = songPlaylistRepoJPA;
    }

    @Override
    public void  removeSongFromPlaylist(Long songId, Long playlistId) {

        boolean exists = songPlaylistRepoJPA.existsById(new CompositeKey(songId,playlistId));

        if (!exists) {
            throw new IllegalStateException("song and playlist pair does not exist");
        }

        songPlaylistRepoJPA.deleteById(new CompositeKey(songId, playlistId));

    }
}
