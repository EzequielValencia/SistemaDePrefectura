package pojos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RegistroHorarioExamen {
	private SimpleStringProperty nombreMateria=new SimpleStringProperty();
	private SimpleIntegerProperty idRegistro = new SimpleIntegerProperty();
	private SimpleStringProperty fechaAplicacion = new SimpleStringProperty();
	private SimpleStringProperty horaInicio = new SimpleStringProperty();
	private SimpleStringProperty horaFin = new SimpleStringProperty();
	private SimpleStringProperty grado = new SimpleStringProperty();
	private SimpleStringProperty grupo = new SimpleStringProperty();
	private SimpleStringProperty turno = new SimpleStringProperty();
	
	public String getTurno() {
		return turno.get();
	}
	
	public void setTurno(String turno) {
		this.turno.set( turno );
	}
	
	public String getGrupo() {
		return grupo.get();
	}
	
	public void setGrupo(String grupo) {
		this.grupo.set( grupo );
	}
	
	public String getGrado() {
		return grado.get();
	}
	public void setGrado(String grado) {
		this.grado.set( grado );
	}
	
	public String getNombreMateria() {
		return nombreMateria.get();
	}
	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria.set( nombreMateria );
	}
	public int getIdRegistro() {
		return idRegistro.get();
	}
	public void setIdRegistro(int idRegistro) {
		this.idRegistro.set( idRegistro );
	}
	public String getFechaAplicacion() {
		return fechaAplicacion.get();
	}
	public void setFechaAplicacion(String fechaAplicacion) {
		this.fechaAplicacion.set( fechaAplicacion );
	}
	public String getHoraInicio() {
		return horaInicio.get();
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio.set( horaInicio );
	}
	public String getHoraFin() {
		return horaFin.get();
	}
	public void setHoraFin(String horaFin) {
		this.horaFin.set( horaFin );
	}
	public RegistroHorarioExamen(String nombreMateria, int idRegistro,String fechaAplicacion,
			String horaInicio, String horaFin,String grado,String grupo, String turno) {
		setNombreMateria(nombreMateria);
		setIdRegistro(idRegistro);
		setHoraFin(horaFin);
		setHoraInicio(horaInicio);
		setFechaAplicacion(fechaAplicacion);
		setGrado(grado);
		setTurno(turno);
		setGrupo(grupo);
	}
	
	public String toString(){
		return getIdRegistro()+' '+getNombreMateria()+' '+getFechaAplicacion()+' '+getHoraInicio()+' '+getHoraFin()
		+' '+getGrado()+' '+getGrupo()+' '+getTurno();
	}
}
