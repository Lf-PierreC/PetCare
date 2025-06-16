import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Home from './components/pages/Home';
import Appointments from './components/pages/Appointments'
import Company from './components/pages/Company';
import Contact from './components/pages/Contact';
import NewAppointment from './components/pages/NewAppointment';

import Container from './components/layout/Container'; 
import Navbar from './components/layout/Navbar'
import Footer from './components/layout/Footer'

function App() {
  return (
    <Router>
      <div className="app-container">
        <Navbar />
        <Container customClass="min-height">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/appointment" element={<Appointments/>} />
            <Route path="/company" element={<Company />} />
            <Route path="/contact" element={<Contact />} />
            <Route path="/newappointment" element={<NewAppointment/>} />
          </Routes>
        </Container>
        <Footer />
      </div>
    </Router>
  );
}


export default App;
