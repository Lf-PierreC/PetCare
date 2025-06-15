import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClinicaVeterinaria implements Atendimento {
    private List<Consulta> consultas = new ArrayList<>();

    @Override
    public void agendarConsulta(Consulta consulta) {
        consultas.add(consulta);
        System.out.println("Consulta agendada para " + consulta);
    }

    @Override
    public void realizarConsulta(Consulta consulta) {
        System.out.println("Realizando consulta...");
        consultas.remove(consulta);
        consulta.exibirResumoConsulta();
    }

  
    public static void main(String[] args) {
        Pet pet = new Pet("Bidu", "Cachorro", 3);
        Cliente cliente = new Cliente("Luiz", "99999-9999", pet);
        Veterinario vet = new Veterinario("Dra. Ana", "CRMV12345");
        Medicamento med = new Medicamento("Antibiótico X", "2x ao dia");

        Consulta consulta = new Consulta(new Date(), cliente, vet);
        consulta.setDiagnostico(vet.emitirDiagnostico("Infecção na pata"));
        consulta.setPrescricao(vet.prescreverMedicamento(med));

        ClinicaVeterinaria clinica = new ClinicaVeterinaria();
        clinica.agendarConsulta(consulta);
        clinica.realizarConsulta(consulta);
    }
}
