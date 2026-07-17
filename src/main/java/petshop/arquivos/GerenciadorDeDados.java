package petshop.arquivos;

import petshop.modelo.Dono;
import petshop.modelo.Atendimento;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class GerenciadorDeDados {
    private static final String ARQUIVO_DONOS = "donos.dat";
    private static final String ARQUIVO_ATENDIMENTOS = "atendimentos.dat"; //  Arquivo para atendimentos
    // SALVAR LISTA DO DONO EM ARQUI.

    public static void salvarDonos(List<Dono> donos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DONOS))) {
            oos.writeObject(donos);
            System.out.println("[Dados de donos salvos em " + ARQUIVO_DONOS + "]");
        } catch (Exception e) {
            System.err.println("Erro ao salvar donos: " + e.getMessage());
        }
    }


    public static List<Dono> carregarDonos() {
        File arquivo = new File(ARQUIVO_DONOS);
        if (!arquivo.exists() || arquivo.length() == 0) {
            System.out.println("[Arquivo de donos não encontrado ou vazio. Criando nova lista.]");
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_DONOS))) {
            List<Dono> donos = (List<Dono>) ois.readObject();
            System.out.println("[Dados de donos carregados de " + ARQUIVO_DONOS + "]");
            return donos;
        } catch (Exception e) {
            System.err.println("Erro ao carregar donos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void salvarAtendimentos(List<Atendimento> atendimentos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_ATENDIMENTOS))) {
            oos.writeObject(atendimentos);
            System.out.println("[Dados de atendimentos salvos em " + ARQUIVO_ATENDIMENTOS + "]");
        } catch (Exception e) {
            System.err.println("Erro ao salvar atendimentos: " + e.getMessage());
        }
    }
    public static List<Atendimento> carregarAtendimentos() {
        File arquivo = new File(ARQUIVO_ATENDIMENTOS);
        if (!arquivo.exists() || arquivo.length() == 0) {
            System.out.println("[Arquivo de atendimentos não encontrado ou vazio. Criando nova lista.]");
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_ATENDIMENTOS))) {
            List<Atendimento> atendimentos = (List<Atendimento>) ois.readObject();
            System.out.println("[Dados de atendimentos carregados de " + ARQUIVO_ATENDIMENTOS + "]");
            return atendimentos;
        } catch (Exception e) {
            System.err.println("Erro ao carregar atendimentos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}