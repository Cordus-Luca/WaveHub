import { useParams } from "react-router";
import UpdatePlaylistForm from "../forms/UpdatePlaylistForm";
import React, { useEffect, useState } from "react";
import { getPlaylist } from "../cruds/PlaylistCRUD";
import "./styles.css";

function UpdatePlaylistPage() {
  const params = useParams();

  const playlistId = params.id;

  const [playlist, setPlaylist] = useState([]);

  const fetchPlaylistDetails = async () => {
    const playlistResponsObj = await getPlaylist(playlistId);
    const playlistObj = playlistResponsObj.data;
    setPlaylist(playlistObj);
  };

  useEffect(() => {
    fetchPlaylistDetails();
  }, [params.id]);

  return (
    <div className="form-box">
      <h2>You are updating {playlist.name}</h2>
      <h3>Current description: {playlist.description}</h3>
      <UpdatePlaylistForm playlistId={playlist.id} />
    </div>
  );
}

export default UpdatePlaylistPage;
