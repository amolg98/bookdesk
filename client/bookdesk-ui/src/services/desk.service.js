import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/api/";

const getDesk = (bookingParam) => {
    // console.log("booking param is ", bookingParam);
    return axios.post(API_URL + "desk/date", bookingParam, { 
        headers: authHeader()
    })
    .then((response) => {
        console.log("response data in desks api is ", response.data);
        return response;
    });

    // async conversion
    // const response = await axios.get(API_URL + "desk", bookingParam, {
    //     headers: authHeader()
    // });
    // console.log("response data in desks api is ", response.data);
    // return response.data;
};

// eslint-disable-next-line import/no-anonymous-default-export
export default {
    getDesk
};