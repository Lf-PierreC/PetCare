import { useParams, useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import CustomerForm from '../Customer/CustomerForm';
import styles from './NewCustomer.module.css';

function EditCustomer() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [customer, setCustomer] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:5000/customers/${id}`)
      .then(res => res.json())
      .then(json => setCustomer(json.data))
      .catch(console.error);
  }, [id]);

  const updateCustomer = (updatedCustomer) => {
    fetch(`http://localhost:5000/customers/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(updatedCustomer),
    })
      .then(res => res.json())
      .then(data => {
        navigate('/customers', { state: { message: 'Cliente atualizado com sucesso!' } });
      })
      .catch(console.error);
  };

  return (
    <div className={styles.newcustomer_container}>
      <h1>Editar Cliente</h1>
      {customer && (
        <CustomerForm
          handleSubmit={updateCustomer}
          btnText="Atualizar Cliente"
          customerData={customer}
        />
      )}
    </div>
  );
}

export default EditCustomer;
