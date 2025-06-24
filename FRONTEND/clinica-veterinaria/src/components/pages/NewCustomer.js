import { useNavigate } from 'react-router-dom';
import CustomerForm from '../Customer/CustomerForm';
import styles from './NewCustomer.module.css';

function NewCustomer() {
  const navigate = useNavigate();

  const createCustomer = (customer) => {
    // Corrigir o endpoint: era 'customer', mas o seu db.json usa 'customers'
    fetch('http://localhost:5000/customers', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        ...customer,
        createdAt: new Date().toISOString(), // adiciona o campo criado em
      }),
    })
      .then(res => {
        if (!res.ok) {
          throw new Error('Erro ao criar cliente');
        }
        return res.json();
      })
      .then(() => {
        navigate('/customers', { state: { message: 'Cliente criado com sucesso!' } });
      })
      .catch(err => {
        alert(err.message); // Mostra erro real
      });
  };

  return (
    <div className={styles.newcustomer_container}>
      <h1>Cadastrar Cliente</h1>
      <CustomerForm handleSubmit={createCustomer} btnText="Cadastrar Cliente" />
    </div>
  );
}

export default NewCustomer;
