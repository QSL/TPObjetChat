import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;


public class ServerImplement extends UnicastRemoteObject implements ChatInterface {
	public HashMap<String, String> list_messages;
	public HashMap<Integer, String> list_users;
	
	private static final String host = "127.0.0.1";
	private static final int port=8080;
	
	protected ServerImplement() throws RemoteException {
		super();
		this.list_users = new HashMap<Integer, String>();
		this.list_messages = new HashMap<String, String>();
	}
	public void connect(Integer id, String nom) throws RemoteException {
		System.out.print("Trying to connect... " + id +"\n");
		list_users.put(id, nom);
		System.out.print(id + " added to list.\n");
	}
	public void send(String id, String msg) throws RemoteException {
		System.out.print("New message from " + id + " : " + msg + "\n");
		this.list_messages.put(id, msg);
	}
	public void bye(int id) throws RemoteException {
		if (list_users.containsKey(id)) {
			System.out.print(id + " s'en va... Au revoir !\n");
			this.list_users.remove(id);
		}
		else throw new RemoteException("Cet user n'est pas connecté !");
	}
	public String receive() throws RemoteException {
		System.out.print("receiving messages \n");
		return (this.list_messages.values().toString());
	}
	public String who() throws RemoteException {
		String list_users_affiche = "";
		for (int i = 0; i < this.list_users.size() ; i++) {
			list_users_affiche += this.list_users.get(i) + "\n";
		}
		return list_users_affiche;
	}
	public static void main(String args[]) {
		try {
			Registry registry = LocateRegistry.createRegistry(port);
			ServerImplement serv = new ServerImplement();
			Naming.rebind("//" + host + ":" + port + "/TPChat", serv);
			System.out.print("Server Implemented.\n");
		}
		catch (Exception e) {
			
		}
	}
}
