import { loginRequest } from "./loginAxios";
import jwt_decode from "jwt-decode";

const loginControllerPath = "/api/v1/auth/authenticate";

export async function loginUser(username, password) {
  const loginRequestBody = {
    email: username,
    password: password,
  };

  const response = await loginRequest(loginControllerPath, loginRequestBody);
  if (!response) {
    alert("Credentials are incorrect");
    return false;
  }

  const accessToken = response.data.token;

  sessionStorage.setItem("accessToken", accessToken);

  const decodedToken = jwt_decode(accessToken);
  sessionStorage.setItem("userId", decodedToken.userId);
  return true;
}
