/**
 * 
 */
/**
 * @author Win10
 *
 */
package chat_client;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import chat_server.ChatInterface;

@Path("/chat")
public class ChatClientResources {
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	public String iniciarChat() {
		
		try {

    		List<ChatInterface> otherClients = new ArrayList<ChatInterface>();

	    	@SuppressWarnings("resource")
			Scanner s=new Scanner(System.in);
	    	System.out.println("Digite o nome:");
	    	String name=s.nextLine().trim();		    		    	
	    	ChatInterface cliente = new Chat(name);

	    	ChatInterface server = (ChatInterface)Naming.lookup("rmi://localhost:1099/ChatServer");
	    	String msg="";

	    	System.out.println("Chat aberto!");
	    	server.novoCliente(cliente);

	    	while(true){
	    		msg=s.nextLine().trim();
	    		msg="Mensagem "+server.getNumeroMensagem()+" - "+cliente.getNome()+": "+msg;		    		
    			server.enviar(msg);
    			
    			server.atualizarClientes();
    			
    			otherClients = server.getClientes();
    			for(ChatInterface c:otherClients){
    				if(!c.equals(cliente))
    					c.enviar(msg);
    			}
	    	}

    	} catch (Exception e) {
    		System.out.println("Chat Erro: " + e);
    	}
		
		return "";
	}
}