package petshop.modelo;

public class ServicoConsulta extends Servico {

    public ServicoConsulta(double preco) {
        super("Consulta", preco);
    }

    @Override //lembrado
    public void executarServico() {
        System.out.println("Executando serviço de CONSULTA. Preço: R$" + preco);
    }
}
