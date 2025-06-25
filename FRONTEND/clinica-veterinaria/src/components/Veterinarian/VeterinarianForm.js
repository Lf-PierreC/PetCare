import { useState } from 'react';
import Input from '../Form/Input';
import SubmitButton from '../Form/SubmitButton';
import styles from './VeterinarianForm.module.css';

function VeterinarianForm({ handleSubmit, btnText, veterinarianData }) {
  const [veterinarian, setVeterinarian] = useState(veterinarianData || {
    name: '',
    phone: '',
    crmv: '',
  });

  const submit = (e) => {
    e.preventDefault();
    handleSubmit(veterinarian);
  };

  const handleChange = (e) => {
    setVeterinarian({ ...veterinarian, [e.target.name]: e.target.value });
  };

  return (
    <form onSubmit={submit} className={styles.form}>
      <Input
        type="text"
        text="Nome"
        name="name"
        handleOnChange={handleChange}
        value={veterinarian.name}
      />
      <Input
        type="text"
        text="Telefone"
        name="phone"
        handleOnChange={handleChange}
        value={veterinarian.phone}
      />
      <Input
        type="text"
        text="CRMV"
        name="crmv"
        handleOnChange={handleChange}
        value={veterinarian.crmv}
      />
      <SubmitButton text={btnText} />
    </form>
  );
}

export default VeterinarianForm;
