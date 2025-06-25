import { useEffect, useState } from 'react';
import VeterinarianCard from '../Veterinarian/VeterinarianCard';
import Message from '../layout/Message';
import Container from '../layout/Container';
import LinkButton from '../layout/LinkButton';
import styles from './Veterinarian.module.css';

function Veterinarian() {
  const [veterinarians, setVeterinarians] = useState([]);
  const [message, setMessage] = useState('');

  useEffect(() => {
    fetch('http://localhost:5000/veterinarians')
      .then(res => res.json())
      .then(json => setVeterinarians(json))
      .catch(console.error);
  }, []);

  const removeVeterinarian = (id) => {
    fetch(`http://localhost:5000/veterinarians/${id}`, {
      method: 'DELETE',
    })
      .then(resp => {
        if (!resp.ok) throw new Error('Erro ao excluir');
        setVeterinarians(prev => prev.filter(v => v.id !== id));
        setMessage('Veterinário excluído com sucesso!');
      })
      .catch(console.error);
  };

  return (
    <div className={styles.veterinarian_container}>
      <div className={styles.title_container}>
        <h1>Veterinários</h1>
        <LinkButton to="/newveterinarians" text="Cadastrar Veterinário" />
      </div>
      {message && <Message type="success" msg={message} />}
      <Container customClass="start">
        {veterinarians.map(vet => (
          <VeterinarianCard
            key={vet.id}
            {...vet}
            handleRemove={removeVeterinarian}
          />
        ))}
      </Container>
    </div>
  );
}

export default Veterinarian;
