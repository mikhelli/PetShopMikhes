package petshop.modelo;

import java.io.Serializable;

public abstract class Servico implements Serializable {
    protected String nome;
    protected double preco;

    private static int contServ = 0; // ← static

    public Servico(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
        contServ++; // aumentando meu cont
    }

    public abstract void executarServico(); //lembrar colocar o override no outro serviço

    public static int getTotalServicosCriados() {
        return contServ;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
