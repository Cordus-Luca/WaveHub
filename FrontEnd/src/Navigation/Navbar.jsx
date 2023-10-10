import { Children, useEffect, useState } from "react";
import { Link, useMatch, useResolvedPath } from "react-router-dom";
import { getPlaylists } from "../cruds/PlaylistCRUD";

export default function Navbar() {
  const [playlists, setPlaylists] = useState([]);
  const [selectedPlaylist, setSelectedPlaylist] = useState("");

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

  function refreshPage() {
    window.location.reload(false);
  }

  return (
    <nav className="nav">
      <Link to="/login" className="site-title">
        WaveHub
      </Link>
      <ul>
        <CustomLink to="/register">Register</CustomLink>
        <CustomLink to="/login">Log in</CustomLink>
        <CustomLink to="/home">All Songs</CustomLink>
        <CustomLink to="/uploadsong">Upload Song</CustomLink>
        <CustomLink to="/createplaylist">Create Playlist</CustomLink>
        <label htmlFor="playlist"></label>
        <select
          name="playlist"
          id="playlistId"
          value={selectedPlaylist}
          onChange={handlePlaylistChange}
        >
          {sessionStorage.getItem("accessToken") != null &&
            playlists.map((playlist) => (
              <option key={playlist.id} value={`${playlist.id}`}>
                {playlist.name}
              </option>
            ))}
        </select>
        <CustomLink to={"/playlist/" + selectedPlaylist}>
          Access Playlist
        </CustomLink>
        <CustomLink to={"/updateplaylist/" + selectedPlaylist}>
          Update Playlist
        </CustomLink>
      </ul>
    </nav>
  );
}

function CustomLink({ to, children, ...props }) {
  const resolvedPath = useResolvedPath(to);
  const isActive = useMatch({ path: resolvedPath.pathname, end: true });
  return (
    <li className={isActive ? "active" : ""}>
      <Link to={to} {...props}>
        {children}
      </Link>
    </li>
  );
}
