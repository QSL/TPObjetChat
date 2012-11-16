import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatClient {
	private static final String host = "127.0.0.1";
	private static final int port = 8080;
	private static Registry registry;
	public ChatClient() {
		
	}
	public static void main(String args[]) {
	    int port = 8080;    String URL;
	    boolean connected = false;
	    String cmd_1 = "";
	    BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));
	    
	try {
			System.out.print("Chat client !");
			registry = LocateRegistry.getRegistry(host, port);
			System.out.print("getting registry...");
			ChatInterface serv = (ChatInterface) Naming.lookup("//" + host + ":" + port + "/TPChat");
			System.out.print("Launching tests...");
			System.out.print("Quel est votre nom ?\n");
			String nom = entree.readLine();
			serv.connect(0, nom);
			serv.connect(1, "Pascal le grand frère");
			connected = true;
			while (connected) {
				System.out.print("Que voulez vous faire (message, who, bye ou receive)?:\n");
				String message = entree.readLine();
				String cmd[] = message.split(" ");
				cmd_1 = cmd[0];
				switch(cmd_1) {
				case "message":
					System.out.print("Veuillez rentrer votre message:\n");
					String message_entree = entree.readLine();
					serv.send(nom, message_entree);
				break;
				case "who":
					System.out.print(serv.who());
				break;
				case "bye":
					serv.bye(0);
					connected = false;
				break;
				case "receive":
					System.out.print(serv.receive());
				break;
				}
				System.out.print(serv.receive());
				System.out.print("Fin de votre Cycle\n\n");
			}
	    }
	catch (Exception exc) { 
	    	
	    }
	}
}