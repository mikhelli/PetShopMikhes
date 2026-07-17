package petshop.main;

import petshop.controle.Controle;
import petshop.modelo.Servico;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Controle controle;
    private Scanner scanner;

    public Menu() {
        this.controle = new Controle();
        this.scanner = new Scanner(System.in);
    }


    public void iniciar() {
        exibirMenuPrincipal();
    }

    private void exibirMenuPrincipal() {
        int opcao;
        do {
            System.out.println("\n--- Menu Principal Petshop ---");
            System.out.println("1. Gerenciar Donos e Pets");
            System.out.println("2. Gerenciar Serviços");
            System.out.println("3. Gerenciar Entrada/Saída de Pets");
            System.out.println("4. Visualizar Relatórios"); // NOVA OPÇÃO DE RELATÓRIOS
            System.out.println("5. Salvar Dados e Sair");   // OPÇÃO DE SAIR FOI PARA 5
            System.out.print("Escolha uma opção: ");

            opcao = lerOpcaoMenu();

            switch (opcao) {
                case 1:
                    menuGerenciarDonosEPets();
                    break;
                case 2:
                    menuGerenciarServicos();
                    break;
                case 3:
                    menuGerenciarEntradaSaidaPets();
                    break;
                case 4:
                    menuVisualizarRelatorios();
                    break;
                case 5:
                    controle.salvarDados();
                    System.out.println("Dados salvos com sucesso. Saindo do sistema. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        } while (opcao != 5);
        scanner.close();
    }

    private void menuGerenciarDonosEPets() {
        int opcao;
        do {
            System.out.println("\n--- Gerenciar Donos e Pets ---");
            System.out.println("1. Adicionar Novo Dono");
            System.out.println("2. Listar Todos os Donos e seus Pets");
            System.out.println("3. Buscar Dono por Nome");
            System.out.println("4. Atualizar Dados do Dono");
            System.out.println("5. Remover Dono");
            System.out.println("6. Adicionar Pet a um Dono");
            System.out.println("7. Remover Pet de um Dono");
            System.out.println("8. Atualizar Dados de um Pet");
            System.out.println("9. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcao = lerOpcaoMenu();

            switch (opcao) {
                case 1:
                    adicionarDono();
                    break;
                case 2:
                    controle.listarTodosDonos();
                    break;
                case 3:
                    buscarDono();
                    break;
                case 4:
                    atualizarDono();
                    break;
                case 5:
                    removerDono();
                    break;
                case 6:
                    adicionarPetAoDono();
                    break;
                case 7:
                    removerPetDoDono();
                    break;
                case 8:
                    atualizarPetDoDono();
                    break;
                case 9:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        } while (opcao != 9);
    }

    private void menuGerenciarServicos() {
        int opcao;
        do {
            System.out.println("\n--- Gerenciar Serviços ---");
            System.out.println("1. Listar Serviços Disponíveis");
            System.out.println("2. Oferecer Serviço a um Pet");
            System.out.println("3. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcao = lerOpcaoMenu();

            switch (opcao) {
                case 1:
                    listarServicosDisponiveis();
                    break;
                case 2:
                    oferecerServicoAPet();
                    break;
                case 3:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        } while (opcao != 3);
    }

    //MINI MENU DA ENTRADA E SAIDA DOS PET
    private void menuGerenciarEntradaSaidaPets() {
        int opcao;
        do {
            System.out.println("\n--- Gerenciar Entrada/Saída de Pets ---");
            System.out.println("1. Dar Entrada em um Pet (Receber)");
            System.out.println("2. Dar Saída em um Pet (Liberar)");
            System.out.println("3. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcao = lerOpcaoMenu();

            switch (opcao) {
                case 1:
                    darEntradaPet();
                    break;
                case 2:
                    darSaidaPet();
                    break;
                case 3:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        } while (opcao != 3);
    }

    // VER OS RELATORIOS ESSA AQUI NÃO PERMITE EDITAR POIS NÃO É FUNÇÃO DELA
    private void menuVisualizarRelatorios() {
        int opcao;
        do {
            System.out.println("\n--- Relatórios ---");
            System.out.println("1. Histórico de Atendimentos Realizados");
            System.out.println("2. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcao = lerOpcaoMenu();

            switch (opcao) {
                case 1:
                    controle.listarTodosAtendimentos(); // Chama o controle para listar
                    break;
                case 2:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        } while (opcao != 2);
    }

    private void adicionarDono() {
        System.out.print("Digite o nome do novo Dono: ");
        scanner.nextLine();
        String nome = scanner.nextLine();
        System.out.print("Digite o telefone do novo Dono: ");
        String telefone = scanner.nextLine();

        controle.adicionarDono(nome, telefone);
    }

    private void buscarDono() {
        System.out.print("Digite o nome do Dono para buscar: ");
        scanner.nextLine();
        String nome = scanner.nextLine();

        controle.buscarDonoPorNome(nome).ifPresentOrElse(
                dono -> {
                    System.out.println("\n--- Dono Encontrado ---");
                    System.out.println("Nome: " + dono.getNome() + ", Telefone: " + dono.getTelefone());
                    dono.listarPets();
                    System.out.println("------------------------");
                },
                () -> System.out.println("Dono '" + nome + "' não encontrado.")
        );
    }

    private void atualizarDono() {
        System.out.print("Digite o nome ATUAL do Dono que deseja atualizar: ");
        scanner.nextLine();
        String nomeAtual = scanner.nextLine();

        System.out.print("Digite o NOVO nome para o Dono: ");
        String novoNome = scanner.nextLine();
        System.out.print("Digite o NOVO telefone para o Dono: ");
        String novoTelefone = scanner.nextLine();

        controle.atualizarDono(nomeAtual, novoNome, novoTelefone);
    }

    private void removerDono() {
        System.out.print("Digite o nome do Dono que deseja remover: ");
        scanner.nextLine();
        String nome = scanner.nextLine();

        controle.removerDono(nome);
    }

    private void adicionarPetAoDono() {
        System.out.print("Digite o nome do Dono ao qual o Pet será adicionado: ");
        scanner.nextLine();
        String nomeDono = scanner.nextLine();

        System.out.print("Digite o nome do Pet: ");
        String nomePet = scanner.nextLine();
        System.out.print("Digite a idade do Pet: ");
        int idadePet = lerInteiro();
        System.out.print("Digite o tipo do Pet (Ex: cachorro, gato): ");
        scanner.nextLine();
        String tipoPet = scanner.nextLine();
        System.out.print("Digite a raça do Pet: ");
        String racaPet = scanner.nextLine();

        controle.adicionarPetAoDono(nomeDono, nomePet, idadePet, tipoPet, racaPet);
    }

    private void removerPetDoDono() {
        System.out.print("Digite o nome do Dono do Pet que será removido: ");
        scanner.nextLine();
        String nomeDono = scanner.nextLine();

        System.out.print("Digite o nome do Pet que deseja remover: ");
        String nomePet = scanner.nextLine();

        controle.removerPetDoDono(nomeDono, nomePet);
    }

    private void atualizarPetDoDono() {
        System.out.print("Digite o nome do Dono do Pet que será atualizado: ");
        scanner.nextLine();
        String nomeDono = scanner.nextLine();

        System.out.print("Digite o nome ATUAL do Pet que deseja atualizar: ");
        String nomePetAtual = scanner.nextLine();

        System.out.print("Digite o NOVO nome do Pet: ");
        String novoNomePet = scanner.nextLine();
        System.out.print("Digite a NOVA idade do Pet: ");
        int novaIdadePet = lerInteiro();
        System.out.print("Digite o NOVO tipo do Pet: ");
        scanner.nextLine();
        String novoTipoPet = scanner.nextLine();
        System.out.print("Digite a NOVA raça do Pet: ");
        String novaRacaPet = scanner.nextLine();

        controle.atualizarPetDoDono(nomeDono, nomePetAtual, novoNomePet, novaIdadePet, novoTipoPet, novaRacaPet);
    }

    private void listarServicosDisponiveis() {
        List<Servico> servicos = controle.getServicosDisponiveis();
        if (servicos.isEmpty()) {
            System.out.println("Nenhum serviço disponível cadastrado.");
            return;
        }
        System.out.println("\n--- Serviços Disponíveis ---");
        for (Servico servico : servicos) {
            System.out.printf("Nome: %s, Preço: R$%.2f\n", servico.getNome(), servico.getPreco());
        }
        System.out.println("----------------------------");
    }

    private void oferecerServicoAPet() {
        System.out.print("Digite o nome do Dono do Pet: ");
        scanner.nextLine();
        String nomeDono = scanner.nextLine();

        System.out.print("Digite o nome do Pet: ");
        String nomePet = scanner.nextLine();

        System.out.print("Digite o nome do Serviço a ser oferecido (Ex: Banho, Consulta): ");
        String nomeServico = scanner.nextLine();

        controle.oferecerServicoAPet(nomeDono, nomePet, nomeServico);
    }

    //ITERAÇÃO DA ENTRA E SAIDA DO ANIMAL

    private void darEntradaPet() {
        System.out.print("Digite o nome do Dono do Pet para dar ENTRADA: ");
        scanner.nextLine();
        String nomeDono = scanner.nextLine();

        System.out.print("Digite o nome do Pet para dar ENTRADA: ");
        String nomePet = scanner.nextLine();

        controle.darEntradaPet(nomeDono, nomePet);
    }

    private void darSaidaPet() {
        System.out.print("Digite o nome do Dono do Pet para dar SAÍDA: ");
        scanner.nextLine();
        String nomeDono = scanner.nextLine();

        System.out.print("Digite o nome do Pet para dar SAÍDA: ");
        String nomePet = scanner.nextLine();

        controle.darSaidaPet(nomeDono, nomePet);
    }


     //Método auxiliar para ler opções de menu, TRYCATCH DAS ENTRADAS INAVL; DOS USUARIOS

    private int lerOpcaoMenu() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.nextLine();
            return -1;
        }
    }

    private int lerInteiro() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                scanner.nextLine();
            }
        }
    }
}