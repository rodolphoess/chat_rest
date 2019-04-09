package chat_client;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import chat_server.ChatInterface;

public class Chat implements ChatInterface {

	private static final long serialVersionUID = 1L;
	
	public String name;
	public ChatInterface cliente=null;
	public int numMensagem=-1;

	private List<ChatInterface> clientes = new ArrayList<ChatInterface>();
 
	public Chat(String n)  throws RemoteException { 
		this.name=n;   
	}
	public String getNome() throws RemoteException {
		return this.name;
	}

	public List<ChatInterface> getClientes() throws RemoteException{
		return clientes;
	}

	public int getNumeroMensagem() throws RemoteException{
		return numMensagem;
	}
 
	public void enviar(String s) throws RemoteException{
		System.out.println(s);
		numMensagem++;
	}	

	public void novoCliente(ChatInterface cliente) throws RemoteException {
		enviar("["+cliente.getNome()+"] entá no chat!");
		clientes.add(cliente);
	}

	public void removerCliente(ChatInterface cliente) throws RemoteException{
		enviar("["+cliente.getNome()+"] saiu do chat!");
		clientes.remove(cliente);
	}

	public void atualizarClientes() throws RemoteException{
		List<ChatInterface> aux = clientes;
		for(ChatInterface c:aux){
			if(c.equals(null)){
		    	removerCliente(c);				
		    }
		 }
	}

}
