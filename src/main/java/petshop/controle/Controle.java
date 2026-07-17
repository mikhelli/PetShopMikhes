package petshop.controle;

import petshop.modelo.Dono;
import petshop.modelo.Pet;
import petshop.modelo.Servico;
import petshop.modelo.ServicoBanho;
import petshop.modelo.ServicoConsulta;
import petshop.modelo.Atendimento; // NOVO IMPORT
import petshop.arquivos.GerenciadorDeDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class  Controle {
    private List<Dono> donos;
    private List<Servico> servicosDisponiveis;
    private List<Atendimento> atendimentos; // colocado para gerar relatorio de att(lembarbndo que são proposital imutaveis)

    public Controle() {
        this.donos = GerenciadorDeDados.carregarDonos();
        this.atendimentos = GerenciadorDeDados.carregarAtendimentos(); // carrega os att

        // se lista ainda estiver vazia vau add os padrão
        if (this.servicosDisponiveis == null || this.servicosDisponiveis.isEmpty()) {
            this.servicosDisponiveis = new ArrayList<>();
            this.servicosDisponiveis.add(new ServicoBanho(50.00));
            this.servicosDisponiveis.add(new ServicoConsulta(120.00));
        }
    }

    // IMPLEMENTAÇÃO DO CRUD!!!!!!


    public boolean adicionarDono(String nome, String telefone) {
        if (buscarDonoPorNome(nome).isPresent()) {
            System.out.println("Erro: Já existe um dono cadastrado com esse nome '" + nome);
            return false;
        } // tentar voltar aqui para colocar verificação se é o mesmo dono ou se quer atualizar no cadasttro do dono
        Dono novoDono = new Dono(nome, telefone);
        this.donos.add(novoDono);
        System.out.println("Dono '" + nome + "' adicionado com sucesso.");
        return true;
    }

    public Optional<Dono> buscarDonoPorNome(String nome) {
        return this.donos.stream()
                .filter(dono -> dono.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

   // lista de donos  e pets cadatsrados
    public void listarTodosDonos() {
        if (donos.isEmpty()) {
            System.out.println("Nenhum dono cadastrado no sistema.");
            return;
        }
        System.out.println("\n--- Donos Cadastrados ---");
        for (Dono dono : donos) {
            System.out.println("Nome: " + dono.getNome() + ", Telefone: " + dono.getTelefone());
            dono.listarPets();
            System.out.println("-------------------------");
        }
    }

    //opção de poder atualizar o cadTSRADDO DO DONO
    public boolean atualizarDono(String nomeAtual, String novoNome, String novoTelefone) {
        Optional<Dono> donoOptional = buscarDonoPorNome(nomeAtual);
        if (donoOptional.isPresent()) {
            Dono donoParaAtualizar = donoOptional.get();
            if (!nomeAtual.equalsIgnoreCase(novoNome) && buscarDonoPorNome(novoNome).isPresent()) {
                System.out.println("Erro: O novo nome '" + novoNome + "' já está em uso por outro dono.");
                return false;
            }
            donoParaAtualizar.setNome(novoNome);
            donoParaAtualizar.setTelefone(novoTelefone);
            System.out.println("Dono '" + nomeAtual + "' atualizado para '" + novoNome + "'.");
            return true;
        }
        System.out.println("Erro: Dono '" + nomeAtual + "' não encontrado para atualização.");
        return false;
    }

    // opção de remover o dono do sistema
    //NÃO É A MESMA COISA DE REMOVER O ATENDIEMDINMENO
    // NO LUGAR QUE TEM O ATENDIEMNTO É COMO SE FICASSE SALVO NO REGSITRO PARA SEMPRE MAS O DONO EM SI PODE SER REMOVIDO DO CADSSTARDO DA LOJA
    public boolean removerDono(String nomeDono) {
        Optional<Dono> donoOptional = buscarDonoPorNome(nomeDono);
        if (donoOptional.isPresent()) {
            this.donos.remove(donoOptional.get());
            System.out.println("Dono '" + nomeDono + "' removido com sucesso.");
            return true;
        }
        System.out.println("Erro: Dono '" + nomeDono + "' não encontrado para remoção.");
        return false;
    }

    // CRUD DOS PETS 9( TODO PET OBRIGATORIAMENTE OPRECISA VIR  DE UM DONO)

    public boolean adicionarPetAoDono(String nomeDono, String nomePet, int idadePet, String tipoPet, String racaPet) {
        Optional<Dono> donoOptional = buscarDonoPorNome(nomeDono);
        if (donoOptional.isPresent()) {
            Dono dono = donoOptional.get();
            boolean petExiste = dono.getPets().stream()
                    .anyMatch(p -> p.getNome().equalsIgnoreCase(nomePet));
            if (petExiste) {
                System.out.println("Erro: O dono '" + nomeDono + "' já possui um pet com o nome '" + nomePet + "'.");
                return false;
            }
            dono.adicionarPet(nomePet, idadePet, tipoPet, racaPet);
            System.out.println("Pet '" + nomePet + "' adicionado ao dono '" + nomeDono + "'.");
            return true;
        }
        System.out.println("Erro: Dono '" + nomeDono + "' não encontrado para adicionar o pet.");
        return false;
    }

    // REMOVE PET
    public boolean removerPetDoDono(String nomeDono, String nomePet) {
        Optional<Dono> donoOptional = buscarDonoPorNome(nomeDono);
        if (donoOptional.isPresent()) {
            Dono dono = donoOptional.get();
            Optional<Pet> petParaRemover = dono.getPets().stream()
                    .filter(p -> p.getNome().equalsIgnoreCase(nomePet))
                    .findFirst();
            if (petParaRemover.isPresent()) {
                dono.getPets().remove(petParaRemover.get());
                System.out.println("Pet '" + nomePet + "' removido do dono '" + nomeDono + "'.");
                return true;
            }
            System.out.println("Erro: Pet '" + nomePet + "' não encontrado para o dono '" + nomeDono + "'.");
            return false;
        }
        System.out.println("Erro: Dono '" + nomeDono + "' não encontrado para remover o pet.");
        return false;
    }

    //ATUALIZZANCO OS DADOS DO PET
    public boolean atualizarPetDoDono(String nomeDono, String nomePetAtual, String novoNomePet, int novaIdadePet, String novoTipoPet, String novaRacaPet) {
        Optional<Dono> donoOptional = buscarDonoPorNome(nomeDono);
        if (donoOptional.isPresent()) {
            Dono dono = donoOptional.get();
            Optional<Pet> petOptional = dono.getPets().stream()
                    .filter(p -> p.getNome().equalsIgnoreCase(nomePetAtual))
                    .findFirst();
            if (petOptional.isPresent()) {
                if (!nomePetAtual.equalsIgnoreCase(novoNomePet) &&
                        dono.getPets().stream().anyMatch(p -> p.getNome().equalsIgnoreCase(novoNomePet))) {
                    System.out.println("Erro: O novo nome '" + novoNomePet + "' já está em uso por outro pet deste dono.");
                    return false;
                }

                Pet petParaAtualizar = petOptional.get();
                petParaAtualizar.setNome(novoNomePet);
                petParaAtualizar.setIdade(novaIdadePet);
                petParaAtualizar.setTipo(novoTipoPet);
                petParaAtualizar.setRaca(novaRacaPet);
                System.out.println("Pet '" + nomePetAtual + "' do dono '" + nomeDono + "' atualizado com sucesso.");
                return true;
            }
            System.out.println("Erro: Pet '" + nomePetAtual + "' não encontrado para o dono '" + nomeDono + "'.");
            return false;
        }
        System.out.println("Erro: Dono '" + nomeDono + "' não encontrado para atualizar o pet.");
        return false;
    }

    // ENTRADA E  SAIDA DO PET PARA REGISTRAR NO ATT

    public boolean darEntradaPet(String nomeDono, String nomePet) {
        Optional<Dono> donoOptional = buscarDonoPorNome(nomeDono);
        if (donoOptional.isPresent()) {
            Dono dono = donoOptional.get();
            Optional<Pet> petOptional = dono.getPets().stream()
                    .filter(p -> p.getNome().equalsIgnoreCase(nomePet))
                    .findFirst();
            if (petOptional.isPresent()) {
                Pet pet = petOptional.get();
                if (pet.isNoPetshop()) {
                    System.out.println("Erro: Pet '" + nomePet + "' do dono '" + nomeDono + "' JÁ está no petshop.");
                    return false;
                }
                pet.setNoPetshop(true);
                System.out.println("Pet '" + nomePet + "' do dono '" + nomeDono + "' deu ENTRADA no petshop.");
                return true;
            }
            System.out.println("Erro: Pet '" + nomePet + "' não encontrado para o dono '" + nomeDono + "'.");
            return false;
        }
        System.out.println("Erro: Dono '" + nomeDono + "' não encontrado para dar entrada no pet.");
        return false;
    }

    // SAIDA PET
    public boolean darSaidaPet(String nomeDono, String nomePet) {
        Optional<Dono> donoOptional = buscarDonoPorNome(nomeDono);
        if (donoOptional.isPresent()) {
            Dono dono = donoOptional.get();
            Optional<Pet> petOptional = dono.getPets().stream()
                    .filter(p -> p.getNome().equalsIgnoreCase(nomePet))
                    .findFirst();
            if (petOptional.isPresent()) {
                Pet pet = petOptional.get();
                if (!pet.isNoPetshop()) {
                    System.out.println("Erro: Pet '" + nomePet + "' do dono '" + nomeDono + "' JÁ está em casa.");
                    return false;
                }
                pet.setNoPetshop(false);
                System.out.println("Pet '" + nomePet + "' do dono '" + nomeDono + "' deu SAÍDA do petshop. Pet Liberado!");
                return true;
            }
            System.out.println("Erro: Pet '" + nomePet + "' não encontrado para o dono '" + nomeDono + "'.");
            return false;
        }
        System.out.println("Erro: Dono '" + nomeDono + "' não encontrado para dar saída no pet.");
        return false;
    }

    // --- Métodos DE Serviços ---

    public List<Servico> getServicosDisponiveis() {
        return new ArrayList<>(servicosDisponiveis);
    }

    //VAI OFERECER SERV. ESPECIFICO MAS SÓ S EO PET TIVER PASSADO PELA ENTRDAA
    public boolean oferecerServicoAPet(String nomeDono, String nomePet, String nomeServico) {
        Optional<Dono> donoOptional = buscarDonoPorNome(nomeDono);
        if (donoOptional.isPresent()) {
            Dono dono = donoOptional.get();
            Optional<Pet> petOptional = dono.getPets().stream()
                    .filter(p -> p.getNome().equalsIgnoreCase(nomePet))
                    .findFirst();
            if (petOptional.isPresent()) {
                Pet pet = petOptional.get();
                // VERIFICAÇÃO: O pet precisa estar no petshop para receber serviço!!!!!!
                if (!pet.isNoPetshop()) {
                    System.out.println("Erro: Pet '" + nomePet + "' não está no petshop para receber o serviço. Por favor, dê ENTRADA nele primeiro.");
                    return false;
                }

                Optional<Servico> servicoOptional = servicosDisponiveis.stream()
                        .filter(s -> s.getNome().equalsIgnoreCase(nomeServico))
                        .findFirst();
                if (servicoOptional.isPresent()) {
                    Servico servico = servicoOptional.get();
                    System.out.println("\n--- Executando Serviço para " + pet.getNome() + " (Dono: " + dono.getNome() + ") ---");
                    servico.executarServico();
                    System.out.println("------------------------------------------------------------------");

                    //  RegistrO DE atendimento
                    Atendimento novoAtendimento = new Atendimento(dono.getNome(), pet.getNome(), servico.getNome(), servico.getPreco());
                    this.atendimentos.add(novoAtendimento);
                    System.out.println("[Atendimento registrado com sucesso.]");

                    return true;
                } else {
                    System.out.println("Erro: Serviço '" + nomeServico + "' não encontrado nos serviços disponíveis.");
                }
            } else {
                System.out.println("Erro: Pet '" + nomePet + "' não encontrado para o dono '" + nomeDono + "'.");
            }
        } else {
            System.out.println("Erro: Dono '" + nomeDono + "' não encontrado.");
        }
        return false;
    }

    // --- Métodos para Atendimentos ---

    // LISTA
    public void listarTodosAtendimentos() {
        if (atendimentos.isEmpty()) {
            System.out.println("Nenhum atendimento registrado no histórico.");
            return;
        }
        System.out.println("\n--- Histórico de Atendimentos ---");
        for (Atendimento atendimento : atendimentos) {
            System.out.println(atendimento.toString());
        }
        System.out.println("---------------------------------");
    }

    // METODO PARA SALVAR

    public void salvarDados() {
        GerenciadorDeDados.salvarDonos(this.donos);
        GerenciadorDeDados.salvarAtendimentos(this.atendimentos); // NOVO: Salva atendimentos
    }
}