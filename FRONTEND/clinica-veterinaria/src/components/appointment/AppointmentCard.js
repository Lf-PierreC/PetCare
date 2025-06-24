import { BsPencil, BsFillTrashFill } from 'react-icons/bs';
import { Link } from 'react-router-dom';
import styles from './AppointmentCard.module.css';

function AppointmentCard({ id, date, petName, serviceName, handleRemove }) {
  return (
    <div className={styles.appointment_card}>
      <h4>Consulta #{id}</h4>
      <p>
        <span>Data:</span> {new Date(date).toLocaleString()}
      </p>
      <p>
        <span>Pet:</span> {petName}
      </p>
      <p>
        <span>Serviço:</span> {serviceName}
      </p>

      <div className={styles.appointment_card_actions}>
        <Link to={`/editappointment/${id}`}>
          <BsPencil /> Editar
        </Link>
        <button onClick={() => handleRemove && handleRemove(Number(id))}>
            <BsFillTrashFill /> Excluir
        </button>
      </div>
    </div>
  );
}

export default AppointmentCard;
