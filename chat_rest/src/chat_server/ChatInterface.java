package chat_server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ChatInterface extends Remote {
	
	public String getNome() throws RemoteException;

	public List<ChatInterface> getClientes() throws RemoteException;

	public int getNumeroMensagem() throws RemoteException;

	public void enviar(String msg) throws RemoteException;

	public void novoCliente(ChatInterface cliente) throws RemoteException;

	public void removerCliente(ChatInterface cliente) throws RemoteException;

	public void atualizarClientes() throws RemoteException;
}
