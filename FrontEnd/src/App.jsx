import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import "./App.css";
import AddSongPage from "./pages/AddSongPage";
import HomePage from "./pages/HomePage";
import AddPlaylistPage from "./pages/AddPlaylistPage";
import UpdatePlaylistPage from "./pages/UpdatePlaylistPage";
import PlaylistSongPage from "./pages/PlaylistSongPage";
import Navbar from "./Navigation/Navbar";
import "./appStyles.css";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";

function App() {
  return (
    <div>
      <Navbar />
      <div>
        <Routes>
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/home" element={<HomePage />} />
          <Route path="/createplaylist" element={<AddPlaylistPage />} />
          <Route path="/uploadsong" element={<AddSongPage />} />
          <Route path="/playlist/:id" element={<PlaylistSongPage />} />
          <Route path="/updateplaylist/:id" element={<UpdatePlaylistPage />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
