import {useEffect, useState} from 'react'

import styles from './AppointmentForm.module.css'

import Input from '../Form/Input'
import Select from '../Form/Select'
import SubmitButton from '../Form/SubmitButton'

function AppointmentForm({ handleSubmit, btnText, appointmentData}) {

    const [categories, setCategories] = useState([])
    const [appointment, setAppointment] = useState(appointmentData || {})

    useEffect(() => {
    fetch("http://localhost:5000/categories", {
        method: "GET",
        headers: {
            'Content-Type':'application/json'
        }
    })
        .then((resp) => resp.json())
        .then((data) => {
            setCategories(data)
        })
        .catch((err) => console.log(err))
    }, [])

    const submit = (e) => {
        e.preventDefault()
        //console.log(appointment)
        handleSubmit(appointment)
    }

    function handleChange(e) {
        setAppointment({...appointment, [e.target.name]: e.target.value})
    }

    function handleCategory(e) {
        setAppointment({...appointment, category: {
            id: e.target.value,
            name: e.target.options[e.target.selectedIndex].text,
            },
        })
        
    }

    return (
        <form onSubmit={submit} className={styles.form}>

            <Input type="text" text="Nome do projeto" name="name"
            placeholder="Insira o nome do projeto" handleOnChange={handleChange} value={appointment.name ? appointment.name : ''}/>

            <Input type="number" text="Orçamento do projeto" name="budget"
            placeholder="Insira o orçamento total" handleOnChange={handleChange}
            value={appointment.budget ? appointment.budget : ''}/>
            
            <Select name="category_id" text="Selecione a categoria" options={categories}
            handleOnChange={handleCategory} value={appointment.category ? appointment.category.id : ''}/>

            <SubmitButton text={btnText}/>
        
        </form>
    )
}

export default AppointmentForm