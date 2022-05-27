
public class Transferencia {

	
	private static int IDTrans = 1;
	
	private Conta contaDestino;
	private double valor;
	private Conta contaOrigem;
	private int id;
	
	public Transferencia(Conta contaOrigem, Conta contaDestino, double valor) {
		this.contaDestino = contaDestino;
		this.valor = valor;
		this.contaOrigem = contaOrigem;
		this.id = IDTrans;
		
		IDTrans++;
		
	}

	public Conta getContaOrigem() {
		return contaOrigem;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	public double getValor() {
		return valor;
	}

	public int getId() {
		return id;
	}
	
	
}
