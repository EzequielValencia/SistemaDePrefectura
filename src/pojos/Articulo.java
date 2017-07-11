package pojos;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Articulo {
	private StringProperty categoria = new SimpleStringProperty();
	private StringProperty nombre = new SimpleStringProperty();
	private StringProperty codigo = new SimpleStringProperty();
	private StringProperty descripcion = new SimpleStringProperty();
	private IntegerProperty id = new SimpleIntegerProperty();
	private String urlImagen;
	
	public Articulo(String nombre, String categoria, String codigo, String descripcion, int id, String urlImagen) {
		setCategoria(categoria);
		setNombre(nombre);
		
		setCodigo(codigo);
		setDescripcion(descripcion);
		setId(id);
		setUrlImagen(urlImagen);
	
	}

	public String getNombre(){
		return nombre.get();
	}
	
	public void setNombre(String nombre){
		this.nombre.set(nombre);
	}
	
	public String getCategoria() {
		return categoria.get();
	}
	
	public void setCategoria(String categoria) {
		this.categoria.set(categoria);
	}
	
	public String getCodigo() {
		return codigo.get();
	}
	
	public void setCodigo(String codigo) {
		this.codigo.set( codigo );
	}
	
	public String getDescripcion() {
		return descripcion.get();
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion.set(descripcion);
	}
	
	public int getId() {
		return id.get();
	}
	
	public void setId(int id) {
		this.id.set( id );
	}
	
	public String getUrlImagen() {
		return urlImagen;
	}
	
	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}
}
