import React, {useEffect, useState} from 'react'
import {Link, Route, Routes} from "react-router-dom";
import logo from "../images/ivanti-marketplace-logo.png";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faGlobe, faCaretDown } from '@fortawesome/free-solid-svg-icons'
import HomePage_creator from "../pages/ContentCreator/HomePage_creator";
import LogOutPage from "../pages/Authentication&Authorisation/LogOutPage";
import LogInPage from "../pages/Authentication&Authorisation/LogInPage";
import AboutPage from "../pages/Miscellaneous/AboutPage";
import UpdateApplication_creator from "../pages/ContentCreator/AppsManagement/UpdateApplication_creator";
import AddApp_creator from "../pages/ContentCreator/AppsManagement/AddApp_creator";
import AppDetailed_creator from "../pages/ContentCreator/AppDetailed_creator";
import AppsPage from '../pages/Customer/AppsPage';
import AppDetailed from '../pages/Customer/AppDetailed';
import MyApps_creator from "../pages/ContentCreator/MyAppsPage/MyApps_creator";
import AddMinorVersion_creator from "../pages/ContentCreator/AppsManagement/AddMinorVersion_creator";
import AddMajorVersion_creator from "../pages/ContentCreator/AppsManagement/AddMajorVersion_creator";
import ErrorPage from "../pages/Authentication&Authorisation/ErrorPage";
import AnalyticsPage from "../pages/ContentCreator/Analytics/AnalyticsPage";
import axios from "axios";

function Navbar() {
    function showContent() {
        document.getElementById("myDropdown").classList.toggle("show");
    }
    window.onclick = function(e) {
        if (!e.target.matches('.dropdown-button')) {
            let myDropdown = document.getElementById("myDropdown");
            if (myDropdown.classList.contains('show')) {
                myDropdown.classList.remove('show');
            }
        }
    }

    const [authorization, setAuthorization] = useState("");
    const [username, setUsername] = useState("");
    const login = (username, password) => {
        axios.post(`http://localhost:8080/login`,{
            "username":username,
            "password":password
        })
            .then(res => {
                localStorage.setItem("token", res.data.accessToken);
                localStorage.setItem("authorization", res.data.permission);
                localStorage.setItem('username', username);
                setAuthorization(res.data.permission);
                setUsername(res.data.username)
                console.log(authorization)

            })
            .catch(err => {});
    }
    const logout =()=>{
        localStorage.removeItem("token");
        localStorage.clear()
        setAuthorization("");
    }

    return(
        <>
            {(authorization === "Customer")?(
                <>
                    <div className="Nav">
                        <Link className="NavLogo" to="/">
                            <img src={logo} height={"38px"} alt={"ivanti marketplace logo"}/>
                        </Link>
                        <Link className='NavLink' to="/about">About us</Link>
                        <Link className='NavLink' to="/customerApps">Apps</Link>
                        <div className="NavTranslate">Translate<FontAwesomeIcon className="NavIcon" icon={faGlobe} /></div>
                        <div className="NavDropdown">
                            <button className="dropdown-button" onClick={showContent}>{localStorage.getItem("username")}<FontAwesomeIcon className="NavIcon" icon={faCaretDown} /></button>
                            <div className="dropdown-content" id="myDropdown">
                                <Link className='NavLink' to="/downloads/:userId">My Downloads</Link> /*TODO*/
                                <hr/>
                                <Link to="/" onClick={logout}>Logout</Link>
                            </div>
                        </div>
                    </div>

                    <div className="Body">
                        <Routes>
                            <Route path="/" element={<HomePage_creator/>} />
                            <Route path="/about" element={<AboutPage/>} />
                            <Route path='/customerApps' element={<AppsPage />} />
                            <Route path='/app/:name' element={<AppDetailed />} />
                            <Route path="/logout" element={<LogOutPage logout={logout}/>} />
                            <Route path="/login" element={<LogInPage login={login}/>} />
                            <Route path="/*" element={<ErrorPage />} />
                        </Routes>
                    </div>
                </>
            ):(authorization === "Creator")?(
                <>
                    <div className="Nav">
                        <Link className="NavLogo" to="/">
                            <img src={logo} height={"38px"} alt={"ivanti marketplace logo"}/>
                        </Link>
                        <Link className='NavLink' to="/apps">Apps</Link>

                        <Link className="NavLink" to="/notifications/:userId">Notifications</Link> /*TODO*/
                        <Link className="NavLink" to="/creator/analytics">Analytics</Link>  /*TODO*/
                        <Link className="NavLink" to="/about">About us</Link>
                        <div className="NavTranslate">Translate<FontAwesomeIcon className="NavIcon" icon={faGlobe} /></div>
                        <div className="NavDropdown">
                            <button className="dropdown-button" onClick={showContent}>{localStorage.getItem("username")}<FontAwesomeIcon className="NavIcon" icon={faCaretDown} /></button>
                            <div className="dropdown-content" id="myDropdown">
                                <Link className="NavLink" to="/myApps/creator">My apps</Link>
                                <hr/>
                                <Link to="/" onClick={logout}>Logout</Link>
                            </div>
                        </div>
                    </div>

                    <div className="Body">
                        <Routes>
                            <Route path="/creator/1/myApps/:appName" element={<AppDetailed_creator />} />

                            <Route path="/" element={<HomePage_creator/>} />
                            <Route path="/about" element={<AboutPage/>} />
                            <Route path='/apps' element={<AppsPage />} />
                            <Route path='/app/:name' element={<AppDetailed />} />
                            <Route path="/creator/myApps/:name" element={<AppDetailed_creator />} />
                            <Route path="/myApps/:username" element={<MyApps_creator />}/>
                            <Route path="/creator/:id/myApps/:name/updateApplication" element={<UpdateApplication_creator />}/>
                            <Route path="/creator/:id/myApps/addApplication" element={<AddApp_creator/>}/>
                            <Route path='/creator/:id/myApps/:name/addMinorVersion' element={<AddMinorVersion_creator />} />
                            <Route path='/creator/:id/myApps/:name/addMajorVersion' element={<AddMajorVersion_creator />} />
                            <Route path="/creator/analytics" element={<AnalyticsPage/>}/>
                            <Route path="/login" element={<LogInPage login={login}/>} />
                            <Route path="/*" element={<ErrorPage />} />

                            {/*}   <Route path="/creator/:id/myApps/:appName/updateApplication" element={<UpdateApplication_creator />}/>
                            <Route path="/creator/:id/myApps/:appName" element={<AppDetailed_creator />} />
                             <Route path="/creator/:id/myApps/:name" element={<AppDetailed_creator />} />*/}
                        </Routes>
                    </div>
                </>
            ):(
                <>
                    <div className="Nav">
                        <Link className="NavLogo" to="/">
                            <img src={logo} height={"38px"} alt={"ivanti marketplace logo"}/>
                        </Link>
                        <Link className='NavLink' to="/apps">All Apps</Link>
                        <Link className='NavLink' to="/about">About us</Link>
                        <div className="NavTranslate">Translate<FontAwesomeIcon className="NavIcon" icon={faGlobe} /></div>
                        <Link className="NavLink" to="/login">Login</Link>
                    </div>

                    <div className="Body">
                        <Routes>
                            <Route path="/" element={<HomePage_creator/>} />
                            <Route path="/about" element={<AboutPage/>} />
                            <Route path='/apps' element={<AppsPage />} />
                            <Route path='/app/:name' element={<AppDetailed />} />
                            <Route path="/login" element={<LogInPage login={login}/>} />
                            <Route path="/*" element={<ErrorPage />} />
                        </Routes>
                    </div>
                </>
            )}
        </>
    );
}


export default Navbar;
