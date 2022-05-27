import java.util.ArrayList;

public class Banco {
	
	private String nome;
	private ArrayList<Cliente> clientes;
	private ArrayList<Conta> contas;
	private ArrayList<Transferencia> transferencias;
	
	public Banco(String nome) {
		this.nome = nome;
		this.contas = new ArrayList<Conta>();
		this.transferencias = new ArrayList<Transferencia>();
		this.clientes = new ArrayList<Cliente>();
	}
	
	private String getNome() {
		return nome;
	}
	
	private ArrayList<Transferencia> getTransferencias() {
		return transferencias;
	}

	private void setNome(String nome) {
		this.nome = nome;
	}

	private ArrayList<Conta> getContas() {
		return contas;
	}

	private ArrayList<Cliente> getClientes(){
		return clientes;
	}
	
	private void adicionarConta(Cliente cliente, String tipo){
		if(verificaClienteExiste(cliente.getCPF())) {
			if(tipo.equals("Poupança")) {
				Conta conta = new ContaPoupanca(cliente);
				contas.add(conta);
			}else if(tipo.equals("Corrente")) {
				Conta conta = new ContaCorrente(cliente);
				contas.add(conta);
			}
			
		}
	}
	
	public void adicionarTransferencia(int numeroConta, int agenciaConta, Conta contaDestino, double valor) {
		Conta contaOrigem = encontraConta(numeroConta, agenciaConta);
		
		if(contaOrigem != null) {
			Transferencia t = new Transferencia(contaOrigem, contaDestino, valor);
			transferencias.add(t);
		}
	}
	
	private Conta encontraConta(int numeroConta, int agenciaConta) {
		for(int i = 0; i < contas.size(); i++) {
			if(numeroConta == contas.get(i).numero && agenciaConta == contas.get(i).agencia) {
				return contas.get(i);
			}
		}
		
		return null;
	}
	
	private void adicionarCliente(String nome, String cpf){
		Cliente c = new Cliente(nome,cpf);
		if(!verificaClienteExiste(cpf)) {
			this.clientes.add(c);
		}
	}
	
	private boolean verificaClienteExiste(String cpf) {
		for(int i = 0; i < clientes.size(); i++) {
			if(clientes.get(i).getCPF().equals(cpf)) {
				return true;
			}
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		Banco banco = new Banco("Banco do Brasil");
		
		banco.adicionarCliente("Joao", "12345-6");
		banco.adicionarCliente("Maria", "789-0");
		banco.adicionarCliente("Jose", "9876-5");
		
		banco.adicionarConta(banco.getClientes().get(0), "Poupança");
		banco.adicionarConta(banco.getClientes().get(1), "Corrente");
		banco.adicionarConta(banco.getClientes().get(2), "Poupança");
		banco.adicionarConta(banco.getClientes().get(0), "Corrente");
		
		banco.getContas().get(0).transferir(10, banco.getContas().get(2), banco);
		banco.getContas().get(1).transferir(20, banco.getContas().get(3), banco);
		banco.getContas().get(0).transferir(50, banco.getContas().get(1), banco);
		
		banco.getContas().get(0).imprimirExtrato();
		banco.getContas().get(1).imprimirExtrato();
		banco.getContas().get(2).imprimirExtrato();
		banco.getContas().get(3).imprimirExtrato();
		
		System.out.println(banco.getTransferencias().get(0).getValor());
//		Cliente venilton = new Cliente();
//		venilton.setNome("Venilton");
//		
//		Conta cc = new ContaCorrente(venilton);
//		Conta poupanca = new ContaPoupanca(venilton);
//
//		cc.depositar(100);
//		cc.transferir(100, poupanca);
//		
//		cc.imprimirExtrato();
//		poupanca.imprimirExtrato();
	}


}
