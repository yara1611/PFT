import './App.css';
import Header from './components/Header';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './Views/Home';
import Footer from './components/Footer';

function App() {
  return (<div className="relative pb-10 min-h-screen">

    <Router>
      <Header/>
      <div className="p-3">
        <Routes>
          <Route path="/" element={<Home/>} />
          {/* <Route path="/signup" element={<Signup/>} />
          <Route path="/login" element={<Login/>} />
          <Route path="/profile" element={<Profile/>} />
          <Route path="/about" element={<About />} />
          <Route path="/contact" element={<Contact/>} /> */}
        </Routes>
      </div>
      <Footer />
    </Router>

  </div>
  );
}

export default App;
