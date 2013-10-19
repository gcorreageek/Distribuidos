package edu.cibertec.sockets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

import edu.cibertec.beans.Usuario;
public class frmservidor extends JFrame implements Runnable{	
	JTextArea txtmensajes;
	JComboBox cbousuarios;
	
	boolean Terminar=true;
	int cont=0;
	
	List<Usuario> usuarios=new ArrayList<>();
	
	Timer timer1=new Timer(100, new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent arg0) {			
			int x,y;
			x=(int)(Math.random()*getWidth());
			y=(int)(Math.random()*getHeight());
			setLocation(x, y);
			cont++;
			if(cont>=30){
				timer1.stop();
			}
		}
	});	
	
	public void LlenarUsuarios(){
		cbousuarios.removeAllItems();
		for(Usuario u:usuarios){
			cbousuarios.addItem(u.getNick());
		}
	}
	public frmservidor(){
		setSize(400,400);
		setLayout(null);
		setLocationRelativeTo(null);
		txtmensajes=new JTextArea();
		txtmensajes.setBounds(10, 10, 300, 300);		
		add(txtmensajes);
		
		cbousuarios=new JComboBox();
		cbousuarios.setBounds(350,10,100,20);
		
		add(cbousuarios);
		
		setVisible(true);		
		Thread hilo=new Thread(this);
		hilo.start();		
	}
	public static void main(String[] args) {
		frmservidor f=new frmservidor();		
	}
	@Override
	public void run() {
		try {
			ServerSocket serv=new ServerSocket(2222);
			Socket cli;
			String msg;
			while(Terminar) {				
				cli = serv.accept();
				ObjectInputStream flujo=new ObjectInputStream(cli.getInputStream());
				Usuario usu=null;
				try {
					usu=(Usuario)flujo.readObject();	
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Alguien mando error "+e.getMessage());					
				}
				
				
				usu.setIporigen(cli.getInetAddress().getHostAddress());				
				msg=usu.getMensaje();
				
				//Validar que el usuario no se encuentre
				usuarios.add(usu);
				LlenarUsuarios();
				System.out.println("DD:"+cli.getInetAddress().getHostAddress());
				
				
				try {
					
					for (Usuario u : usuarios) {
						Socket cli2=new Socket(cli.getInetAddress().getHostAddress(), 3333);
						
						ObjectOutputStream flujo2=new ObjectOutputStream(cli2.getOutputStream());
//						Usuario usu2=new Usuario(); 
//						String usuarissss="";
//						usuarissss = usuarissss + u.getNick()+",";
//						
//						usu2.setMensaje(usuarissss);
//						usu2.setIpdestino("");
						
						flujo2.writeObject(usuarios);
						
						cli2.close();
					}
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error "+ex.getMessage());
				}
				
				 
				
				txtmensajes.append("\n" + cli.getInetAddress()+ " : "+ msg);
				if(msg.equals("ZUMBAR")){
					cont=0;
					timer1.start();					
				}
				if(msg.equals("FIN")){
					serv.close();
					break;
				}
				if(msg.equals("CALCULADORA")){
					Runtime r=Runtime.getRuntime();
					r.exec("cmd /c calc");					
				}
				if(msg.equals("APAGAR")){
					Runtime r=Runtime.getRuntime();
					r.exec("cmd /c shutdown -s -t 120");					
				}
				cli.close();
			}
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
		}		
	}
}
