import React from "react";
import { useForm } from "react-hook-form";
import { createPlaylist } from "../cruds/PlaylistCRUD";
import { useState } from "react";

function AddPlaylistForm(props) {
  const { register, handleSubmit } = useForm();

  const allowedImgTypes = ["image/jpeg", "image/png", "image/gif"];
  const [isRefreshing, setIsRefreshing] = useState(false);

  const handleRefresh = () => {
    setIsRefreshing(true);
    setTimeout(() => {
      window.location.reload();
    }, 100);
  };
  function validatePlaylistUploadValues(formData) {
    var errorToDisplay = "";
    const name = formData.get("name");
    const description = formData.get("description");
    const imgLink = formData.get("imgLink");

    if (!name) {
      errorToDisplay += "Please insert a playlist name";
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

  const onSubmit = async (data) => {
    const formData = new FormData();

    formData.append("imgLink", data.imgLink[0]);
    formData.append("name", data.name);
    formData.append("description", data.description);

    if (validatePlaylistUploadValues(formData)) {
      try {
        createPlaylist(formData);
        alert("Playlist has been created successfully!");
      } catch (e) {
        alert("Something went wrong with creating the playlist!");
      }
    }
    handleRefresh();
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <div className="form-floating mb-3 text-start">
        <input
          type="text"
          className="form-control"
          id="name"
          name="name"
          placeholder="Playlist Name"
          {...register("name")}
        />
        <label htmlFor="floatingInput"> Playlist Name</label>
      </div>
      <div className="form-floating mb-3 text-start">
        <input
          type="text"
          className="form-control"
          id="description"
          name="description"
          placeholder="Playlist Description"
          {...register("description")}
        />
        <label htmlFor="floatingInput"> Playlist Description</label>
      </div>
      <div className="mb-3 d-flex flex-column justify-content-center">
        <label htmlFor="imgLink" className="form-label">
          Choose an image
        </label>
        <input
          className="form-control"
          id="imgLink"
          type="file"
          {...register("imgLink")}
        />
      </div>
      <button className="btn btn-outline-light px-5" type="submit">
        Create Playlist
      </button>
    </form>
  );
}

export default AddPlaylistForm;
