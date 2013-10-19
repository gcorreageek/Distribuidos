package edu.cibertec.sockets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import edu.cibertec.beans.Usuario;

public class frmcliente extends JFrame implements ActionListener,Runnable {	
	JTextField txtmensaje;
	JButton btnenviar,btnzumbido;	
	JComboBox cbousuarios;
	boolean Terminar=true;
	
	List<Usuario> usuarios=new ArrayList<Usuario>();
	public frmcliente(){
		setSize(400,400);
		setLocationRelativeTo(null);		
		setLayout(null);		
		txtmensaje=new JTextField();
		btnenviar=new JButton("Enviar");
		btnzumbido=new JButton("Zumbido");
		txtmensaje.setBounds(10, 10, 300, 20);
		btnenviar.setBounds(10,40,100, 50);
		btnzumbido.setBounds(150,40,100, 50);
		
		cbousuarios=new JComboBox();
		cbousuarios.setBounds(350,10,100,20);
		
		add(cbousuarios);
		
		btnenviar.addActionListener(this);
		btnzumbido.addActionListener(this);		
		add(txtmensaje);
		add(btnenviar);
		add(btnzumbido);
		setVisible(true);
		
		Thread hilo=new Thread(this);
		hilo.start();	
	}	
	private static  void meoto() {
		
	}
	public static void main(String[] args) {
		frmcliente f=new frmcliente();
//		meoto();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnenviar){
			try {
				Socket cli=new Socket("192.168.1.35", 2222);
				
				ObjectOutputStream flujo=new ObjectOutputStream(cli.getOutputStream());
				Usuario usu=new Usuario();
				usu.setNick("CHAVO");
				usu.setMensaje(txtmensaje.getText().toString());
				usu.setIpdestino("");
				
				flujo.writeObject(usu);
				
				cli.close();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error "+ex.getMessage());
			}
		} 
	}
	@Override
	public void run() {
		try {
			ServerSocket serv=new ServerSocket(3333);
			Socket cli;
			String msg;
			while(Terminar) {				
				cli = serv.accept();
				ObjectInputStream flujo=new ObjectInputStream(cli.getInputStream());
				List<Usuario> usus=null;
				try {
					usus=(List<Usuario>)flujo.readObject();	
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Alguien mando error "+e.getMessage());					
				}
				
				
//				usu.setIporigen(cli.getInetAddress().getHostAddress());				
//				msg=usu.getMensaje(); 
//				StringTokenizer stk = new StringTokenizer(msg, ",");
//				while(stk.hasMoreTokens()){
//					Usuario e = new Usuario();
//					e.setNick(stk.nextToken());
//					usuarios.add(e); 
//		        } 
				usuarios= usus;
				LlenarUsuarios();
				 
				
//				txtmensajes.append("\n" + cli.getInetAddress()+ " : "+ msg);
				 
				cli.close();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	
	public void LlenarUsuarios(){
		cbousuarios.removeAllItems();
		for(Usuario u:usuarios){
			cbousuarios.addItem(u.getNick());
		}
	}
	
}
