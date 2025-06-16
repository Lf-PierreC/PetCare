import { useLocation } from 'react-router-dom'

import { useState, useEffect } from 'react'

import Message from '../layout/Message'
import Container from '../layout/Container'
import LinkButton from '../layout/LinkButton'
import AppointmentCard from '../appointment/AppointmentCard'

import styles from './Appointments.module.css'

function Appointments(){

    const [appointments, setAppointments] = useState([])

    const location = useLocation()
    let message = ""
    if(location.state){
        message = location.state.message
    }

    useEffect(() => {

        fetch('http://localhost:5000/appointments', {
            method: 'GET',
            headers: {
                'Content-Type':'application/json',
            },
        }).then((resp) => resp.json())
        .then((data) => {
            console.log(data)
            setAppointments(data)
        })
        .catch(err => console.log(err))
    }, [])

    return (
        <div className={styles.appointment_container}>
            <div className={styles.title_container}>
                <h1>Minhas Consultas</h1>
                <LinkButton to="/newappointment" text="Agendar Consulta"/>
            </div>
            {message && <Message type="success" msg={message}/>}
            <Container customClass="start">
                {appointments.length > 0 && 
                    appointments.map((appointment) => (
                        <AppointmentCard 
                        id={appointment.id}
                        name={appointment.name}
                        budget={appointment.budget}
                        category={appointment.category ? appointment.category.name : 'Sem categoria'} 
                        key={appointment.id}
                        />
                    ))
                }
            </Container>
        </div>
    )
}

export default Appointments