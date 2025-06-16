import {Link} from 'react-router-dom'

import Container from './Container'

import styles from './Navbar.module.css'
import logo from '../img/Logo branca.png'

function Navbar() {
    return (
        <nav className={styles.navbar}>
            <Container>
                <Link>
                    <img src={logo} alt='Costs' width='100px'/>
                </Link>
                <ul className={styles.list}>
                    <li className={styles.item}>
                        <Link to="/">Home</Link>
                    </li>
                    <li className={styles.item}>
                        <Link to="/appointment">Consultas</Link>
                    </li>
                    <li className={styles.item}>
                        <Link to="/contact">Contato</Link>
                    </li>
                    <li className={styles.item}>
                        <Link to="/company">Empresa</Link>
                    </li>
                    <li className={styles.item}>
                        <Link to="/newappointment">Nova Consulta</Link>
                    </li>
                </ul>
            </Container>
        </nav>
    )
}

export default Navbar