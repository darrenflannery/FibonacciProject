package ie.gmit;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;


public class FibService {

	private LinkedList <FibRequest> inQueue = new LinkedList<FibRequest>();
	private Map <Integer, String> outQueue = new HashMap<Integer, String>();
	
	public int add(int max){
		Random rand = new Random();
		int random = rand.nextInt(1000000000);
		inQueue.addLast(new FibRequest(random, max));
		return random;
	}
	
	public String getResult(int jobNumber){
		if(outQueue.containsKey(jobNumber)){
			String result = outQueue.get(jobNumber);
			outQueue.remove(jobNumber);
			return result;
		}
		return null;
	}		
	
	
	
	//RMI Client Call to Remote Object
	public void rmiCall() throws MalformedURLException, RemoteException, NotBoundException{

		new Thread(new Runnable(){
			public void run(){
				try {	
					RemoteFib service =  (RemoteFib) Naming.lookup("rmi://localhost:1099/FibService");
					System.out.println("--" + service.echo("Hey Server: ") + " " + service.getClass().getName());
				
					//stores first object in inQueue in temp and then remove object from inqueue
					//FibRequest temp = inQueue.get(0);
					//inQueue.remove(0);
					
					//processes first object in queue on a new thread in remote method of remote object
					String fibSequence = service.getFibonacciSequence(inQueue.get(0).getMax());

					int jobNo = inQueue.get(0).getJobNumber();
					
					inQueue.remove(0);

					//add to outQueue
					outQueue.put(jobNo, fibSequence);
					
					} catch (RemoteException | MalformedURLException | NotBoundException e) {
						e.printStackTrace();
					}
				
					System.out.println(outQueue);
			}
		}).start();
	}
	
}
