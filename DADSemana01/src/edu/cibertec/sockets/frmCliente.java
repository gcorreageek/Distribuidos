package edu.cibertec.sockets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class frmCliente extends JFrame implements ActionListener{
	
	JTextField txtMensaje;
	JButton btnEnviar;
	JButton btnSunbido;
	
	public frmCliente(){
		setSize(400, 400);
		setLocationRelativeTo(null);//centrar formulario y salga en el centro.
		setLayout(null);//es para obtener libremente los objetos creados
		//instanciando los controles;
		txtMensaje=new JTextField();
		btnEnviar=new JButton("Enviar",new ImageIcon("../DADSemana01/images/Alerta.png"));
		btnSunbido = new JButton();
		btnSunbido.setBounds(150,40,150,50);
		btnSunbido.setText("Sumbate");
		txtMensaje.setBounds(10,10,300,20);
		add(txtMensaje);
		btnEnviar.setBounds(10, 40, 150, 50);
		add(btnEnviar);
		btnEnviar.addActionListener(this);
		add(btnSunbido);
		btnSunbido.addActionListener(this);
		
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		frmCliente f=new frmCliente();
				
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnEnviar)
		{
			try 
			{
				//Socket cli=new Socket("10.40.28.150", 9090);
//				Socket cli=new Socket("10.40.28.172", 9081);
				Socket cli=new Socket("127.0.0.1", 9081);
				DataOutputStream flujo=new DataOutputStream(cli.getOutputStream()); //Envia datos
				flujo.writeUTF(txtMensaje.getText().toString());
				
				cli.close();
				
				
				
			} catch (Exception ex) {
			 JOptionPane.showMessageDialog(null, "Mensaje de Error" + ex.getMessage());
			}
			
		}
		
		if(e.getSource()==btnSunbido)
		{
			try 
			{ 
				Socket cli=new Socket("127.0.0.1", 9081);
				DataOutputStream flujo=new DataOutputStream(cli.getOutputStream()); //Envia datos
				flujo.writeUTF("ZUMBAR");
				
				cli.close();
				
				
				
			} catch (Exception ex) {
			 JOptionPane.showMessageDialog(null, "Mensaje de Error" + ex.getMessage());
			}
			
		}
		
	}
	
	

}
