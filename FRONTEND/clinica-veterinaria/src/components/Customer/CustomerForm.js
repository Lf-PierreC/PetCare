import { useState, useEffect } from 'react';
import Input from '../Form/Input';
import SubmitButton from '../Form/SubmitButton';
import styles from './CustomerForm.module.css';

function CustomerForm({ handleSubmit, btnText, customerData }) {
  const [customer, setCustomer] = useState(customerData || { name: '', phone: '' });

  const submit = (e) => {
    e.preventDefault();
    handleSubmit(customer);
  };

  const handleChange = (e) => {
    setCustomer({ ...customer, [e.target.name]: e.target.value });
  };

  return (
    <form onSubmit={submit} className={styles.form}>
      <Input
        type="text"
        text="Nome do cliente"
        name="name"
        handleOnChange={handleChange}
        value={customer.name || ''}
      />
      <Input
        type="text"
        text="Telefone"
        name="phone"
        handleOnChange={handleChange}
        value={customer.phone || ''}
      />
      <SubmitButton text={btnText} />
    </form>
  );
}

export default CustomerForm;
