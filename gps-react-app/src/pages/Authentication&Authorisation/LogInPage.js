import React, { useState } from "react";
import "../../styles/Authentication&Authorisation/login.css";
import logo from '../../../src/images/ivanti-logo.svg';
function LogInPage({login}){

    const [usernameState, setUsernameState] =useState("");
    const [passwordState, setPasswordState] =useState("");

    const tryLogin = () => {
        login(usernameState, passwordState);
    }

    return (
        <div className='container'>
            <div className='login-container'>
                <div className='login-logo'>
                    <img src={logo}/>
                </div>
                <h2>Login</h2>
                <input className='login-input' type="username" placeholder="username"  value={usernameState} onChange={(event)=>{setUsernameState(event.target.value);}}/>

                <input className='login-input' type="password" placeholder="password" value={passwordState} onChange={(event)=>{setPasswordState(event.target.value)}}/>
                <button className='login-button' onClick={tryLogin}>Log In</button>
        </div>
        </div>
    );
   }
export default LogInPage;

