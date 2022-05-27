import java.util.ArrayList;

public abstract class Conta implements IConta {
	
	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;
	
	protected int agencia;
	protected int numero;
	protected double saldo;
	protected Cliente cliente;
	protected ArrayList<Double> historicoExtrato;

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
		this.historicoExtrato = new ArrayList<Double>();
	}

	@Override
	public void sacar(double valor) {
		saldo -= valor;
		historicoExtrato.add(-(valor));
	}

	@Override
	public void depositar(double valor) {
		saldo += valor;
		historicoExtrato.add(valor);
	}

	@Override
	public void transferir(double valor, Conta contaDestino, Banco banco) {
		this.sacar(valor);
		banco.adicionarTransferencia(this.numero, this.agencia, contaDestino, valor);
		contaDestino.depositar(valor);
	}

	public int getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
		imprimirHistorico();
	}
	
	private void imprimirHistorico() {
		if(historicoExtrato.size() != 0) {
			System.out.println("===========Historico " + cliente.getNome() + "===========");
			for(int i = 0; i < historicoExtrato.size(); i++) {
				System.out.println("Valor: " + historicoExtrato.get(i));
			}
			System.out.println("==========================================\n");
		}
	}
}
