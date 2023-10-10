import React, { useState, useEffect } from "react";
import { useForm } from "react-hook-form";
import { getPlaylist, updatePlaylist } from "../cruds/PlaylistCRUD";

function UpdatePlaylistForm({ playlistId }) {
  const { register, handleSubmit } = useForm();
  const [playlist, setPlaylist] = useState([]);

  const fetchPlaylistDetails = async () => {
    const playlistResponsObj = await getPlaylist(playlistId);
    const playlistObj = playlistResponsObj.data;
    setPlaylist(playlistObj);
  };

  useEffect(() => {
    fetchPlaylistDetails();
  }, []);

  const [isRefreshing, setIsRefreshing] = useState(false);

  const handleRefresh = () => {
    setIsRefreshing(true);
    setTimeout(() => {
      window.location.reload();
    }, 100);
  };

  const onSubmit = async (data) => {
    const formData = new FormData();

    formData.append("id", data.id);
    formData.append("name", data.name);
    formData.append("description", data.description);

    alert("Playlist updated");
    updatePlaylist(formData, playlistId);
    handleRefresh();
  };

  function validatePlaylistUpdateValues(formData) {
    var errorToDisplay = "";
    const name = formData.get("name");
    const description = formData.get("description");
    const imgLink = formData.get("imgLink");

    const allowedImgTypes = ["image/jpeg", "image/png", "image/gif"];

    if (!name) {
      errorToDisplay += "You must insert a playlist name";
    }

    if (!description) {
      errorToDisplay += "You must insert a playlist description";
    }

    if (!imgLink || !allowedImgTypes.includes(imgLink.type)) {
      if (errorToDisplay) {
        errorToDisplay += " and an image file in jpeg/png/gif format";
      } else {
        errorToDisplay +=
          "You must insert an image file in jpeg/png/gif format";
      }
    }

    if (errorToDisplay) {
      alert(errorToDisplay);
      return false;
    }

    return true;
  }

  return (
    <div>
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="">
          <input
            type="text"
            className="form-control"
            id="name"
            name="name"
            placeholder="Playlist Name"
            {...register("name")}
          />
          <label htmlFor="floatingInput"> New Playlist Name</label>
        </div>
        <div className="">
          <input
            type="text"
            className="form-control"
            id="description"
            name="description"
            placeholder="Playlist Description"
            {...register("description")}
          />
          <label htmlFor="floatingInput"> New Playlist Description</label>
        </div>

        <button className="" type="submit">
          Update Playlist
        </button>
        {console.log()}
      </form>
    </div>
  );
}

export default UpdatePlaylistForm;
