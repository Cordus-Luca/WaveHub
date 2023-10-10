import PlaylistCard from "../ViewSong/PlaylistCard";
import { useParams } from "react-router-dom";
import { getPlaylist, deletePlaylist } from "../cruds/PlaylistCRUD";
import { useState, useEffect, useRef } from "react";
import { getPlaylistSongs } from "../cruds/SongCRUD";
import Pagination from "../Pagination/Pagination";
import "./styles.css";

function PlaylistSongPage() {
  const params = useParams();

  const [playlist, setPlaylist] = useState([]);
  const [songs, setSongs] = useState([]);

  const [isRefreshing, setIsRefreshing] = useState(false);

  const playlistId = params.id;

  const fetchPlaylistDetails = async () => {
    const playlistResponsObj = await getPlaylist(playlistId);
    const playlistObj = playlistResponsObj.data;
    setPlaylist(playlistObj);

    console.log(sessionStorage.getItem("accessToken"));
  };

  const fetchSongs = async () => {
    const songsOfPlaylist = await getPlaylistSongs(playlistId);
    setSongs(songsOfPlaylist.data);
  };

  useEffect(() => {
    fetchPlaylistDetails();
    fetchSongs();
  }, [params.id]);

  const handleRefresh = () => {
    setIsRefreshing(true);
    setTimeout(() => {
      window.location.reload();
    }, 100);
  };

  const handleDeleteAndRefresh = () => {
    deletePlaylist(playlistId);
    handleRefresh();
  };

  const elementRef = useRef(null);

  return (
    <div className="">
      <div>
        <div
          className="flex-container song-playing-background"
          ref={elementRef}
          style={{ marginTop: 60 }}
        >
          <div className="d-flex flex-row justify-content-start flex-item-playlist">
            <div className="text-center">
              <img
                src={
                  "https://wavehub.fra1.cdn.digitaloceanspaces.com/" +
                  playlist.imgLink
                }
                className="rounded"
                style={{
                  height: 250,
                  width: 250,
                }}
                alt="PLAYLISTUL A FOST STERS"
              ></img>
            </div>

            <button
              type="button"
              className="btn btn-danger rounded-top rounded-bottom media-button"
              onClick={() => {
                handleDeleteAndRefresh();
              }}
            >
              Delete
            </button>
          </div>
          <div className="media-body">
            <h1 className="text-start py-4t media-heading">{playlist.name}</h1>
            <p className="text-start py-4">{playlist.description}</p>
          </div>
        </div>

        <div className="mb-5">
          <div className="song-list-container">
            {songs.map((song) => {
              return <PlaylistCard key={song.id} song={song} />;
            })}
          </div>
        </div>
      </div>
    </div>
  );
}

export default PlaylistSongPage;
