package petshop.modelo;

import java.io.Serializable;

public class Pet implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private int idade;
    private String tipo;
    private String raca;
    private boolean noPetshop; // NOVO ATRIBUTO: true se estiver no petshop, false se estiver em casa

    public Pet(String nome, int idade, String tipo, String raca) {
        this.nome = nome;
        this.idade = idade;
        this.tipo = tipo;
        this.raca = raca;
        this.noPetshop = false; // Por padrão, o pet não está no petshop ao ser criado
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    // vai verfiicar o status do animal nno petshop
    public boolean isNoPetshop() {
        return noPetshop;
    }

    public void setNoPetshop(boolean noPetshop) {
        this.noPetshop = noPetshop;
    }

    @Override
    public String toString() {
        String status = noPetshop ? " (NO PETSHOP)" : " (EM CASA)";
        return "  - Pet: " + nome + " (Tipo: " + tipo + ", Raça: " + raca + ", Idade: " + idade + " anos)" + status;
    }
}