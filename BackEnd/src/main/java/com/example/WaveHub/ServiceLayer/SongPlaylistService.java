//package com.example.WaveHub.ServiceLayer;
//
//import com.example.WaveHub.Interfaces.SongPlaylist.ISongPlaylistRepo;
//import com.example.WaveHub.Interfaces.SongPlaylist.ISongPlaylistService;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SongPlaylistService implements ISongPlaylistService {
//
//    private final ISongPlaylistRepo songPlaylistRepo;
//
//    public SongPlaylistService(ISongPlaylistRepo songPlaylistRepo) {
//        this.songPlaylistRepo = songPlaylistRepo;
//    }
//
//    @Override
//    public void removeSongFromPlaylist(Long songId, Long playlistId) {
//        songPlaylistRepo.removeSongFromPlaylist(songId, playlistId);
//    }
//}
