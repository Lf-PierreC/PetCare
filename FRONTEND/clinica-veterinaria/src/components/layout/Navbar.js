import {Link} from 'react-router-dom'

import Container from './Container'

import styles from './Navbar.module.css'
import logo from '../img/Logo branca.png'

function Navbar() {
    return (
        <nav className={styles.navbar}>
            <Container>
                <Link>
                    <img src={logo} alt='PetCare' width='100px'/>
                </Link>
                <ul className={styles.list}>
                    <li className={styles.item}>
                        <Link to="/">Home</Link>
                    </li>
                    <li className={styles.item}>
                        <Link to="/appointments">Consultas</Link>
                    </li>
                    <li className={styles.item}>
                        <Link to="/customers">Clientes</Link>
                    </li>
                    <li className={styles.item}>
                        <Link to="/veterinarians">Veterinários</Link>
                    </li>
                    <li className={styles.item}>
                        <Link to="/newappointments">Nova Consulta</Link>
                    </li>
                </ul>
            </Container>
        </nav>
    )
}

export default Navbar