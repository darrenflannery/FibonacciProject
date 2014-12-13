package ie.gmit;



import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class FibonacciServlet extends HttpServlet {

	private String result;
	private int jobNumber=0;
	int cnt = 0;

	private static final long serialVersionUID = 1L;
	
	//create an instance of fibService where the in and out queue are implemented 
	private FibService fibService = new FibService();

	  public void init(ServletConfig servletConfig) throws ServletException{

		  
		    //create registry - server functionality implementation
		    Registry registry;
			try {
				RemoteFib fib = new Fib();
				registry = LocateRegistry.createRegistry(1099);
				registry.rebind("FibService", fib);
			} catch (RemoteException e) {
				e.printStackTrace();
			}

			System.out.println("Server Ready");
			
			
	  }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws RemoteException, MalformedURLException, ServletException, IOException {	

	     
		String length = request.getParameter("length");


		//get specified length and convert to integer
		int fibLength = Integer.parseInt(length);
		
		//call add method to add to inQueue and get number number back
		if(jobNumber==0){
			jobNumber = fibService.add(fibLength);

				//rmi call from fibService
				try {
					fibService.rmiCall();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

			
		Timer timer = new Timer(); 
        TimerTask task = new TimerTask() {  
        
        
			public void run() {
				if(cnt==0){
					result = fibService.getResult(jobNumber);
				}
				cnt = 1;
				if(result==null){
					System.out.println("Processing");
				}
				
				else{
				request.setAttribute("result", result);
				 System.out.println("Done");
				 timer.cancel();
				}
        }

        };
        timer.schedule(task,10000,10000);
		
		
		request.setAttribute("length", length);
		request.setAttribute("jobNumber", jobNumber);
		RequestDispatcher resdispatcher = request.getRequestDispatcher("/Result.jsp");  
		resdispatcher.forward(request, response);
		


	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
 	}
	
}
