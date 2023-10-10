import { postTokenless } from "./UrlCRUD";

const controllerPath = "api/v1/auth";

export function createUser(username, email, password) {
  const requestBody = {
    username: username,
    password: password,
    email: email,
  };

  postTokenless(requestBody);
}
