package edu.cibertec.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceRMI extends Remote {
//Defino provedeimientos o funciones
	
	public String Saludar() throws RemoteException;
	public int Suma (int a, int b) throws RemoteException;
	public String Grabar(String cod, String nom, int pension) throws RemoteException;
	
	
}
