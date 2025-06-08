import React from "react";
import Navigation from "./Navigation";
import NavMenu from "./NavMenu";
function Header(props) {
    // let {loggedIn, setLoggedIn}= props;
    return (<>
    {/* items-center is the vertically centered */}
    <header className="border-b p-3 flex justify-between items-center flex-row">
    <span className="font-bold text-xl"> Personal Financer</span>
    <NavMenu />
    <span><img src="" alt="avatar"/></span>
        {/* <Navigation loggedIn={loggedIn} setLoggedIn={setLoggedIn}/> */}
    </header>
    </>)
}

export default Header