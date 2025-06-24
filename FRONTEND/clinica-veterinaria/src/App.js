import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Home from './components/pages/Home';
import Appointments from './components/pages/Appointments'
import NewAppointment from './components/pages/NewAppointment';
import EditAppointment from './components/appointment/EditAppointment';
import Customer from './components/pages/Customer';
import NewCustomer from './components/pages/NewCustomer';
import EditCustomer from './components/pages/EditCustomer';

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
            <Route path="/newappointment" element={<NewAppointment/>} />
            <Route path="/editappointment/:id" element={<EditAppointment />} />
            <Route path="/customers" element={<Customer />} />
            <Route path="/newcustomer" element={<NewCustomer />} />
            <Route path="/editcustomer/:id" element={<EditCustomer />} />
          </Routes>
        </Container>
        <Footer />
      </div>
    </Router>
  );
}


export default App;
