import axios from "axios";

const defaultPath = "http://localhost:8080";

export const loginRequest = async (loginControllerPath, loginRequestBody) => {
  try {
    const accessToken = await axios.post(
      defaultPath + loginControllerPath,
      loginRequestBody
    );

    return accessToken;
  } catch (err) {
    console.log(err);
    return null;
  }
};
