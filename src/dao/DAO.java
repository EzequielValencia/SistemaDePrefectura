package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import pojos.Usuario;




public class DAO {
	private static Connection conexion;
	
	private static  ResultSet resultadoConsulta;
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

	public static Vector<Usuario> verificaUsuario(String usuario,String password){
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
}
