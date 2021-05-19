import jwt_decode from 'jwt-decode';
import {AUTH_RESET} from "../redux/types/AUTH_TYPES";

export const getUserDetails = (token) => {
    const {user} = jwt_decode(token);
    const currentRole = user.roles.map(role => role.role === "ADMIN" ? "ADMIN" : "USER");

    console.log({role: currentRole[0], user: user});
    return {role: currentRole[0], user: user};
};

export const storeToken = (token) => {
    localStorage.setItem("token", token);
};

export const removeToken = () => dispatch => {
    localStorage.removeItem("token");
    dispatch({type: AUTH_RESET});
};