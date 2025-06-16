import {useNavigate} from 'react-router-dom'

import AppointmentForm from '../appointment/AppointmentForm'
import styles from './NewAppointment.module.css'

function NewAppointment(){

    const navigate = useNavigate()

    function createPost(appointment){

        appointment.cost = 0
        appointment.service = []

        fetch("http://localhost:5000/appointments", {
            method: 'POST',
            headers: {
                'Content-type': 'application/json',
            },
            body: JSON.stringify(appointment) 
        })
        .then((resp) => resp.json()) // Primeiro `.then()` processa a resposta
        .then((data) => { // Segundo `.then()` trata os dados
            console.log(data);
            navigate('/appointments', { state: { message: 'Projeto criado com sucesso!' } });
        })
        .catch(err => console.log(err)); // Captura erros
    }

    return (
        <div className={styles.newappointment_container}>
            <h1>Agendar Consulta</h1>
            <p> Agende sua consulta!</p>
            <AppointmentForm handleSubmit={createPost} btnText="Agendar Consulta"/>
        </div>
    )
}

export default NewAppointment