import React from "react";
import { useState } from "react";
import useApi from '../Hooks/useApi';
function Signup(){

    const [name,setName]=useState('');
    const [email,setEmail]=useState('');
    const [user,setUser]=useState('');
    const [pass,setPass]=useState('');
    const { request } = useApi();


    const account = {
      name: name,
      username: user,
      email: email,
      password: pass,
    };

    const handleSubmit = async (e)=>{
    e.preventDefault();
       const result = await request("POST", 'https://pft-imqb.onrender.com/auth/register', account);
       if(result) {
        console.log("Account created successfully:", result);
        } else {
          alert("Failed to add account");
        }
        
    }
    return<>
    <h1 className="text-3xl font-bold text-center mt-10 mb-5">Signup</h1>
    <div className="border mb-4 rounded overflow-hidden max-w-sm mx-auto shadow-lg p-6">
            <form className="flex flex-col gap-4" onSubmit={handleSubmit}>
            <label htmlFor="Full Name" className="font-semibold">Name:</label>
                <input 
                    id="Name" 
                    type="text" 
                    autoFocus 
                    className="border rounded p-2"
                    placeholder="Enter full name"
                    value={name}
                    onChange={(e)=>{setName(e.target.value)}}
                />
                
                <label htmlFor="username" className="font-semibold">Username:</label>
                <input 
                    id="username" 
                    type="text" 
                    autoFocus 
                    className="border rounded p-2"
                    placeholder="Enter username"
                    value={user}
                    onChange={(e)=>{setUser(e.target.value)}}
                />
                <label htmlFor="email" className="font-semibold">E-mail:</label>
                <input 
                    id="email" 
                    type="email"
                    autoFocus 
                    className="border rounded p-2"
                    placeholder="Enter email"
                    value={email}
                    onChange={(e)=>{setEmail(e.target.value)}}
                />

                <label htmlFor="password" className="font-semibold">Password:</label>
                <input 
                    id="password" 
                    type="password" 
                    className="border rounded p-2"
                    placeholder="Enter password"
                    value={pass}
                    onChange={(e)=>{setPass(e.target.value)}}
                />

                <button 
                    type="submit" 
                    className="rounded bg-sky-500 hover:bg-sky-700 text-white py-2 mt-3 transition-all"
                    
                >
                    Sign up
                </button>
                <a href="/login">Already have an account? Log In</a>
            </form>
            {/* {user && <p className="text-green-600 font-semibold">Welcome {user}!</p>} */}
        </div>
        </>
}

export default Signup;