import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/api/";

const getRoom = () => {
    return axios.get(API_URL + "room", { headers: authHeader() });
};

const getPublicContent = () => {
    return axios.get(API_URL + "test/all");
};

const getUserBoard = () => {
    return axios.get(API_URL + "test/user", { headers: authHeader() });
};

const getAdminBoard = () => {
    return axios.get(API_URL + "test/admin", { headers: authHeader() });
};

// eslint-disable-next-line import/no-anonymous-default-export
export default {
    getRoom,
    getPublicContent,
    getUserBoard,
    getAdminBoard
}