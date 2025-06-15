public class Medicamento {
    private String nome;
    private String dosagem;

    public Medicamento(String nome, String dosagem) {
        this.nome = nome;
        this.dosagem = dosagem;
    }

    public String getNome() {
        return nome;
    }

    public String getDosagem() {
        return dosagem;
    }
}
