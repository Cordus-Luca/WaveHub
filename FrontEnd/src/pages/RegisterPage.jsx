import React from "react";
import "./styles.css";
import { Link } from "react-router-dom";
import RegisterForm from "../forms/RegisterForm";

function RegisterPage(props) {
  return (
    <div className="">
      <div className="form-box">
        <h2 className="">Please register a new account</h2>
        <h3 className="">Sign up</h3>
        <p className="">Please enter your credentials!</p>
        <RegisterForm />
      </div>
    </div>
  );
}

export default RegisterPage;
