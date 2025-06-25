import { BsPencil, BsFillTrashFill } from 'react-icons/bs';
import { Link } from 'react-router-dom';
import styles from './VeterinarianCard.module.css';

function VeterinarianCard({ id, name, phone, crmv, createdAt, handleRemove }) {
  return (
    <div className={styles.veterinarian_card}>
      <h4>Veterinário #{id}</h4>
      <p><span>Nome:</span> {name}</p>
      <p><span>Telefone:</span> {phone}</p>
      <p><span>CRMV:</span> {crmv}</p>
      <p><span>Criado em:</span> {new Date(createdAt).toLocaleString()}</p>

      <div className={styles.veterinarian_card_actions}>
        <Link to={`/editveterinarians/${id}`}>
          <BsPencil /> Editar
        </Link>
        <button onClick={() => handleRemove(id)}>
          <BsFillTrashFill /> Excluir
        </button>
      </div>
    </div>
  );
}

export default VeterinarianCard;
