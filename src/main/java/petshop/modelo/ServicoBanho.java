package petshop.modelo;

public class ServicoBanho extends Servico {

    public ServicoBanho(double preco) {
        super("Banho", preco);
    }

    @Override // lembrei!!!!!
    public void executarServico() {
        System.out.println("Executando serviço de BANHO. Preço: R$" + preco);
    }
}
