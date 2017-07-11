package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import pojos.Alumno;
import pojos.Articulo;
import pojos.Persona;
import pojos.Profesor;
import pojos.Usuario;




public class DAO {
	private static Connection conexion;
	/**
	 * Meotod para conectar a la base de datos. 
	 * Debido a que ya se cuenta con una DB definida dentro de este equipo el codigo
	 * ya incluye la URL de donde se conectara la base de datos
	 */
	public static boolean conectarDB(){
		String servidor = "jdbc:mysql://localhost:3306/sistemaprefectura";
		String usuario = "root";
		String password = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(servidor,usuario,password);
			return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Algo salio mal");
		}
		return false;
	}
	/**
	 * 
	 * @param usuario
	 * @param password
	 * @return
	 */
	public static Vector<Usuario> verificaUsuario(String usuario,String password){
		ResultSet resultadoConsulta;
		String query ="SELECT"
				+ " usuario.id,"
				+ " usuario.nombres,"
				+ "usuario.apellido_paterno,"
				+ "usuario.apellido_materno,"
				+ "usuario.usuario,"
				+ " usuario.contrasenia,"
				+ " usuario.catergoria as categoria"
				+ " FROM "
				+ "usuario"
				+ " WHERE"
				+ " usuario.usuario = ?"
				+ " AND "
				+ "usuario.contrasenia = ?"; 
		Vector<Usuario> usuarios = new Vector<Usuario>();
		try {
			PreparedStatement comando = conexion.prepareStatement(query);
			comando.setString(1, usuario);
			comando.setString(2, password);
			resultadoConsulta = comando.executeQuery();

			while (resultadoConsulta.next()) {
				usuarios.addElement(new Usuario(
						resultadoConsulta.getInt("id"),
						resultadoConsulta.getString("nombres"),
						resultadoConsulta.getString("apellido_paterno"),
						resultadoConsulta.getString("apellido_materno"),
						resultadoConsulta.getString("usuario"),
						resultadoConsulta.getString("contrasenia"),
						resultadoConsulta.getInt("categoria")
						));
			}
			return usuarios;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Articulo consultarArticulo(String codigoArticulo){
		ResultSet resultadoConsulta;
		Articulo articuloEncontrado=null;
		String query = 
				"SELECT "
				+ "articulo.nombre_articulo AS nombre,"
				+"articulo.categoria AS categoria,"
				+ "articulo.codigo as codigo, "
				+ "articulo.descripcion as descripcion, "
				+ "articulo.id as idArticulo, "
				+ "articulo.imagen as localizacionImagen "
				+ "FROM articulo "
				+ "WHERE articulo.codigo = ?";
		PreparedStatement comando;
		try {
			comando = conexion.prepareStatement(query);
			comando.setString(1, codigoArticulo);
			resultadoConsulta = comando.executeQuery();
			while( resultadoConsulta.next() ){
				
				articuloEncontrado = new Articulo(resultadoConsulta.getString("nombre"),
						resultadoConsulta.getString("categoria")
						, resultadoConsulta.getString("codigo"),
						resultadoConsulta.getString("descripcion"),resultadoConsulta.getInt("idArticulo"),
						resultadoConsulta.getString("localizacionImagen")
						);
				
			}
			return articuloEncontrado;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return null;
	}
	
	/**
	 * Busca a un alumno en la base de datos y lo retorna de encontrarlo
	 * @param codigo Codigo del alumno a buscar
	 * @return Alumno encontrado o null si no encuentra.
	 */
	public static Alumno buscaAlumno(String codigo){
		Alumno alumno=null;
		PreparedStatement comando;
		ResultSet resultadoConsulta;
		String query = "SELECT "
				+ "nombre as nombre,"
				+ "apellido_paterno as apellidoPaterno,"
				+ "apellido_materno as apellidoMaterno,"
				+ "codigo as codigo,"
				+ "id as id,"
				+ "grado as grado,"
				+ "grupo as grupo,"
				+ "turno as turno "
				+ "FROM "
				+ "alumnos "
				+ "WHERE "
				+ "codigo = ?";
		
		try {
			comando = conexion.prepareStatement(query);
			comando.setString(1,codigo);
			resultadoConsulta = comando.executeQuery();
			
			while(resultadoConsulta.next()){
				alumno= new Alumno(resultadoConsulta.getString("nombre"),
						resultadoConsulta.getString("apellidoPaterno"),
						resultadoConsulta.getString("apellidoMaterno"),
						resultadoConsulta.getString("codigo"),
						resultadoConsulta.getInt("id"),
						resultadoConsulta.getString("grupo"),
						resultadoConsulta.getString("turno"),
						resultadoConsulta.getString("grado"));
			}
			return alumno;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alumno;
	}
	
	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public static Profesor buscaProfesor(String codigo){
		Profesor profesor = null;
		ResultSet resultadoConsulta;
		PreparedStatement comando;
		String query = "SELECT "
				+ "id,"
				+ "nombre,"
				+ "apellido_paterno,"
				+ "apellido_materno,"
				+ "codigo "
				+ "FROM "
				+ "profesor "
				+ "WHERE "
				+ "codigo = ?";
		try {
			comando = conexion.prepareStatement(query);
			comando.setString(1,codigo);
			resultadoConsulta = comando.executeQuery();
			
			while(resultadoConsulta.next()){
				profesor= new Profesor(resultadoConsulta.getString("nombre"),
						resultadoConsulta.getString("apellido_paterno"),
						resultadoConsulta.getString("apellido_materno"),
						resultadoConsulta.getString("codigo"),
						resultadoConsulta.getInt("id"));
			}
			return profesor;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return profesor;
	}
	
	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public static Persona buscaSolicitante(String codigo){
		Persona solicitante=null;
		if((solicitante = buscaAlumno(codigo))!=null){
			System.out.println("Alumno "+solicitante.getNombreCompleto());
			return solicitante;
		}else if((solicitante=buscaProfesor(codigo))!=null){
			System.out.println("Profesor "+solicitante.getNombreCompleto());
			return solicitante;
		}
		return solicitante;
	}
}
