import { useNavigate } from 'react-router-dom';
import CustomerForm from '../Customer/CustomerForm';
import styles from './NewCustomer.module.css';

function NewCustomer() {
  const navigate = useNavigate();

  const createCustomer = (customer) => {
    fetch('http://localhost:5000/customers', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(customer),
    })
      .then(res => res.json())
      .then(data => {
        if (data.code === 200) {
          navigate('/customers', { state: { message: 'Cliente criado com sucesso!' } });
        } else {
          alert(data.message);
        }
      })
      .catch(console.error);
  };

  return (
    <div className={styles.newcustomer_container}>
      <h1>Cadastrar Cliente</h1>
      <CustomerForm handleSubmit={createCustomer} btnText="Cadastrar Cliente" />
    </div>
  );
}

export default NewCustomer;
