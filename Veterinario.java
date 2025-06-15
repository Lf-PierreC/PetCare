public class Veterinario {
    private String nome;
    private String crm;

    public Veterinario(String nome, String crm) {
        this.nome = nome;
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public String getCrm() {
        return crm;
    }

    public Diagnostico emitirDiagnostico(String descricao) {
        return new Diagnostico(descricao);
    }

    public Prescricao prescreverMedicamento(Medicamento medicamento) {
        return new Prescricao(medicamento);
    }
}
