import './App.css';
import { useState } from 'react';
import Header from './components/Header';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './Views/Home';
import About from './Views/About';
import Signup from './Views/Signup';
import Login from './Views/Login';
import Accounts from './Views/Accounts';
import Records from './Views/Records';
import Foot from './components/Footer';

function App() {
  const [loggedIn, setLoggedIn] = useState(false);
  return (<div className="relative pb-10 min-h-screen">

    <Router>
      <Header loggedIn={loggedIn} setLoggedIn={setLoggedIn}/>
      <div className="p-3 bg-zinc-100 h-screen">
        <Routes>
          <Route path="/" element={<Home/>} />
          <Route path="/signup" element={<Signup/>} />
          <Route path="/login" element={<Login setLoggedIn={setLoggedIn} loggedIn={loggedIn}/>} />
          <Route path="/accounts" element={<Accounts/>} />
          <Route path="/records" element={<Records/>} />
        {/* <Route path="/profile" element={<Profile/>} /> */}
          <Route path="/about" element={<About/>} />
        </Routes>
      </div>
      
    </Router>
    <Foot></Foot>
  </div>
  );
}

export default App;
