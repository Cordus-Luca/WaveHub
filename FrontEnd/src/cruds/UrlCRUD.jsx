import axios from "axios";

const defaultPath = "http://localhost:8080";

function addTokenIfExists() {
  const accessToken = sessionStorage.getItem("accessToken");

  if (!accessToken) {
    return null;
  }

  const headerValue = {
    Authorization: `Bearer ${accessToken}`,
  };
  return headerValue;
}

export function retrieve(path) {
  const headers = addTokenIfExists();

  return axios({
    headers: headers,
    method: "get",
    url: defaultPath + path,
  });
}

export async function updateAsync(path, body) {
  const updatePath = defaultPath + path;
  const header = addTokenIfExists();
  await axios({
    header: header,
    method: "put",
    url: updatePath,
    data: body,
  });
}

export function post(path, data) {
  const postPath = defaultPath + path;

  axios({
    method: "post",
    url: postPath,
    data: data,
  });
}

export function deleteFromDB(path, data) {
  const deletePath = defaultPath + path + data;
  //alert(deletePath);

  axios({
    method: "delete",
    url: deletePath,
  });
}

export function postTokenless(path, data) {
  const postPath = defaultPath + path;

  axios({
    method: "post",
    url: postPath,
    data: data,
  });
}

export function addSongToP(path, data) {
  const addSongToPlaylistPath = defaultPath + path;

  axios({
    method: "put",
    url: addSongToPlaylistPath,
    data: data,
  });
}
