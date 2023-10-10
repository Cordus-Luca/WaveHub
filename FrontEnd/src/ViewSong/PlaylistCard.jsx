import React from "react";
import { Link } from "react-router-dom";
import { useState } from "react";
import { deleteSong } from "../cruds/SongCRUD";

function PlaylistSongCard(props) {
  const song = props.song;

  const handleDeleteSong = () => {
    deleteSong(song.id);
  };

  const [isRefreshing, setIsRefreshing] = useState(false);

  const handleRefresh = () => {
    setIsRefreshing(true);
    setTimeout(() => {
      window.location.reload();
    }, 1000);
  };

  function handleDeleteAndRefresh() {
    handleDeleteSong();
    handleRefresh();
  }

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
          className="card-body mb-auto playlist-song-titlecard"
          style={{
            borderRadius: 10,
            backgroundColor: "#51087E",
            marginBottom: 6,
            marginLeft: 10,
            width: 200,
            paddingRight: 10,
          }}
        >
          <h3
            className="card-title"
            style={{ paddingLeft: 15, paddingRight: 15 }}
          >
            {song.name}
          </h3>
          <h5 className="card-title" style={{ paddingLeft: 18 }}>
            {" "}
            By {song.artist}
          </h5>
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
            >
              <source type="auido/mpeg" />
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
      </div>
    </div>
  );
}

export default PlaylistSongCard;
