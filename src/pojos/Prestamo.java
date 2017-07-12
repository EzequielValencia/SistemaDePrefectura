package pojos;

import javafx.beans.property.SimpleStringProperty;

public class Prestamo {
	private SimpleStringProperty nombreArticulo = new SimpleStringProperty();
	private SimpleStringProperty categoriaArticulo = new SimpleStringProperty();
	private SimpleStringProperty fechaPrestamo = new SimpleStringProperty();
	private SimpleStringProperty fechaDevolucion = new SimpleStringProperty();
	private SimpleStringProperty nombreSolicitante = new SimpleStringProperty();
	private SimpleStringProperty nombrePrefecto = new SimpleStringProperty();
	private SimpleStringProperty grupoRegistrado = new SimpleStringProperty();
	
	public Prestamo(String nombreArticulo, String categoriaArticulo,String fechaPrestamo,String nombrePrefecto
			,String grupoRegistrado,String nombreSolicitante){
		
		setNombreArticulo(nombreArticulo);
		setCategoriaArticulo(categoriaArticulo);
		setFechaPrestamo(fechaPrestamo);
		setNombrePrefecto(nombrePrefecto);
		setGrupoRegistrado(grupoRegistrado);
		setNombreSolicitante(nombreSolicitante);
	}
	
	public void setGrupoRegistrado(String grupoRegistrado){
		this.grupoRegistrado.set(grupoRegistrado);
	}
	
	public String getGrupoRegistrado(){
		return grupoRegistrado.get();
	}
	
	public String getNombreArticulo() {
		return nombreArticulo.get();
	}
	
	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo.set( nombreArticulo );
	}
	
	public String getCategoriaArticulo() {
		return categoriaArticulo.get();
	}
	
	public void setCategoriaArticulo(String categoriaArticulo) {
		this.categoriaArticulo.set(categoriaArticulo);
	}
	
	public String getFechaPrestamo() {
		return fechaPrestamo.get();
	}
	
	public void setFechaPrestamo(String fechaPrestamo) {
		this.fechaPrestamo.set( fechaPrestamo );
	}
	
	public String getFechaDevolucion() {
		return fechaDevolucion.get();
	}
	
	public void setFechaDevolucion(String fechaDevolucion) {
		this.fechaDevolucion.set( fechaDevolucion );
	}
	
	public String getNombreSolicitante() {
		return nombreSolicitante.get();
	}
	
	public void setNombreSolicitante(String nombreSolicitante) {
		this.nombreSolicitante.set( nombreSolicitante);
	}
	
	public String getNombrePrefecto() {
		return nombrePrefecto.get();
	}
	
	public void setNombrePrefecto(String nombrePrefecto) {
		this.nombrePrefecto.set( nombrePrefecto );
	}
	
	
}
