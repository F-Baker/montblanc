import axios from "axios";

const API_URL = "http://localhost:8080/mbe/";

class AuthService {

    // login service
    signin(email, password) {
        return axios
            .post(API_URL + "signin", {
                email: email,
                password: password
            })
            .then(response => {
                if (response.data.accessToken) {
                    localStorage.setItem("user", JSON.stringify(response.data));
                }
                console.log(response.data)
                return response.data;
            });
    }

    signout() {
        localStorage.removeItem("user");
    }

    // User registration service
    register(user) {
        return axios.post(API_URL + "signup", user);
    }

    // Update user data
    updateUser(user) {
        return axios
            .post(API_URL + "signup", {
                lastname: user.lastname,
                firstname: user.firstname
            })
            .then(response => {
                if (response.data.accessToken) {
                    localStorage.setItem("user", JSON.stringify(response.data));
                }
                return response.data;
            });
    }

    // Create faculty, e.g. "professor"
    createFaculty(faculty) {
        return axios
            .post(API_URL + "signup", {
                lastname: faculty.lastname,
                firstname: faculty.firstname
            })
            .then(response => {
                if (response.data.accessToken) {
                    //todo: verify that this "user" should be "user" and not "faculty"
                    localStorage.setItem("user", JSON.stringify(response.data));
                }
                return response.data;
            });
    }

    getCurrentUser() {
        return JSON.parse(localStorage.getItem('user'));
    }

    isUser() {
        return this.getCurrentUser() && this.getCurrentUser().roles && this.getCurrentUser().roles.includes("ROLE_STUDENT");
    }

    isAdmin() {
        return this.getCurrentUser() && this.getCurrentUser().roles && this.getCurrentUser().roles.includes("ROLE_ADMIN");
    }
}

export default new AuthService();