package petshop.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Dono implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String telefone;
    private List<Pet> pets;

    public Dono(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
        this.pets = new ArrayList<>();
    }

    // Getters
    public String getNome() {
        return nome;
    }
    public String getTelefone() {
        return telefone;
    }
    // Setters

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Pet> getPets() {
        return pets;
    }

    // Método para adicionar um Pet ao Dono (já existente)
    public void adicionarPet(String nome, int idade, String tipo, String raca) {
        Pet novoPet = new Pet(nome, idade, tipo, raca);
        this.pets.add(novoPet);
        System.out.println("  [Pet '" + nome + "' adicionado diretamente.]");
    }

    //  Adiciona um Pet já criado ao Dono
    public void adicionarPet(Pet pet) {
        if (pet != null) {
            this.pets.add(pet);
            System.out.println("  [Pet '" + pet.getNome() + "' adicionado por objeto.]");
        } else {
            System.out.println("  [Erro: Tentativa de adicionar um pet nulo.]");
        }
    }

    // Método para listar os pets deste dono
    public void listarPets() {
        if (pets.isEmpty()) {
            System.out.println("  [Nenhum pet cadastrado para este dono.]");
            return;
        }
        System.out.println("  Pets de " + nome + ":");
        for (Pet pet : pets) {
            System.out.println(pet.toString());
        }
    }

    @Override
    public String toString() {
        return "Dono: " + nome + ", Telefone: " + telefone + ", Pets: " + pets.size();
    }
}