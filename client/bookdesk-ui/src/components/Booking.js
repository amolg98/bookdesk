import React, { useState, useEffect } from 'react';
import UserService from '../services/user.service';

const Booking = () => {
    const [content, setContent] = useState("");

    useEffect(() => {
        UserService.getBooking().then(
            (response) => {
                setContent(response.data);
            },
            (error) => {
                const _content = 
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();
                setContent(_content);
            }
        );
    }, []);

    return(
        <div className="container">
            <header className="jumbotron">
                <p>{content}</p>
            </header>
        </div>
    );
};

export default Booking;