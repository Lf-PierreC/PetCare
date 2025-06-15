import java.util.Date;

public class Consulta {
    private Date data;
    private Cliente cliente;
    private Veterinario veterinario;
    private Diagnostico diagnostico;
    private Prescricao prescricao;

    public Consulta(Date data, Cliente cliente, Veterinario veterinario) {
        this.data = data;
        this.cliente = cliente;
        this.veterinario = veterinario;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setPrescricao(Prescricao prescricao) {
        this.prescricao = prescricao;
    }

    public void exibirResumoConsulta() {
        System.out.println("Consulta: " + data);
        System.out.println("Veterinário: " + veterinario.getNome());
        System.out.println("Pet: " + cliente.getPet().getNome());
        System.out.println("Diagnóstico: " + (diagnostico != null ? diagnostico.getDescricao() : "N/A"));
        System.out.println("Prescrição: " + (prescricao != null ? prescricao.getMedicamento().getNome() : "N/A"));
    }
}
