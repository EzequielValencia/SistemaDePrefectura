package pojos;


import javafx.beans.property.SimpleStringProperty;

public class Alumno extends Persona {
	private SimpleStringProperty grupo = new SimpleStringProperty();
	private SimpleStringProperty turno = new SimpleStringProperty();
	private SimpleStringProperty grado = new SimpleStringProperty();
	public Alumno(String nombre, String apellidoPaterno, String apellidoMaterno, 
			String codigo, int id, String grupo, String turno,String grado) {
		super(nombre, apellidoPaterno, apellidoMaterno, codigo, id);
		// TODO Auto-generated constructor stub
		setGrado(grado);
		setGrupo(grupo);
		setTurno(turno);
	}
		
	public String getGrupo() {
		return grupo.get();
	}

	public void setGrupo(String grupo) {
		this.grupo.set(grupo);
	}

	public String getTurno() {
		return turno.get();
	}

	public void setTurno(String turno) {
		this.turno.set(turno);
	}

	public void setGrado(String grado){
		this.grado.set(grado);
	}
		
	public String getGrado(){
		return grado.get();
	}
}
