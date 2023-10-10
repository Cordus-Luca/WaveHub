import React from "react";
import { useForm } from "react-hook-form";
import { createSong } from "../cruds/SongCRUD";

function AddSongForm(props) {
  const { register, handleSubmit } = useForm();
  const stompClient = props.stompClient;

  function validateSongUploadValues(formData) {
    var errorToDisplay = "";
    const name = formData.get("name");
    const album = formData.get("album");
    const artist = formData.get("artist");
    const mp3Link = formData.get("mp3Link");
    const imgLink = formData.get("imgLink");

    const allowedImgTypes = ["image/jpeg", "image/png", "image/gif"];

    if (!name) {
      errorToDisplay += "You must insert a song name";
    }

    if (!album) {
      if (errorToDisplay) {
        errorToDisplay += " and an album name";
      } else {
        errorToDisplay += "You must insert an album name";
      }
    }

    if (!artist) {
      if (errorToDisplay) {
        errorToDisplay += " and an artist name";
      } else {
        errorToDisplay += "You must insert an artist name";
      }
    }

    if (!mp3Link || !(mp3Link.type === "audio/mpeg")) {
      if (errorToDisplay) {
        errorToDisplay += " and a song file in mp3 format";
      } else {
        errorToDisplay += "You must insert a song file in mp3 format";
      }
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

    formData.append("name", data.name);
    formData.append("artist", data.artist);
    formData.append("album", data.album);
    formData.append("mp3Link", data.mp3Link[0]);
    formData.append("imgLink", data.imgLink[0]);

    if (validateSongUploadValues(formData)) {
      try {
        createSong(formData);
        alert("Song has been created successfully!");
      } catch (e) {
        alert("Something went wrong with creating the Song!");
      }
    }
  };

  return (
    <div className="d-flex flex-column align-items-center mt-5">
      <h2 className="mb-4">Upload a Song</h2>
      <form
        onSubmit={handleSubmit(onSubmit)}
        className="w-50"
        encType="multipart/form-data"
      >
        <div className="form-floating mb-3">
          <input
            type="text"
            className="form-control"
            id="name"
            name="name"
            placeholder="Song Name"
            {...register("name")}
          />
          <label htmlFor="name"> Song Name</label>
        </div>

        <div className="form-floating mb-3">
          <input
            type="text"
            className="form-control"
            id="album"
            name="album"
            placeholder="Album Name"
            {...register("album")}
          />
          <label htmlFor="album"> Album Name</label>
        </div>

        <div className="form-floating mb-3">
          <input
            type="text"
            className="form-control"
            id="artist"
            name="artist"
            placeholder="Artist Name"
            {...register("artist")}
          />
          <label htmlFor="artist"> Artist Name</label>
        </div>

        <div className="mb-3 d-flex flex-column justify-content-center">
          <label htmlFor="mp3Link" className="form-label">
            Choose a song file (mp3 format)
          </label>
          <input
            className="form-control"
            id="mp3Link"
            type="file"
            {...register("mp3Link")}
          />
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

        <button className="btn btn-primary px-5" type="submit">
          Upload Song
        </button>
      </form>
    </div>
  );
}

export default AddSongForm;
