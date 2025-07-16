import React from "react";
import { useState } from "react";

import { useNavigate } from 'react-router-dom';


function Login({setLoggedIn,loggedIn}){
    const [username, setUsername] = useState('');
    const [pass, setPass] = useState('');
    const navigate = useNavigate();
    const handleSubmit = (e)=>{
        //remove this fel akher
        console.log(username,pass)
        setLoggedIn(true)
        navigate('/');
        localStorage.setItem('loggedIn',JSON.stringify(loggedIn))
    }

    return<>
    <h1 className="text-3xl font-bold text-center mt-10 mb-5">Log In</h1>
    <div className="border mb-4 rounded overflow-hidden max-w-sm mx-auto shadow-lg p-6">

    <form className="flex flex-col gap-4" onSubmit={handleSubmit}>
    <label htmlFor="username">Username:</label>
    <input
        id="username"
        required
        type="text"
        autoFocus
        className="border rounded p-2"
        placeholder="Enter username"
        onChange={(e)=>{setUsername(e.target.value)}}
    />
    <label htmlFor="password">Password:</label>
    <input
        id="password"
        required
        type="password"
        autoFocus
        className="border rounded p-2"
        placeholder="Enter password"
        onChange={(e)=>{setPass(e.target.value)}}
    />
    <button type="submit">Log in</button>
    <a href="/signup">Don't have and Account? Sign Up</a>
    </form>
    </div>
    </>
    
}

export default Login;