package pojos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RegistroEntradaSalida {
	private SimpleIntegerProperty idRegistro = new SimpleIntegerProperty();
	private SimpleStringProperty nombreEmpleado = new SimpleStringProperty();
	private SimpleStringProperty tipoRegistro = new SimpleStringProperty();
	private SimpleStringProperty fechaRegistro = new SimpleStringProperty();
	
	public RegistroEntradaSalida(String nombreEmpleado, String tipoRegistro, int idRegistro,String fechaRegistro){
		
		setNombreEmpleado(nombreEmpleado);
		setTipoRegistro(tipoRegistro);
		setIdRegistro(idRegistro);
		setFechaRegistro(fechaRegistro);
	}
	
	public void setFechaRegistro(String fechaRegistro){
		this.fechaRegistro.set(fechaRegistro);
	}
	
	public String getFechaRegistro(){
		return fechaRegistro.get();
	}
	
	public void setNombreEmpleado(String nombreEmpleado){
		this.nombreEmpleado.set(nombreEmpleado);
		
	}
	
	public String getNombreEmpleado(){
		return nombreEmpleado.get();
	}
	
	public void setIdRegistro(int idRegistro){
		this.idRegistro.set(idRegistro);
	}
	
	public int getIdRegistro(){
		return idRegistro.get();
	}
	
	public void setTipoRegistro(String tipoRegistro){
		this.tipoRegistro.set(tipoRegistro);
	}
	
	public String getTipoRegistro(){
		return tipoRegistro.get();
	}
}
