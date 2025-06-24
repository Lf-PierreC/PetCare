import { useEffect, useState } from 'react';
import styles from './AppointmentForm.module.css';

import Input from '../Form/Input';
import Select from '../Form/Select';
import SubmitButton from '../Form/SubmitButton';

function AppointmentForm({ handleSubmit, btnText, appointmentData }) {
  const [appointment, setAppointment] = useState({});
  const [services, setServices] = useState([]);
  const [pets, setPets] = useState([]);

  useEffect(() => {
    fetch('http://localhost:5000/services')
      .then((res) => res.json())
      .then((data) => setServices(data))
      .catch((err) => console.error(err));

    fetch('http://localhost:5000/pets')
      .then((res) => res.json())
      .then((data) => setPets(data))
      .catch((err) => console.error(err));
  }, []);

        useEffect(() => {
        if (appointmentData) {
            setAppointment(appointmentData);
        }
    }, [appointmentData]);

  const submit = (e) => {
    e.preventDefault();
    handleSubmit(appointment);
  };

  const handleChange = (e) => {
    setAppointment({ ...appointment, [e.target.name]: e.target.value });
  };

  return (
    <form onSubmit={submit} className={styles.form}>
      <Input
            type="datetime-local"
            text="Data e hora da consulta"
            name="date"
            handleOnChange={handleChange}
            value={
                appointment.date
                ? new Date(appointment.date).toISOString().slice(0, 16)
                : ''
            }
        />


      <Select
        name="serviceId"
        text="Selecione o serviço"
        options={services}
        handleOnChange={handleChange}
        value={appointment.serviceId || ''}
      />

      <Select
        name="petId"
        text="Selecione o pet"
        options={pets}
        handleOnChange={handleChange}
        value={appointment.petId || ''}
      />

      <SubmitButton text={btnText} />
    </form>
  );
}

export default AppointmentForm;
