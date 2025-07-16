import React from "react";

import NavMenu from "./NavMenu";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
function Header(props) {
    const navigate = useNavigate();
     const {loggedIn, setLoggedIn}= props;
     const handleClick=()=>{
        setLoggedIn(false);
        localStorage.removeItem('loggedIn');
        navigate('/login')
     }
    return (<>
    {/* items-center is the vertically centered */}
    <header className="border-b p-3 flex justify-between items-center flex-row h-[74px] w-full">
    <span className="font-bold text-xl"> Personal Financer</span>
    <NavMenu loggedIn={loggedIn} />
    <span><img src="" alt="avatar" onClick={handleClick}/></span>
        {/* <Navigation loggedIn={loggedIn} setLoggedIn={setLoggedIn}/> */}
    </header>
    </>)
}

export default Header