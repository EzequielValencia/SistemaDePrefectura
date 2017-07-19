package dao;

import java.io.Reader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Vector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pojos.Alumno;
import pojos.Articulo;
import pojos.Materia;
import pojos.Persona;
import pojos.Profesor;
import pojos.RegistroEntradaSalida;
import pojos.RegistroHorarioExamen;
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
		String password = "root";
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
	 * Verifica la existencia del usuario en la base de datos
	 * @param usuario que se verificara
	 * @param password password del usuario
	 * @return retorna un vector de usuarios
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
	/**
	 * Verifica que exista el articulo en la base de datos
	 * @param codigoArticulo Es el codigo por el que se buscara el articulo
	 * @return Retorna null si no encuentra el articulo. De lo contrario retorna un articulo
	 */
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
	 * Busca al profesor por el codigo que entra como parametro
	 * @param codigo
	 * @return null si no lo encuentra. De lo contrario regresa el profesor encontrado
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
	 * Busca a una persona por su codigo
	 * @param codigo
	 * @return Null si este no se encuentra. De lo contrario regresa La persona localizada.
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

	public static RegistroEntradaSalida existeRegistroEntrada(String codigo){
		PreparedStatement comando;
		ResultSet resultadoConsulta;
		RegistroEntradaSalida registro = null;
		String query = "SELECT "
				+ "registro_entrada_salida.id AS idRegistro, "
				+ "concat(profesor.nombre,' ',profesor.apellido_paterno,"
				+ "' ',profesor.apellido_materno) AS nombreEmpleado, "
				+ "registro_entrada_salida.tipo_registro AS tipoRegistro, "
				+ "registro_entrada_salida.hora_registro AS fechaRegistro "
				+ "FROM "
				+ "registro_entrada_salida "
				+ "INNER JOIN "
				+ "profesor ON profesor.codigo = registro_entrada_salida.codigo_persona_registrada "
				+ "WHERE registro_entrada_salida.codigo_persona_registrada = ? "
				+ "AND DATE(hora_registro) = curdate() AND tipo_registro = 'entrada'";
		try {
			comando = conexion.prepareStatement(query);
			comando.setString(1, codigo);
			resultadoConsulta = comando.executeQuery();
			while(resultadoConsulta.next()){
				registro = new RegistroEntradaSalida(resultadoConsulta.getString("nombreEmpleado"), 
						resultadoConsulta.getString("tipoRegistro"), 
						resultadoConsulta.getInt("idRegistro"), 
						resultadoConsulta.getTimestamp("fechaRegistro").toString());
			}
			return registro;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return registro;
	}
	
	public static RegistroEntradaSalida existeRegistroSalida(String codigo){
		PreparedStatement comando;
		ResultSet resultadoConsulta;
		RegistroEntradaSalida registro = null;
		String query = "SELECT "
				+ "registro_entrada_salida.id AS idRegistro, "
				+ "concat(profesor.nombre,' ',profesor.apellido_paterno,"
				+ "' ',profesor.apellido_materno) AS nombreEmpleado, "
				+ "registro_entrada_salida.tipo_registro AS tipoRegistro, "
				+ "registro_entrada_salida.hora_registro AS fechaRegistro "
				+ "FROM "
				+ "registro_entrada_salida "
				+ "INNER JOIN "
				+ "profesor ON profesor.codigo = registro_entrada_salida.codigo_persona_registrada "
				+ "WHERE registro_entrada_salida.codigo_persona_registrada = ? "
				+ "AND DATE(hora_registro) = curdate() AND tipo_registro = 'salida'";
		try {
			comando = conexion.prepareStatement(query);
			comando.setString(1, codigo);
			resultadoConsulta = comando.executeQuery();
			while(resultadoConsulta.next()){
				registro = new RegistroEntradaSalida(resultadoConsulta.getString("nombreEmpleado"), 
						resultadoConsulta.getString("tipoRegistro"), 
						resultadoConsulta.getInt("idRegistro"), 
						resultadoConsulta.getTimestamp("fechaRegistro").toString());
			}
			return registro;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return registro;
	}
	/**
	 * Inserta el nuevo registro en la base de datos.
	 * 
	 * @param codigoProfesor codigo del profesor que esta haciendo el registro
	 * @param tipoRegistro este puede ser "entrada" o "salida"
	 * @param horaRegistro hora en la que  se esta efectuando el registro
	 * @return verdadero si se puedo insertar. Falso si no lo hizo
	 */
	public static boolean agregarRegistroEntradaSalida(String codigoProfesor,String tipoRegistro,Timestamp horaRegistro){
		PreparedStatement comando;
		
		String query = "INSERT INTO registro_entrada_salida(codigo_persona_registrada,"
				+ "tipo_registro,hora_registro) values (?,?,?)";
		try {
			comando = conexion.prepareStatement(query);
			comando.setString(1,codigoProfesor);
			comando.setString(2,tipoRegistro);
			comando.setTimestamp(3, horaRegistro);
			System.out.println(comando.execute());
			return comando.getUpdateCount()>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/***
	 * Esta funcion regresa los los registros de tipo. Siempre y cuando 
	 * existan registros de este tipo en la tabla 'registro_entrada_salida'
	 * @return vector de registros que puede o no estar vacio de acuerdo a los datos en la base de datos
	 */
	public static Vector<RegistroEntradaSalida> dameRegistrosEntradaSalida(){
		Vector<RegistroEntradaSalida> vectorRegistrosEntradaSalida=new Vector<RegistroEntradaSalida>();
		PreparedStatement comando;
		ResultSet resultadoConsulta;
		String query = "SELECT "
						+"registro_entrada_salida.id AS idRegistro, " 
					    +"concat(profesor.nombre,' ',profesor.apellido_paterno,' ',profesor.apellido_materno) AS nombreProfesor, " 
					    +"registro_entrada_salida.tipo_registro AS tipoRegistro, " 
					    +"registro_entrada_salida.hora_registro AS horaRegistro "
					    +"FROM "
					    +"registro_entrada_salida "
						+" INNER JOIN profesor ON profesor.codigo = registro_entrada_salida.codigo_persona_registrada "
						+ "ORDER BY idRegistro";
		try {
			comando = conexion.prepareStatement(query);
			resultadoConsulta = comando.executeQuery();
			while(resultadoConsulta.next()){
				vectorRegistrosEntradaSalida.add(new RegistroEntradaSalida(resultadoConsulta.getString("nombreProfesor"), 
						resultadoConsulta.getString("tipoRegistro"), 
						resultadoConsulta.getInt("idRegistro"), 
						resultadoConsulta.getTimestamp("horaRegistro").toString()));
			}
			return vectorRegistrosEntradaSalida;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vectorRegistrosEntradaSalida;
	}
	/**
	 * Regresa el ultimo id registrado en la tabla registro_entrada_salida
	 * @return ultimo id registrado.
	 */
	public static int idUltimoRegistroEntradaSalida(){
		int idRegistro=-1;
		String sql = "SELECT MAX(id) as ultimoId FROM registro_entrada_salida";
		PreparedStatement comando;
		ResultSet resultadoConsulta;
		try {
			comando = conexion.prepareStatement(sql);
			resultadoConsulta = comando.executeQuery();
			while(resultadoConsulta.next()){
				idRegistro = resultadoConsulta.getInt("ultimoId");
			}
			return idRegistro;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idRegistro;
	}
	
	public static ObservableList<Materia> listaMateriasPorGrado(int grado){
		ObservableList<Materia> materias = FXCollections.observableArrayList();
		PreparedStatement comando;
		ResultSet resultadosConsulta;
		String query="SELECT id,nombre,grado FROM sistemaprefectura.materia WHERE grado = ?";
		try {
			comando = conexion.prepareStatement(query);
			comando.setInt(1, grado);
			resultadosConsulta=comando.executeQuery();
			while(resultadosConsulta.next()){
				materias.add(new Materia(resultadosConsulta.getString("nombre"),resultadosConsulta.getInt("id"),
						resultadosConsulta.getInt("grado")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return materias;
	}
	
	public static boolean  registraHorarioDeExamen(Time horaInicio,Time horaFin,Date fechaAplicacion,char grupo,
			int grado,String turno,int idMateria){
		String sql = "INSERT INTO horario_aplicacion_examen ("
				+ "hora_inicio, "
				+ "hora_fin,"
				+ "fecha_aplicacion,"
				+ "id_materia,"
				+ "grupo_a_aplicar,"
				+ "grado,"
				+ "turno) "
				+ "VALUES (?,?,?,?,?,?,?)";
		PreparedStatement comando;
		boolean comandoEjecutado=false;
		try {
			comando = conexion.prepareStatement(sql);
			comando.setTime(1, horaInicio);
			comando.setTime(2, horaFin);
			comando.setDate(3, fechaAplicacion);
			comando.setInt(4, idMateria);
			comando.setString(5, String.valueOf(grupo));
			comando.setInt(6, grado);
			comando.setString(7, turno);
			comando.execute();
			comandoEjecutado = comando.getUpdateCount()>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comandoEjecutado;
	}
	
	public static int ultimoRegistroHorarioExamenInsertado(){
		int idRegistro=-1;
		String sql = "SELECT MAX(id) as ultimoId FROM horario_aplicacion_examen";
		PreparedStatement comando;
		ResultSet resultadoConsulta;
		try {
			comando = conexion.prepareStatement(sql);
			resultadoConsulta = comando.executeQuery();
			while(resultadoConsulta.next()){
				idRegistro = resultadoConsulta.getInt("ultimoId");
			}
			return idRegistro;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idRegistro;
	}

	public static boolean existeExamenRegistradoEnFechaHoraYGrupo(Time hora,Date fecha,char grupo,int grado,String turno){
		boolean existe=false;
		String sql =  
				"SELECT "
				+ "* "
				+ "FROM "
				+ "horario_aplicacion_examen "
				+ "WHERE "
				+ "fecha_aplicacion = ? "
				+ "AND hora_inicio = ? "
				+ "AND grupo_a_aplicar = ? "
				+"AND grado = ? "
				+ "AND turno = ?";
		PreparedStatement comando;
		ResultSet resultadoConsulta;
		int cantidadRegistrosExistentes=0;
		try {
			comando = conexion.prepareStatement(sql);
			comando.setTime(2, hora);
			comando.setDate(1, fecha);
			comando.setString(3, String.valueOf(grupo));
			comando.setInt(4, grado);
			comando.setString(5, turno);
			
			resultadoConsulta = comando.executeQuery();
			while(resultadoConsulta.next()){
				cantidadRegistrosExistentes++;
			}
			existe = cantidadRegistrosExistentes>=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return existe;
	}
	
	public static Vector<RegistroHorarioExamen> horariosDeExamenes(){
		Vector<RegistroHorarioExamen> examenes = new Vector<RegistroHorarioExamen>();
		PreparedStatement comando;
		String sql = "SELECT "
					+"horario_aplicacion_examen.id AS idRegistro, "
					+"materia.nombre AS nombreMateria, "
					+"horario_aplicacion_examen.fecha_aplicacion AS fechaAplicacion, "
					+"horario_aplicacion_examen.hora_inicio AS horaInicio, "
					+"horario_aplicacion_examen.hora_fin AS horaFin, "
					+"horario_aplicacion_examen.grado AS grado, "
					+"horario_aplicacion_examen.grupo_a_aplicar AS grupo, "
					+"horario_aplicacion_examen.turno AS turno "
					+"FROM "
					+"horario_aplicacion_examen "
					+"INNER JOIN materia ON materia.id = horario_aplicacion_examen.id_materia";
		ResultSet resultadoConsulta;
		try {
			comando = conexion.prepareStatement(sql);
			resultadoConsulta = comando.executeQuery();
			while (resultadoConsulta.next()) {
				examenes.add(new RegistroHorarioExamen(resultadoConsulta.getString("nombreMateria"),
						resultadoConsulta.getInt("idRegistro"), 
						resultadoConsulta.getDate("fechaAplicacion").toString(), 
						resultadoConsulta.getTime("horaInicio").toString(),
						resultadoConsulta.getTime("horaFin").toString(), 
						resultadoConsulta.getString("grado"), 
						resultadoConsulta.getString("grupo"), 
						resultadoConsulta.getString("turno")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return examenes;
	}
}
