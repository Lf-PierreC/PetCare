import styles from './Home.module.css'
import logo from '../img/Logo Clinica.png'
import LinkButton from '../layout/LinkButton'

function Home(){
    return (
        <section className={styles.home_container}>
            <h1>Bem-vindo a <span>Pet Care</span></h1>
            <p>Comece a gerenciar os seus projetos agora mesmo!</p>
            <LinkButton to="/newproject" text="Agendar Consulta"/>
            <img src={logo} alt='Clinica'/>
        </section>
    )
}

export default Home