package pojos;

public class Usuario {
	private static int id;
	private static String nombre;
	private static String apellidoPaterno;
	private static String apellidoMaterno;
	private static String usuario;
	private static String contrasenia;
	private static String categoria;
	
	public Usuario(int id, String nombre, String apellidoPaterno, String apellidoMaterno, String usuario,
			String contrasenia,String categoria) {
		
		setId(id);
		setNombre(nombre);
		setApellidoMaterno(apellidoMaterno);
		setApellidoPaterno(apellidoPaterno);
		setUsuario(usuario);
		setContrasenia(contrasenia);
		setCategoria(categoria);
	}
	public static String getCategoria() {
		return categoria;
	}
	public static void setCategoria(String categoria1) {
		categoria = categoria1;
	}
	public static int getId() {
		return id;
	}
	public static void setId(int id1) {
		id = id1;
	}
	public static String getNombre() {
		return nombre;
	}
	public static void setNombre(String nombre1) {
		nombre = nombre1;
	}
	public static String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public static void setApellidoPaterno(String apellidoPaterno1) {
		apellidoPaterno = apellidoPaterno1;
	}
	public static String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public static void setApellidoMaterno(String apellidoMaterno1) {
		apellidoMaterno = apellidoMaterno1;
	}
	public static String getUsuario() {
		return usuario;
	}
	public static void setUsuario(String usuario1) {
		usuario = usuario1;
	}
	public static String getContrasenia() {
		return contrasenia;
	}
	public static void setContrasenia(String contrasenia1) {
		contrasenia = contrasenia1;
	}
}
