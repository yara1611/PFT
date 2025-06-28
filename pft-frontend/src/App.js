import './App.css';
import Header from './components/Header';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './Views/Home';
import About from './Views/About';
import Signup from './Views/Signup';
import Login from './Views/Login';
import Footer from './components/Footer';
import Accounts from './Views/Accounts';
import Records from './Views/Records';

function App() {
  return (<div className="relative pb-10 min-h-screen">

    <Router>
      <Header/>
      <div className="p-3 bg-zinc-100">
        <Routes>
          <Route path="/" element={<Home/>} />
          <Route path="/signup" element={<Signup/>} />
          <Route path="/login" element={<Login/>} />
          <Route path="/accounts" element={<Accounts/>} />
          <Route path="/records" element={<Records/>} />
        {/* <Route path="/profile" element={<Profile/>} /> */}
          <Route path="/about" element={<About/>} />
        </Routes>
      </div>
      
    </Router>

  </div>
  );
}

export default App;
