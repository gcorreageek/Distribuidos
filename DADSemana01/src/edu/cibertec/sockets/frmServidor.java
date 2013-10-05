package edu.cibertec.sockets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class frmServidor extends JFrame implements Runnable{

	JTextArea txtMensaje;
	Boolean terminar=true;
	int cont=0;
	
	Timer timer1 = new Timer(100, new ActionListener() { 
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int x,y;
			x=(int) (Math.random()*getWidth());
			y=(int) (Math.random()*getHeight());
			setLocation(x, y);
			cont++;
			if(cont>=30){
				timer1.stop();
				cont = 0;
			}
			
		}
	});
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
//		timer1.start();
		
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
				String mensaje =  flujo.readUTF();
				txtMensaje.append("\n"+cli.getInetAddress()+" - "+mensaje);
				if("ZUMBAR".equals(mensaje) ){
					timer1.start();
				}
				if(mensaje.equals("FIN")){
					serv.close();
					break;
				}
				
				cli.close();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
			}
		
	}

}
