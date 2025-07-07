import React from "react";

function Foot(){

    return <>
    <footer className="bg-black p-3 h-max w-full">
    <span className="font-bold text-xl text-white"> Personal Financer</span>
    <li className="text-white">link 1</li>
    <li className="text-white">link 2</li>
    <li className="text-white">link 3</li>
        {/* <Navigation loggedIn={loggedIn} setLoggedIn={setLoggedIn}/> */}
    </footer>
    </>
}
export default Foot;