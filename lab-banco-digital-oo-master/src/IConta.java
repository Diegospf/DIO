
public interface IConta {
	
	void sacar(double valor);
	
	void depositar(double valor);
	
	void transferir(double valor, Conta contaDestino, Banco banco);
	
	void imprimirExtrato();
}
