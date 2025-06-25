import { useNavigate } from 'react-router-dom';
import VeterinarianForm from '../Veterinarian/VeterinarianForm';
import styles from './NewVeterinarian.module.css';

function NewVeterinarian() {
  const navigate = useNavigate();

  const createVeterinarian = (veterinarian) => {
    fetch('http://localhost:5000/veterinarians', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        ...veterinarian,
        createdAt: new Date().toISOString(),
      }),
    })
      .then(res => {
        if (!res.ok) {
          throw new Error('Erro ao cadastrar veterinário');
        }
        return res.json();
      })
      .then(() => {
        navigate('/veterinarians', { state: { message: 'Veterinário cadastrado com sucesso!' } });
      })
      .catch(err => {
        alert(err.message);
      });
  };

  return (
    <div className={styles.newveterinarian_container}>
      <h1>Cadastrar Veterinário</h1>
      <VeterinarianForm
        handleSubmit={createVeterinarian}
        btnText="Cadastrar Veterinário"
      />
    </div>
  );
}

export default NewVeterinarian;
