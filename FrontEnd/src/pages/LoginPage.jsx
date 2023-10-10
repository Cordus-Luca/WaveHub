import React from "react";
import LoginForm from "../forms/LoginForm";
import "./styles.css";

function LoginPage(props) {
  return (
    <div className="form-box">
      <div className="">
        <h2 className="">Please Login to your account</h2>
        <h3 className="">Login</h3>
        <p className="">Please enter your credentials!</p>
        <LoginForm />
      </div>
    </div>
  );
}

export default LoginPage;
