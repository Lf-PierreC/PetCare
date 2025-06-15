public class Cliente {
    private String nome;
    private String telefone;
    private Pet pet;

    public Cliente(String nome, String telefone, Pet pet) {
        this.nome = nome;
        this.telefone = telefone;
        this.pet = pet;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public Pet getPet() {
        return pet;
    }
}
