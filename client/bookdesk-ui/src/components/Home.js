import React from "react";
import Desk from "../components/Desk";

import '../App.css';

const Home = (props) => {

    // const [loading, setLoading] = useState(false);
    
    return(
        <div className="container">
            <header className="jombotron">
                <h3>Book Desk</h3>
            </header>
            <div>
                <Desk />
            </div>
        </div>
    );
};

export default Home;