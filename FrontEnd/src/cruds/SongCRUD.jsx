import {
  retrieve,
  updateAsync,
  post,
  deleteFromDB,
  addSongToP,
} from "./UrlCRUD";

const controllerPath = "/api/v1/song";

export function getSongs() {
  const getSongsPath = controllerPath;

  return retrieve(getSongsPath);
}

export async function addSong(song) {
  const addSongsPath = controllerPath;

  const addSongsBody = {
    songId: song.id,
  };

  await updateAsync(addSongsPath, addSongsBody);
}

export function createSong(formData) {
  post(controllerPath, formData);
}

export function getPlaylistSongs(playlistId) {
  const getPlaylistSongsPath = "/playlist/" + playlistId;

  const playlists = retrieve(controllerPath + getPlaylistSongsPath);
  return playlists;
}

export function deleteSong(songId) {
  deleteFromDB("/api/v1/song/", songId);
}

export function addSongToPlaylist(data) {
  return addSongToP(controllerPath, data);
}
