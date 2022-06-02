import React, {useEffect, useState} from 'react'
import {Link, Route, Routes} from "react-router-dom";
import logo from "../images/ivanti-marketplace-logo.png";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faGlobe, faCaretDown } from '@fortawesome/free-solid-svg-icons'
import HomePage from "../pages/ContentCreator/HomePage";
import LogOutPage from "../pages/Authentication&Authorisation/LogOutPage";
import LogInPage from "../pages/Authentication&Authorisation/LogInPage";
import AboutPage from "../pages/Miscellaneous/AboutPage";
import ContactPage from "../pages/Miscellaneous/ContactPage";
import UpdateApplicationPage from "../pages/ContentCreator/UpdateApplicationPage";
import AddApplicationPage from "../pages/ContentCreator/AddApplicationPage";
import ApplicationPage from "../pages/ContentCreator/ApplicationPage/ApplicationPage";
import AllApplicationsPage from '../pages/Customer/AllApplications/AllApplicationsPage';
import ApplicationDetailedPage from '../pages/Customer/ApplicationDetails/ApplicationDetailedPage';
import MyAppsPage from "../pages/ContentCreator/MyAppsPage/MyAppsPage";
import AddMinorVersionPage from "../pages/ContentCreator/AddMinorVersionPage";
import AddMajorVersionPage from "../pages/ContentCreator/AddMajorVersionPage";
import ErrorPage from "../pages/Authentication&Authorisation/ErrorPage";
import AnalyticsPage from "../pages/ContentCreator/AnalyticsPage";
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
                        <Link className='NavLink' to="/all-apps">All Apps</Link>
                        <div className="NavTranslate">Translate<FontAwesomeIcon className="NavIcon" icon={faGlobe} /></div>
                        <div className="NavDropdown">
                            <button className="dropdown-button" onClick={showContent}>{localStorage.getItem("username")}<FontAwesomeIcon className="NavIcon" icon={faCaretDown} /></button>
                            <div className="dropdown-content" id="myDropdown">
                                <Link to="/customer/1">My Account</Link>
                                <hr/>
                                <Link to="/" onClick={logout}>Logout</Link>
                            </div>
                        </div>
                    </div>

                    <div className="Body">
                        <Routes>
                            <Route path="/" element={<HomePage/>} />
                            <Route path="/about" element={<AboutPage/>} />
                            <Route path="/contact" element={<ContactPage/>} />
                            <Route path='/all-apps' element={<AllApplicationsPage />} />
                            <Route path='/app/:name' element={<ApplicationDetailedPage />} />
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
                        <Link className='NavLink' to="/all-apps">All Apps</Link>
                        <Link className="NavLink" to="/creator/analytics">Analytics</Link>
                        <Link className="NavLink" to="/creator/1/notifications">Notifications</Link>
                        <Link className="NavLink" to="/my-account/creator">my Apps</Link>
                        <div className="NavTranslate">Translate<FontAwesomeIcon className="NavIcon" icon={faGlobe} /></div>
                        <div className="NavDropdown">
                            <button className="dropdown-button" onClick={showContent}>{localStorage.getItem("username")}<FontAwesomeIcon className="NavIcon" icon={faCaretDown} /></button>
                            <div className="dropdown-content" id="myDropdown">
                                <Link to="/creator/1">My Account</Link>
                                <hr/>
                                <Link to="/" onClick={logout}>Logout</Link>
                            </div>
                        </div>
                    </div>

                    <div className="Body">
                        <Routes>
                            <Route path="/" element={<HomePage/>} />
                            <Route path="/about" element={<AboutPage/>} />
                            <Route path="/contact" element={<ContactPage/>} />

                            <Route path="/creator/:id/myApps/:name/updateApplication" element={<UpdateApplicationPage />}/>
                            <Route path="/creator/:id/myApps/addApplication" element={<AddApplicationPage/>}/>
                            <Route path="/creator/:id/myApps/:name" element={<ApplicationPage />} />
                            <Route path='/app/:name' element={<ApplicationDetailedPage />} />
                            <Route path='/all-apps' element={<AllApplicationsPage />} />
                            <Route path='/app/:name' element={<ApplicationDetailedPage />} />
                            <Route path="/my-account/:username" element={<MyAppsPage />}/>
                            <Route path="/creator/:id/myApps/:appName/updateApplication" element={<UpdateApplicationPage />}/>
                            <Route path="/creator/:id/myApps/addApplication" element={<AddApplicationPage/>}/>
                            <Route path="/creator/:id/myApps/:appName" element={<ApplicationPage />} />
                            <Route path='/creator/:id/myApps/:name/addMinorVersion' element={<AddMinorVersionPage />} />
                            <Route path='/creator/:id/myApps/:name/addMajorVersion' element={<AddMajorVersionPage />} />
                            <Route path="/login" element={<LogInPage login={login}/>} />
                            <Route path="/*" element={<ErrorPage />} />
                            <Route path="/creator/myApps/:name" element={<ApplicationPage />} />
                            <Route path="/creator/analytics" element={<AnalyticsPage/>}/>
                        </Routes>
                    </div>
                </>
            ):(
                <>
                    <div className="Nav">
                        <Link className="NavLogo" to="/">
                            <img src={logo} height={"38px"} alt={"ivanti marketplace logo"}/>
                        </Link>
                        <Link className='NavLink' to="/all-apps">All Apps</Link>
                        <div className="NavTranslate">Translate<FontAwesomeIcon className="NavIcon" icon={faGlobe} /></div>
                 
                                <Link className="NavLink" to="/login">Login</Link>
                      
                    </div>

                    <div className="Body">
                        <Routes>
                            <Route path="/" element={<HomePage/>} />
                            <Route path="/about" element={<AboutPage/>} />
                            <Route path="/contact" element={<ContactPage/>} />
                            <Route path='/all-apps' element={<AllApplicationsPage />} />
                            <Route path='/app/:name' element={<ApplicationDetailedPage />} />
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
