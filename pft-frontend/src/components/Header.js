import NavMenu from "./NavMenu";
import { useNavigate } from "react-router-dom";
import { useState } from "react";

//header returns to loggedout state when refreshed
//make an avatar component
//make a dropdown menu for avatar
//Restyle the header
function Header() {
    const navigate = useNavigate();
    const [loggedIn, setLoggedIn] = useState();
     const handleClick=()=>{
        setLoggedIn(false);
        localStorage.removeItem('loggedIn');
        localStorage.removeItem('user');
        navigate('/login')
     }
    return (<>
    {/* items-center is the vertically centered */}
    <header className="border-b p-3 flex justify-between items-center flex-row h-[74px] w-full">
    <span className="font-bold text-xl"> Personal Financer</span>
    <NavMenu/>
    <span><img src="" alt="avatar" onClick={handleClick}/></span>
        {/* <Navigation loggedIn={loggedIn} setLoggedIn={setLoggedIn}/> */}
    </header>
    </>)
}

export default Header