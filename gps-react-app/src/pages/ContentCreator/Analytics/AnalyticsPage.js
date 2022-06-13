import React, {useEffect, useState} from "react";
import axios from "axios";
import "../../../styles/ContentCreator/AnalyticsPage.css";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCaretDown} from "@fortawesome/free-solid-svg-icons";
import Analytics from "./component/Analytics";

function AnalyticsPage() {
    const [applications, setApplications] = useState([]);
    let username = localStorage.getItem("username");

    useEffect(() => {
        getAllApplications();
    },[applications]);

    const getAllApplications =() => {
        axios.get(`http://localhost:8080/user/`+username+`/statistics?year=`+2021)
            .then(res => {
                setApplications(res.data);
            })
            .catch(err => {
                console.log(err.message);
            });
    }

    return (
        <div className={"analytics"}>
            <div className={"analytics-controls"}>
                <h1 className={"title"}>Analytics</h1>
                <input className={"search-field"} type="text"  placeholder="Search"/>
                <div className={"year-dropdown"}>
                    <select>
                        <option value="2022">2022</option>
                        <option value="2021">2021</option>
                    </select>
                    <FontAwesomeIcon className="dropdown-icon" icon={faCaretDown} />
                </div>
            </div>
            <hr/>
            <Analytics applications={applications} />
        </div>
    );
}
export default AnalyticsPage;
