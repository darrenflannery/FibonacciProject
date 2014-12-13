package ie.gmit;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Fib extends UnicastRemoteObject implements RemoteFib {

	private String fibSequence;
	
	protected Fib() throws RemoteException {
		super();	
	}
	
	public String echo(String input) throws RemoteException {
		return "From Server:" + input;
	}

	public String getFibonacciSequence(int max) throws RemoteException{		
		
		fibSequence = "";
		int index = 0;
		
		for(int i=0; i<max; i++)
		{
			fibSequence += Long.toString(lastFibonacci(index)) + ", ";
			index++;
		}
		
		System.out.println(fibSequence+Thread.currentThread().getName());
		
		return fibSequence;
	}
	
	public long lastFibonacci(int index)
	{
		if(index==0) return 0;
		if(index<=2) return 1;
		
		long fib = lastFibonacci(index-1) + lastFibonacci(index - 2);
		
		return fib;
	}
}