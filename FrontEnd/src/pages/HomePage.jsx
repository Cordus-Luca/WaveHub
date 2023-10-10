import React, { useState, useEffect } from "react";
import ViewSong from "../ViewSong/ViewSong";
import { getSongs } from "../cruds/SongCRUD";

const HomePage = () => {
  const [isPlaying, setIsPlaying] = useState(false);
  const [currentTime, setCurrentTime] = useState(0);
  const [songs, setSongs] = useState([]);

  const fetchSongs = async () => {
    const songList = await getSongs();
    const songListing = songList.data;
    setSongs(songListing);
  };

  useEffect(() => {
    fetchSongs();
  }, []);

  useEffect(() => {
    console.log(songs);
  }, [songs]);

  const handlePlayPause = () => {
    setIsPlaying((prevIsPlaying) => !prevIsPlaying);
  };

  const formatTime = (timeInSeconds) => {
    const minutes = Math.floor(timeInSeconds / 60);
    const seconds = timeInSeconds % 60;
    return `${minutes.toString().padStart(2, "0")}:${seconds
      .toString()
      .padStart(2, "0")}`;
  };

  return (
    <div>
      <div className="mb-5">
        <div className="song-list-container">
          {sessionStorage.getItem("accessToken") != null &&
            songs.map((song) => {
              return <ViewSong song={song} />;
            })}
        </div>
      </div>
    </div>
  );
};

export default HomePage;
