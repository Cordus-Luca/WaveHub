import React, { useState, useEffect } from "react";
import "./cardStyles.css";
import { deleteSong, addSongToPlaylist } from "../cruds/SongCRUD";
import { getPlaylists } from "../cruds/PlaylistCRUD";

function ViewSong(props) {
  const song = props.song;
  const [selectedPlaylist, setSelectedPlaylist] = useState("");
  const [playlists, setPlaylists] = useState([]);

  const fetchPlaylists = async () => {
    const playlistList = await getPlaylists();
    const playlistListing = playlistList.data;
    setPlaylists(playlistListing);
  };

  useEffect(() => {
    fetchPlaylists();
  }, []);

  const handlePlaylistChange = (event) => {
    setSelectedPlaylist(event.target.value);
  };

  const [isPlaying, setIsPlaying] = useState(false);

  const handleAudioPlay = () => {
    setIsPlaying(true);
  };

  const handleAudioPause = () => {
    setIsPlaying(false);
  };

  const togglePlayback = () => {
    setIsPlaying((prevIsPlaying) => !prevIsPlaying);
  };

  const handleDeleteSong = () => {
    deleteSong(song.id);
  };

  const [isRefreshing, setIsRefreshing] = useState(false);

  const handleRefresh = () => {
    setIsRefreshing(true);
    setTimeout(() => {
      window.location.reload();
    }, 100);
  };

  function handleDeleteAndRefresh() {
    handleDeleteSong();
    handleRefresh();
  }

  const handleAddToPlaylist = () => {
    const data = {
      songId: song.id,
      playlistId: selectedPlaylist,
    };

    addSongToPlaylist(data)
      .then((response) => {
        // Handle successful addition to playlist
        console.log("Song added to playlist:", response.data);
      })
      .catch((error) => {
        // Handle error
        console.error("Error adding song to playlist:", error);
      });
  };

  return (
    <div
      className="col-md-4 songs-flex-container"
      style={{
        border: 2,
        backgroundColor: "black",
        padding: 10,
        paddingBottom: 6,
      }}
    >
      <div className="card" style={{ display: "flex" }}>
        <div className="media p-4">
          <img
            src={
              "https://wavehub.fra1.cdn.digitaloceanspaces.com/" + song.imgLink
            }
            style={{
              height: 135,
              width: 135,
              borderRadius: 10,
            }}
          ></img>
        </div>
        <div
          className="card-body mb-auto"
          style={{
            borderRadius: 10,
            backgroundColor: "#51087E",
            marginBottom: 6,
            width: 150,
          }}
        >
          <h3
            className="card-title"
            style={{ paddingLeft: 15, paddingRight: 15 }}
          >
            {song.name}
          </h3>
          <h5 style={{ paddingLeft: 18 }}> By {song.artist}</h5>
        </div>

        <ul className="list-group list-group-flush">
          <li className="list-group-item">
            <span className="text-primary pe-1"></span>
            Album: {song.album}
          </li>

          <li className="list-group-item">
            <audio
              controls
              className=""
              src={
                "https://wavehub.fra1.cdn.digitaloceanspaces.com/" +
                song.mp3Link
              }
              style={{
                borderRadius: 10,
              }}
              onPlay={handleAudioPlay}
              onPause={handleAudioPause}
            >
              <source type="audio/mpeg" />
              Your browser does not support the used audio element
            </audio>
          </li>
        </ul>

        <div className="card-footer text-center p-3">
          <div className="d-flex flex-row justify-content-center">
            {isRefreshing ? (
              <div>Refreshing page...</div>
            ) : (
              <button
                type="button"
                className="btn-outline-primary rounded m-1"
                onClick={handleDeleteAndRefresh}
              >
                🗑
              </button>
            )}
          </div>
        </div>
        <label htmlFor="playlist"></label>
        <select
          name="playlist"
          id="playlistId"
          value={selectedPlaylist}
          onChange={handlePlaylistChange}
          style={{ height: 42 }}
        >
          {playlists.map((playlist) => (
            <option key={playlist.id} value={`${playlist.id}`}>
              {playlist.name}
            </option>
          ))}
        </select>
        <button style={{ height: 42 }} onClick={handleAddToPlaylist}>
          Add to playlist
        </button>
      </div>
      <div
        className="playing-song"
        style={{
          position: "fixed",
          bottom: 15,
          left: 15,
          backgroundColor: "rgba(0, 0, 0, 0.5)",
          color: "white",
          padding: "10px",
          visibility: isPlaying ? "visible" : "hidden",
          borderRadius: 10,
        }}
      >
        <img
          src={
            "https://wavehub.fra1.cdn.digitaloceanspaces.com/" + song.imgLink
          }
          style={{
            height: 135,
            width: 135,
            borderRadius: 10,
          }}
        ></img>
        <h2 className="playing-song-item">Now Playing</h2>
        <h3 className="playing-song-item">{song.name}</h3>
        <p className="playing-song-item">By {song.artist}</p>
      </div>
    </div>
  );
}

export default ViewSong;
