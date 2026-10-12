import axios from "axios";

const API_URL = "http://localhost:8081/api/auth/login";

export const LoginService = async (username, password) => {

    try {
        const response = await axios.post(API_URL, {
            username,
            password
        });
    } catch (error) {
        console.error("Error during login:", error);
    }

    

    console.log(response);

    const token = response.headers["authorization"];

    if (token) {
        localStorage.setItem("token", token);
    }

    return token;

}