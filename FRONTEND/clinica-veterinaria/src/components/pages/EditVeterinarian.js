import { useParams, useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import VeterinarianForm from '../Veterinarian/VeterinarianForm';
import styles from './NewVeterinarian.module.css';

function EditVeterinarian() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [veterinarian, setVeterinarian] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:5000/veterinarians/${id}`)
      .then(res => res.json())
      .then(json => setVeterinarian(json))
      .catch(console.error);
  }, [id]);

  const updateVeterinarian = (updatedVet) => {
    fetch(`http://localhost:5000/veterinarians/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(updatedVet),
    })
      .then(res => res.json())
      .then(() => {
        navigate('/veterinarians', { state: { message: 'Veterinário atualizado com sucesso!' } });
      })
      .catch(console.error);
  };

  return (
    <div className={styles.newveterinarian_container}>
      <h1>Editar Veterinário</h1>
      {veterinarian && (
        <VeterinarianForm
          handleSubmit={updateVeterinarian}
          btnText="Atualizar Veterinário"
          veterinarianData={veterinarian}
        />
      )}
    </div>
  );
}

export default EditVeterinarian;
