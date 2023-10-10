import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginUser } from "../login/loginLogic";

function LoginForm(props) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  async function handleFormSubmit(e) {
    e.preventDefault();

    var errorToDisplay = "";
    if (!username) {
      errorToDisplay += "You must enter a username";
    }

    if (!password) {
      if (!errorToDisplay) {
        errorToDisplay += "You must enter a password";
      } else {
        errorToDisplay += " and a password";
      }
    }

    if (errorToDisplay) {
      alert(errorToDisplay);
      return;
    }

    const loginOK = await loginUser(username, password);

    if (loginOK) {
      alert("Succesfully logged in!");
      navigate("/home");
    }
  }

  function usernameTextChanged(e) {
    setUsername(e.target.value);
  }

  function passwordTextChanged(e) {
    setPassword(e.target.value);
  }

  return (
    <form onSubmit={handleFormSubmit}>
      <div className="form-floating mb-3 text-start">
        <input
          type="username"
          className="form-control"
          id="username"
          name="username"
          placeholder="Email"
          value={username}
          onChange={usernameTextChanged}
        />
        <label htmlFor="floatingInput"> Email</label>
      </div>

      <div className="form-floating mb-3 text-start">
        <input
          type="password"
          className="form-control"
          id="password"
          name="password"
          placeholder="Password"
          value={password}
          onChange={passwordTextChanged}
        />
        <label htmlFor="floatingPassword"> Password</label>
      </div>

      <p className="small mb-4 pb-lg-2">
        <a href="/register">Create an account instead?</a>
      </p>

      <button className="btn btn-outline-light px-5" type="submit" href="\home">
        Login
      </button>
    </form>
  );
}

export default LoginForm;
