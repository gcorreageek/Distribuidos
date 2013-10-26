package edu.cibertec.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ImplementacionRMI extends UnicastRemoteObject implements InterfaceRMI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ImplementacionRMI() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String Saludar() throws RemoteException {
		// TODO Auto-generated method stub
		return "Hola Mundo";
	}

	@Override
	public int Suma(int a, int b) throws RemoteException {
		// TODO Auto-generated method stub
		return a+b;
	}

	@Override
	public String Grabar(String cod, String nom, int pension)
			throws RemoteException {
		// TODO Auto-generated method stub
		return "Se Grabo Correctamen";
	}

}
