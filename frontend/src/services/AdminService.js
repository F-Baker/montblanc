import axios from "axios";
import authHeader from "../auth/AuthHeader";

const API_URL = "http://localhost:8080/mbe/";

class AdminService {

    // get students
    getStudents() {
        return axios
            .get(API_URL + "admin/students", {headers: authHeader()});
    }

    // get status
    getEnrollmentStatus() {
        return axios
            .get(API_URL + "admin/enrollment-status", {headers: authHeader()});
    }

    // change status
    changeStatus(studentId, statusId) {
        return axios
            .post(API_URL + "admin/students/edit/status", {studentId, statusId}, {headers: authHeader()});
    }
}

export default new AdminService();