import { useParams, useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';

import AppointmentForm from '../appointment/AppointmentForm';
import styles from '../pages/NewAppointment.module.css';

function EditAppointment() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [appointment, setAppointment] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:5000/appointments/${id}`)
      .then((res) => res.json())
      .then((data) => {
        setAppointment(data);
      })
      .catch((err) => console.log(err));
  }, [id]);

  function updateAppointment(updatedData) {
    fetch(`http://localhost:5000/appointments/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(updatedData),
    })
      .then((res) => res.json())
      .then(() => {
        navigate('/appointments', { state: { message: 'Consulta atualizada com sucesso!' } });
      })
      .catch((err) => console.log(err));
  }

  return (
    <div className={styles.newappointment_container}>
      <h1>Editar Consulta</h1>
      <p>Altere as informações da consulta</p>
      {appointment && (
        <AppointmentForm
          handleSubmit={updateAppointment}
          btnText="Atualizar Consulta"
          appointmentData={appointment}
        />
      )}
      
    </div>
  );
}

export default EditAppointment;
