import {useNavigate} from 'react-router-dom'

import AppointmentForm from '../appointment/AppointmentForm'
import styles from './NewAppointment.module.css'

function NewAppointment(){

    const navigate = useNavigate()

    function createPost(appointment) {
        const { id, ...cleanAppointment } = appointment; 

        cleanAppointment.petId = Number(cleanAppointment.petId);
        cleanAppointment.serviceId = Number(cleanAppointment.serviceId);


        fetch('http://localhost:5000/appointments', {
            method: 'POST',
            headers: {
            'Content-type': 'application/json',
            },
            body: JSON.stringify(cleanAppointment),
        })
            .then((resp) => resp.json())
            .then((data) => {
            console.log(data);
            navigate('/appointments', {
                state: { message: 'Consulta criada com sucesso!' },
            });
            })
            .catch((err) => console.log(err));
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