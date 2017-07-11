package pojos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public abstract class Persona {
	private SimpleStringProperty nombre = new SimpleStringProperty();
	private SimpleStringProperty apellidoPaterno = new SimpleStringProperty();
	private SimpleStringProperty apellidoMaterno = new SimpleStringProperty();
	private SimpleStringProperty codigo = new SimpleStringProperty();
	private SimpleIntegerProperty id = new SimpleIntegerProperty();
	private SimpleStringProperty nombreCompleto = new SimpleStringProperty();
	/**
	 * 
	 * @param nombre Nombre de la persona
	 * @param apellidoPaterno Apellido Paterno de la persona
	 * @param apellidoMaterno 
	 * @param codigo
	 * @param id
	 */
	public Persona(String nombre, String apellidoPaterno,
			String apellidoMaterno, String codigo, int id) {
		
		setNombre(nombre);
		setApellidoPaterno(apellidoPaterno);
		setApellidoMaterno(apellidoMaterno);
		setNombreCompleto(getNombre()+' '+getApellidoPaterno()+' '+getApellidoMaterno());
		setCodigo(codigo);
		setId(id);
		
	}
	
	public String getNombre() {
		return nombre.get();
	}
	
	public void setNombreCompleto(String nombreCompleto){
		this.nombreCompleto.set(nombreCompleto);
	}
	
	public String getNombreCompleto(){
		return nombreCompleto.get();
	}
	
	public void setNombre(String nombre) {
		this.nombre.set( nombre );
	}
	
	public String getApellidoPaterno() {
		return apellidoPaterno.get();
	}
	
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno.set(apellidoPaterno);
	}
	
	public String getApellidoMaterno() {
		return apellidoMaterno.get();
	}
	
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno.set(apellidoMaterno);
	}
	
	public String getCodigo() {
		return codigo.get();
	}
	
	public void setCodigo(String codigo) {
		this.codigo.set(codigo);
	}
	
	public int getId() {
		return id.get();
	}
	
	public void setId(int id) {
		this.id.set(id);
	}
	
}
