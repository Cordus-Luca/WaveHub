import React, { useState } from "react";
import AddSongForm from "../forms/AddSongForm";
import "./styles.css";

function AddSongPage() {
  const [stompClient, setStompClient] = useState(null);

  return (
    <div className="">
      <div className="form-box">
        <h3 className="">Song Details</h3>
        <p className="">Please enter your song details and the song file!</p>
        <AddSongForm stompClient={stompClient} />
      </div>
    </div>
  );
}

export default AddSongPage;
