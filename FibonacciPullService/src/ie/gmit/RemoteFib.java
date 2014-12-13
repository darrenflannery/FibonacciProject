package ie.gmit;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteFib extends Remote {

	public String echo(String input)throws RemoteException;
	public String getFibonacciSequence(int jobNumber) throws RemoteException;
	
}