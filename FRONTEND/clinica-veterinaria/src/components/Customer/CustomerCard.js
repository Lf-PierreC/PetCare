import { BsPencil, BsFillTrashFill } from 'react-icons/bs';
import { Link } from 'react-router-dom';
import styles from './CustomerCard.module.css';

function CustomerCard({ id, name, phone, createdAt, handleRemove }) {
  return (
    <div className={styles.customer_card}>
      <h4>Cliente #{id}</h4>
      <p><span>Nome:</span> {name}</p>
      <p><span>Telefone:</span> {phone}</p>
      <p><span>Criado em:</span> {new Date(createdAt).toLocaleString()}</p>

      <div className={styles.customer_card_actions}>
        <Link to={`/editcustomer/${id}`}>
          <BsPencil /> Editar
        </Link>
        <button onClick={() => handleRemove(id)}>
          <BsFillTrashFill /> Excluir
        </button>
      </div>
    </div>
  );
}

export default CustomerCard;
