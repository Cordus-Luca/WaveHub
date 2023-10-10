import React, { useState } from "react";
import AddPlaylistForm from "../forms/AddPlaylistForm";
import "./styles.css";

function AddPlaylistPage() {
  return (
    <div className="">
      <div className="form-box">
        <h2 className="">Create a new playlist</h2>
        <h3 className="">Playlist</h3>
        <p className="">Please enter the playlist details</p>
        <AddPlaylistForm />
      </div>
    </div>
  );
}

export default AddPlaylistPage;
