import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface ChatInterface extends Remote {
	public void connect(Integer id, String nom)  throws RemoteException;
	public void send(String id, String msg)  throws RemoteException;
	public void bye(int id) throws RemoteException;
	public String receive() throws RemoteException;
	public String who() throws RemoteException;
}