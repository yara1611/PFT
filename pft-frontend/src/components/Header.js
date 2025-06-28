import React from "react";
import Navigation from "./Navigation";
import NavMenu from "./NavMenu";
import { useState } from "react";
function Header(props) {
     const [loggedIn, setLoggedIn]= useState(true);
    return (<>
    {/* items-center is the vertically centered */}
    <header className="border-b p-3 flex justify-between items-center flex-row h-16 w-screen">
    <span className="font-bold text-xl"> Personal Financer</span>
    <NavMenu loggedIn={loggedIn} setLoggedIn={setLoggedIn} />
    <span><img src="" alt="avatar"/></span>
        {/* <Navigation loggedIn={loggedIn} setLoggedIn={setLoggedIn}/> */}
    </header>
    </>)
}

export default Header