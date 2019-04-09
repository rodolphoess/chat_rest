package chat_server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Chat extends UnicastRemoteObject implements ChatInterface {

	private static final long serialVersionUID = 1L;

	private String nome;
	private ChatInterface cliente = null;
	private int numeroMensagem = -1;

	private List<ChatInterface> clientes = new ArrayList<ChatInterface>();

	public Chat(String nome) throws RemoteException {
		this.nome = nome;
	}

	@Override
	public int getNumeroMensagem() throws RemoteException {
		return numeroMensagem;
	}

	@Override
	public List<ChatInterface> getClientes() throws RemoteException {
		return clientes;
	}

	@Override
	public String getNome() throws RemoteException {
		return this.nome;
	}

	@Override
	public void enviar(String msg) throws RemoteException {
		System.out.println(msg);
		numeroMensagem += 1;

	}

	@Override
	public void novoCliente(ChatInterface cliente) throws RemoteException {
		enviar("[" + cliente.getNome() + "] está no chat!");
		clientes.add(cliente);

	}

	@Override
	public void removerCliente(ChatInterface cliente) throws RemoteException {
		enviar("[" + cliente.getNome() + "] saiu do chat!");
		clientes.remove(cliente);

	}

	@Override
	public void atualizarClientes() throws RemoteException {
		List<ChatInterface> listaAuxiliar = clientes;

		for (ChatInterface chat : listaAuxiliar) {
			if (chat.equals(null)) {
				removerCliente(chat);
			}
		}

	}

}
