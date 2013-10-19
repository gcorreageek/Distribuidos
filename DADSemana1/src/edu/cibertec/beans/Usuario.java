package edu.cibertec.beans;

import java.io.Serializable;

public class Usuario implements Serializable {
	
	private String nick;
	private String iporigen;
	private String ipdestino;
	private String mensaje;
	
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getIporigen() {
		return iporigen;
	}
	public void setIporigen(String iporigen) {
		this.iporigen = iporigen;
	}
	public String getIpdestino() {
		return ipdestino;
	}
	public void setIpdestino(String ipdestino) {
		this.ipdestino = ipdestino;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
