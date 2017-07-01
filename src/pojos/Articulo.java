package pojos;

public class Articulo {
	private String categoria;
	private String codigo;
	private String descripcion;
	private int id;
	private String urlImagen;
	
	public Articulo(String categoria, String codigo, String descripcion, int id, String urlImagen) {
		
		setCategoria(categoria);
		setCodigo(codigo);
		setDescripcion(descripcion);
		setId(id);
		setUrlImagen(urlImagen);
	
	}

	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUrlImagen() {
		return urlImagen;
	}
	
	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}
}
