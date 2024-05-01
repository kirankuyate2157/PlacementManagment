import axios from "axios";

export const createUser = async ({ username, password, type }) => {
  const url = "https://kplace.onrender.com/user/add";

  const userData = {
    name: username,
    type: type || "ADMIN",
    password: password,
  };

  try {
    const response = await axios.post(url, userData);
    return response;
  } catch (error) {
    console.log("Error creating user:", error.message);
    return error.message
  }
};

export const LoginUser = async ({ username, password}) => {
    const url = "https://kplace.onrender.com/users/login";
  
    const userData = {
      name: username,
      password: password,
    };
  
    try {
      const response = await axios.post(url, userData);
      return response;
    } catch (error) {
      console.log("Error Login user:", error.message);
      return error.message
    }
  };
