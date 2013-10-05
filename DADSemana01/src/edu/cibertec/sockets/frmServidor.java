package edu.cibertec.sockets;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class frmServidor extends JFrame implements Runnable{

	JTextArea txtMensaje;
	Boolean terminar=true;
	public frmServidor()
	{
		setSize(400, 400);
		setLayout(null);
		setLocationRelativeTo(null);
		
		txtMensaje=new JTextArea();
		txtMensaje.setBounds(10, 10, 360, 340);
		add(txtMensaje);
		setVisible(true);
		Thread hilo=new Thread(this);
		
		hilo.start();
		
		
	}
	
	public static void main(String[] args) {
		frmServidor f=new frmServidor();
		
	}

	@Override
	public void run() {
		try {
			ServerSocket serv=new ServerSocket(9081);
			Socket cli;
			
			while(terminar){
				cli=serv.accept();
				DataInputStream flujo=new DataInputStream(cli.getInputStream());
				txtMensaje.append("\n"+cli.getInetAddress()+" - "+flujo.readUTF());
				cli.close();
				
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
			}
		
	}

}
