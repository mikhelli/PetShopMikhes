package petshop.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Atendimento implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nomeDono;
    private String nomePet;
    private String nomeServico;
    private double precoServico;
    private LocalDateTime dataHora;

    public Atendimento(String nomeDono, String nomePet, String nomeServico, double precoServico) {
        this.nomeDono = nomeDono;
        this.nomePet = nomePet;
        this.nomeServico = nomeServico;
        this.precoServico = precoServico;
        this.dataHora = LocalDateTime.now();
    }

    // Getters
    public String getNomeDono() {
        return nomeDono;
    }

    public String getNomePet() {
        return nomePet;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public double getPrecoServico() {
        return precoServico;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("Atendimento: %s para %s (Dono: %s) - Preço: R$%.2f - Data: %s",
                nomeServico, nomePet, nomeDono, precoServico, dataHora.format(formatter));
    }
}

// classe sem sets pq não tem necessidade de colocar eles já que são dados imutaveis apenas para gerar relatorios
// de atendimentos