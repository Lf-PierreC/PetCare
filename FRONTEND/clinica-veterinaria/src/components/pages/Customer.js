import { useEffect, useState } from 'react';
import CustomerCard from '../Customer/CustomerCard';
import Message from '../layout/Message';
import Container from '../layout/Container';
import LinkButton from '../layout/LinkButton';
import styles from './Customer.module.css';

function Customer() {
  const [customers, setCustomers] = useState([]);
  const [message, setMessage] = useState('');

  useEffect(() => {
    fetch('http://localhost:5000/customers')
      .then(res => res.json())
      .then(json => setCustomers(json.data))
      .catch(console.error);
  }, []);

  const removeCustomer = (id) => {
    fetch(`http://localhost:5000/customers/${id}`, {
      method: 'DELETE',
    })
      .then(resp => {
        if (!resp.ok) throw new Error('Erro ao excluir');
        setCustomers(prev => prev.filter(c => c.id !== id));
        setMessage('Cliente excluído com sucesso!');
      })
      .catch(console.error);
  };

  return (
    <div className={styles.customer_container}>
      <div className={styles.title_container}>
        <h1>Clientes</h1>
        <LinkButton to="/newcustomer" text="Cadastrar Cliente" />
      </div>
      {message && <Message type="success" msg={message} />}
      <Container customClass="start">
        {customers.map(customer => (
          <CustomerCard
            key={customer.id}
            {...customer}
            handleRemove={removeCustomer}
          />
        ))}
      </Container>
    </div>
  );
}

export default Customer;
