package edu.cibertec.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class ProgramaRMI {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws RemoteException {
		
		try {
			ImplementacionRMI rmi=new ImplementacionRMI();
			//Para abrir el servidor:
			Naming.rebind("//localhost:9094/servi", rmi);
			System.out.println("RMI Ejecutando");
				
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		

	}

}
