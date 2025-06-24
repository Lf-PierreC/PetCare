import { useLocation } from 'react-router-dom';
import { useState, useEffect } from 'react';

import Message from '../layout/Message';
import Container from '../layout/Container';
import LinkButton from '../layout/LinkButton';
import AppointmentCard from '../appointment/AppointmentCard';

import styles from './Appointments.module.css';

function Appointments() {
  const [appointments, setAppointments] = useState([]);
  const [pets, setPets] = useState([]);
  const [services, setServices] = useState([]);

  const location = useLocation();
  let message = '';
  if (location.state) {
    message = location.state.message;
  }

  // Função para buscar os dados e atualizar o estado
  function fetchAppointments() {
    Promise.all([
      fetch('http://localhost:5000/appointments').then(res => res.json()),
      fetch('http://localhost:5000/pets').then(res => res.json()),
      fetch('http://localhost:5000/services').then(res => res.json()),
    ])
      .then(([apptData, petsData, servicesData]) => {
        setPets(petsData);
        setServices(servicesData);

        const formatted = apptData.map(appt => {
          const pet = petsData.find(p => Number(p.id) === Number(appt.petId));
          const service = servicesData.find(s => Number(s.id) === Number(appt.serviceId));

          return {
            ...appt,
            petName: pet ? pet.name : 'Pet não encontrado',
            serviceName: service ? service.name : 'Serviço não encontrado',
          };
        });

        setAppointments(formatted);
      })
      .catch(console.error);
  }

  // Carrega ao montar
  useEffect(() => {
    fetchAppointments();
  }, []);

  // Remove e atualiza a lista
  function removeAppointment(id) {
    const numericId = Number(id);
    fetch(`http://localhost:5000/appointments/${numericId}`, {
      method: 'DELETE',
    })
      .then((resp) => {
        if (!resp.ok) throw new Error('Erro ao deletar');
        fetchAppointments(); // atualiza lista após exclusão
      })
      .catch(console.error);
  }

  return (
    <div className={styles.appointment_container}>
      <div className={styles.title_container}>
        <h1>Minhas Consultas</h1>
        <LinkButton to="/newappointment" text="Agendar Consulta" />
      </div>
      {message && <Message type="success" msg={message} />}
      <Container customClass="start">
        {appointments.length > 0 &&
          appointments.map((appointment) => (
            <AppointmentCard
              key={appointment.id}
              id={appointment.id}
              date={appointment.date}
              petName={appointment.petName}
              serviceName={appointment.serviceName}
              handleRemove={removeAppointment}
            />
          ))}
      </Container>
    </div>
  );
}

export default Appointments;
