package paqueteDatos;

import java.io.Serializable;

public class DatosEntradaSalida implements Serializable{
	private static final long serialVersionUID = -5174402298203499969L;
	private String ipDestino,mensaje,remitente;
	
	public DatosEntradaSalida(String ipDestino, String mensaje,String remitente){
		setIpDestino(ipDestino);
		setMensaje(mensaje);
		setRemitente(remitente);
	}
	
	public String getIpDestino() {
		return ipDestino;
	}

	public void setIpDestino(String idDestino) {
		this.ipDestino = idDestino;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getRemitente() {
		return remitente;
	}

	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}

	
}
