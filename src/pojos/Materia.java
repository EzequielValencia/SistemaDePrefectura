package pojos;


public class Materia {
	private String nombre;
	private int id;
	private int grado;
	public Materia(String nombre,int id,int grado){
		setNombre(nombre);
		setId(id);
		setGrado(grado);
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setGrado(int grado){
		this.grado = grado;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public int getId(){
		return id;
	}
	
	public int getGrado(){
		return grado;
	}
}
