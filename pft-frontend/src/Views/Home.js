import React from "react";

function Home(){

    const user = JSON.parse(localStorage.getItem('user'));

    return<>
    <p>Current user: {user}</p>
    <h1 className="text-3xl font-bold text-center mt-10">Welcome to Personal Financer Dashboard</h1>
    </>
    
}

export default Home;