import Axios from "axios";
const httpClient = Axios.create({baseURL: "http://localhost:8080/mbe/"});

httpClient.interceptors.request.use(
    (config) => {
        config.headers["content-type"] = "application/json";
        return config;
    },
    (error) => {
        Promise.reject(error);
    }
);
export default httpClient;