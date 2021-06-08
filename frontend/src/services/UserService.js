import axios from "axios";
import authHeader from "../auth/AuthHeader";

const API_URL = "http://localhost:8080/mbe/";

class UserService {
    // get Students
    getStudentById(email) {
        return axios
            .get(API_URL + "user?email="+email, { headers: authHeader() });
    }
}

export default new UserService();