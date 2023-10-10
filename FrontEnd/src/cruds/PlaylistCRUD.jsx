import { retrieve, updateAsync, post, deleteFromDB } from "./UrlCRUD";

const controllerPath = "/api/v1/playlist";

export function getPlaylists() {
  const getPlaylistsPath = controllerPath;

  return retrieve(getPlaylistsPath);
}

export function getPlaylist(playlistId) {
  const getPlaylistPath = controllerPath + "/" + playlistId;

  return retrieve(getPlaylistPath);
}

export function createPlaylist(playlist) {
  post(controllerPath, playlist);
}

export function updatePlaylist(playlist, playlistId) {
  const updatePlaylistPath = controllerPath + "/" + playlistId;
  updateAsync(updatePlaylistPath, playlist);
}

export function deletePlaylist(playlistId) {
  const deletePlaylistPath = controllerPath + "/";
  deleteFromDB("/api/v1/playlist/", playlistId);
}
