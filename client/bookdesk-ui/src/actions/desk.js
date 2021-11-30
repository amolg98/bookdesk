import DeskService from "../services/desk.service";

export const desk = (bookingParam) => (dispatch) => {
    return DeskService.getDesk().then(
        (response) => {
            dispatch({
                type: ""
            });

            dispatch({
                type: "",
                payload: response.data.message,
            });

            return Promise.resolve();
        },
        (error) => {
            const message = 
                (error.response && 
                    error.response.data &&
                    error.response.data.message) ||
                error.message ||
                error.toString();

            dispatch({
                type: "",
            });

            dispatch({
                type: "",
                payload: message,
            });

            return Promise.reject();
        }
    );
};
