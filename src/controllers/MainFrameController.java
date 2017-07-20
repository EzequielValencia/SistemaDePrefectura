package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.Vector;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import dao.DAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pojos.Alumno;
import pojos.Articulo;
import pojos.Materia;
import pojos.Persona;
import pojos.Prestamo;
import pojos.Profesor;
import pojos.RegistroHorarioExamen;
import pojos.Usuario;
import util.Toast;
/**
 * 
 * MainFrameController.
 * 
 * Controla la ventana principal del proyecto
 */
public class MainFrameController implements Initializable{

	@FXML private JFXDrawer sideBar;
	@FXML private GridPane seccionCreacionDeExamenes;
	@FXML private SplitPane seccionCreacionDePrestamos;
	@FXML private JFXHamburger menuAmburguesa;
	@FXML private TableView<Prestamo> tablaPrestamos;
	@FXML private JFXTextArea areaDescripcionArticulo;
	@FXML private JFXTextField textNombreArticulo,textCodigoArticulo,textCodigoSolicitante,textGrado,
	textGrupo,textTurno,textNombreSolicitante;
	@FXML private JFXButton botonBuscarArticulo,botonAgregarALista,botonEliminarDeLista,botonBuscarAlumno;
	@FXML private ImageView imagenArticulo;
	@FXML private Label etiquetaBorrarTodo,etiquetaBorrarSolicitante;
	
	@FXML private JFXComboBox<Integer> comboGrado;
	@FXML private JFXComboBox<String> comboTurno;
	@FXML private JFXComboBox<Character> comboGrupo;
	@FXML private JFXComboBox<String> comboMateria;
	@FXML private TableView<RegistroHorarioExamen> tablaRegistroExamenes;
	@FXML private JFXButton botonBorrarCampos,botonRegistrar;
	@FXML private JFXDatePicker selectorFechaAplicacion;
	@FXML private JFXTimePicker selectorHoraInicio,selectorHoraFin;	
	
	public static JFXDrawer sideBarVista;
	public static GridPane seccionExamenes;
	public static SplitPane seccionPrestamosVista;
	private Articulo articuloEncontrado;
	private Persona solicitante;
	private Prestamo prestamoGenerado;
	private ObservableList<Prestamo> listaArticulos;
	private ObservableList<Materia> listaMaterias;
	private ObservableList<RegistroHorarioExamen> listaDeExamenes;
	@Override
	/**
	 * Metodo que se utiliza una vez se carga la ventana para inicializar componentes y 
	 * acctiones a los botones
	 */
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(Usuario.getNombre());
		listaArticulos = FXCollections.observableArrayList();
		listaDeExamenes = FXCollections.observableArrayList();
		Vector<RegistroHorarioExamen> examenes = DAO.horariosDeExamenes();
		solicitante = null;
		articuloEncontrado = null;
		tablaPrestamos.setItems(listaArticulos);
		
		tablaRegistroExamenes.setItems(listaDeExamenes);
		for(int i=0;i<examenes.size();i++){
			agregarExamenATabla(examenes.get(i));
		}
		
		seccionExamenes = seccionCreacionDeExamenes;
		seccionPrestamosVista = seccionCreacionDePrestamos;
		sideBarVista = sideBar;
		try {
			
			VBox sideBar = FXMLLoader.load(getClass().getResource("/view/sideBar.fxml"));
			
			this.sideBar.setSidePane(sideBar);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		transformacionAFlecha();
		configurarColumnasTablas();
		agregarAccionesABotonesPrestamos();
		inicializaComboBoxs();
	}
	
	private void inicializaComboBoxs(){
		comboGrado.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8));
		comboGrado.valueProperty().addListener(new ChangeListener<Integer>(){

			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
				// TODO Auto-generated method stub
				listaMaterias = DAO.listaMateriasPorGrado(newValue);
				comboMateria.getItems().clear();
				for(int i=0;i<listaMaterias.size();i++){
					comboMateria.getItems().add(listaMaterias.get(i).getNombre());
				}
			}
			
		});
		comboTurno.setItems(FXCollections.observableArrayList("Matutino","Vespertino","Mixto","Nocturno"));
		comboGrupo.setItems(FXCollections.observableArrayList('A','B','C','D','E','F','G','H'));
		
	}
	
	/**
	 * Metodo para agregar las acciones a los botones
	 */
	private void agregarAccionesABotonesPrestamos(){
		botonBuscarArticulo.setOnAction(e->buscarArticulo());
		etiquetaBorrarSolicitante.setOnMouseClicked(e->borrarCamposSolicitante());
		etiquetaBorrarTodo.setOnMouseClicked(e->borrarCamposArticulo());
		botonAgregarALista.setOnAction(e->agregarATablaArticulo());
		botonBuscarAlumno.setOnAction(e->buscarSolicitante());
		botonRegistrar.setOnAction(e->{
			if(camposCompletosParaRegistrarExamen()){
			crearRegistroHorarioExamen();
			}
		});
	}
	private boolean camposCompletosParaRegistrarExamen(){
		boolean camposLlenos = false;
		String mensaje="";

		if(comboMateria.getValue()==null){
			mensaje+="Materia\n";
		}if(comboGrado.getValue()==null){
			mensaje+="Grado\n";
		}if(comboGrupo.getValue()==null){
			mensaje+="Grupo\n";
		}if(comboTurno.getValue()==null){
			mensaje+="Turno\n";
		}if(selectorFechaAplicacion.getValue()==null){
			mensaje+="Fecha aplicacion\n";
		}if(selectorHoraInicio.getValue()==null){
			mensaje+="Hora de inicio\n";
		}if(selectorHoraFin.getValue()==null){
			mensaje+="Hora de fin\n";
		}
		if(mensaje.length()>0){
			Toast.makeText((Stage)botonRegistrar.getScene().getWindow(),"Falta seleccionar \n"+mensaje, 3500,
					500,500,Color.RED);
		}else{
			camposLlenos = true;
		}
		return camposLlenos;
	}
	/**
	 * 
	 */
	@SuppressWarnings("deprecation")
	private void crearRegistroHorarioExamen(){
		Date fechaAplicacion = new Date(selectorFechaAplicacion.getValue().getYear()-1901,
				selectorFechaAplicacion.getValue().getDayOfMonth(),
				selectorFechaAplicacion.getValue().getMonthValue());
		Time horaInicio = new Time(selectorHoraInicio.getValue().getHour(),
				selectorHoraInicio.getValue().getMinute(), selectorHoraInicio.getValue().getSecond());
		Time horaFin = new Time(selectorHoraFin.getValue().getHour(),
				selectorHoraFin.getValue().getMinute(), selectorHoraFin.getValue().getSecond());
		char grupo = comboGrupo.getValue();
		int grado = comboGrado.getValue();
		String turno = comboTurno.getValue();
		String nombreMateria = comboMateria.getValue();
		int idMateria = codigoMateriaSeleccionada(nombreMateria);
		int idRegistro;
		RegistroHorarioExamen registro;
		if(DAO.existeExamenRegistradoEnFechaHoraYGrupo(horaInicio, fechaAplicacion, grupo,grado, turno)){
			Toast.makeText(((Stage)botonRegistrar.getScene().getWindow()), ("El grupo "+grado+".-"+grupo+" del turno "+turno+".\n"+
					"Ya tiene un examen a esa hora")
					, 3500, 500, 500, Color.RED);
		}else{
			if(DAO.registraHorarioDeExamen(horaInicio, horaFin, fechaAplicacion, grupo, grado, turno, idMateria)){
				
				idRegistro = DAO.ultimoRegistroHorarioExamenInsertado();
				
				registro = new RegistroHorarioExamen(nombreMateria,idRegistro,fechaAplicacion.toString(),
						horaInicio.toString(),horaFin.toString(),String.valueOf(grado),String.valueOf(grupo),turno);
				;
				agregarExamenATabla(registro);
			}
		}
	}
	/**
	 * 
	 * @param nombreMateria
	 * @return
	 */
	private int codigoMateriaSeleccionada(String nombreMateria){
		Materia materiaSeleccionada;
		int idMateria=0;
		for(int i=0;i<listaMaterias.size();i++){
			materiaSeleccionada = listaMaterias.get(i);
			
			if(materiaSeleccionada.getNombre().equals(nombreMateria)){

				idMateria = materiaSeleccionada.getId();
			}
		}
		return idMateria;
	}
	/**
	 * Borra los cambpos del articulo poniendoles 
	 * caracter nulo como texto
	 */
	private void borrarCamposArticulo(){
		textNombreArticulo.setText("");
		areaDescripcionArticulo.setText("");
		textCodigoArticulo.setText("");
	}
	/**
	 * Borra los campos de el solicitante poniendoles
	 * caracter nulo como texto
	 */
	private void borrarCamposSolicitante(){
		textNombreSolicitante.setText("");
		textGrado.setText("");
		textGrupo.setText("");
		textTurno.setText("");
		textCodigoSolicitante.setText("");
	}
	/**
	 * Genera el prestamos para poder ser añadido a la base de datos y a la tabla 
	 * de la vista
	 */
	private void generarPrestamo(){
		Timestamp fechaPrestamo = new Timestamp(new java.util.Date().getTime());
		String grupo="";
		
		if(solicitante instanceof Alumno){
			grupo = ((Alumno)solicitante).getGrado()+' '+((Alumno)solicitante).getGrupo();
		}
		System.out.println(articuloEncontrado.getCategoria());
		prestamoGenerado = new Prestamo(articuloEncontrado.getNombre(), 
				articuloEncontrado.getCategoria(),
				fechaPrestamo.toString(), ""
				,grupo,
				solicitante.getNombreCompleto());
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Agrega el prestamo a la tabla de prestamos
	 */
	private void agregarATablaArticulo(){
		TableColumn<Prestamo, String> columnaNombreSolicitante = ((TableColumn<Prestamo, String>) tablaPrestamos.getColumns().get(0));
		TableColumn<Prestamo, String> columnaNombreArticulo = ((TableColumn<Prestamo, String>) tablaPrestamos.getColumns().get(1)); 
		TableColumn<Prestamo,String > columnaCategoria = ((TableColumn<Prestamo,String>) tablaPrestamos.getColumns().get(2));
		TableColumn<Prestamo,String > columnaFechaPrestamo = ((TableColumn<Prestamo,String>) tablaPrestamos.getColumns().get(3));
		TableColumn<Prestamo, String> columnaGrupo = ((TableColumn<Prestamo, String>) tablaPrestamos.getColumns().get(4));
		TableColumn<Prestamo, String> columnaNombrePrefecto = ((TableColumn<Prestamo, String>) tablaPrestamos.getColumns().get(6));
		if(!articuloEncontrado.equals(null) && !solicitante.equals(null)){
			generarPrestamo();
			listaArticulos.add(prestamoGenerado);
			columnaNombreSolicitante.setCellValueFactory(new PropertyValueFactory<Prestamo,String>("nombreSolicitante"));
			columnaNombreArticulo.setCellValueFactory(new PropertyValueFactory<Prestamo,String>("nombreArticulo"));
			columnaCategoria.setCellValueFactory(new PropertyValueFactory<Prestamo,String>("categoriaArticulo"));
			columnaFechaPrestamo.setCellValueFactory(new PropertyValueFactory<Prestamo,String>("fechaPrestamo"));
			columnaGrupo.setCellValueFactory(new PropertyValueFactory<Prestamo,String>("grupoRegistrado"));
			columnaNombrePrefecto.setCellValueFactory(new PropertyValueFactory<Prestamo,String>("nombrePrefecto"));
		}

	}
	@SuppressWarnings("unchecked")
	private void agregarExamenATabla(RegistroHorarioExamen examen){
		TableColumn<RegistroHorarioExamen, Integer> columnaIdExamen = ((TableColumn<RegistroHorarioExamen, Integer>) 
																	tablaRegistroExamenes.getColumns().get(0));
		TableColumn<RegistroHorarioExamen, String> columnaNombreMateria = ((TableColumn<RegistroHorarioExamen, String>)
																			tablaRegistroExamenes.getColumns().get(1));
		TableColumn<RegistroHorarioExamen, String> columnaGrado = ((TableColumn<RegistroHorarioExamen, String>)
				tablaRegistroExamenes.getColumns().get(2));
		TableColumn<RegistroHorarioExamen, String> columnaGrupo = ((TableColumn<RegistroHorarioExamen, String>)
				tablaRegistroExamenes.getColumns().get(3));
		TableColumn<RegistroHorarioExamen, String> columnaTurno = ((TableColumn<RegistroHorarioExamen, String>)
				tablaRegistroExamenes.getColumns().get(4));
		TableColumn<RegistroHorarioExamen, String> columnaFechaAplicacion = ((TableColumn<RegistroHorarioExamen, String>)
																	tablaRegistroExamenes.getColumns().get(5));
		TableColumn<RegistroHorarioExamen, String> columnahHoraInicio = ((TableColumn<RegistroHorarioExamen, String>)
				tablaRegistroExamenes.getColumns().get(6));
		TableColumn<RegistroHorarioExamen, String> columnaHoraFin = ((TableColumn<RegistroHorarioExamen, String>)
				tablaRegistroExamenes.getColumns().get(7));
		
		listaDeExamenes.add(0,examen);
		columnaIdExamen.setCellValueFactory(new PropertyValueFactory<RegistroHorarioExamen,Integer>("idRegistro"));
		columnaNombreMateria.setCellValueFactory(new PropertyValueFactory<RegistroHorarioExamen,String>("nombreMateria"));
		columnaGrado.setCellValueFactory(new PropertyValueFactory<RegistroHorarioExamen,String>("grado"));
		columnaGrupo.setCellValueFactory(new PropertyValueFactory<RegistroHorarioExamen,String>("grupo"));
		columnaTurno.setCellValueFactory(new PropertyValueFactory<RegistroHorarioExamen,String>("turno"));
		columnaFechaAplicacion.setCellValueFactory(new PropertyValueFactory<RegistroHorarioExamen,String>("fechaAplicacion"));
		columnahHoraInicio.setCellValueFactory(new PropertyValueFactory<RegistroHorarioExamen,String>("horaInicio"));
		columnaHoraFin.setCellValueFactory(new PropertyValueFactory<RegistroHorarioExamen,String>("horaFin"));
		
	}
	
	/**
	 * Metodo para buscar el articulo.
	 */
	private void buscarArticulo(){
		String codigoArticulo = textCodigoArticulo.getText();
		articuloEncontrado =DAO.consultarArticulo(codigoArticulo);
		if(!articuloEncontrado.equals(null)){
			textNombreArticulo.setText(articuloEncontrado.getNombre());
			areaDescripcionArticulo.setText(articuloEncontrado.getDescripcion());
			imagenArticulo.setImage(new Image( "file:/"+articuloEncontrado.getUrlImagen() ));
			System.out.println(imagenArticulo.getImage());
		}else{
			
		}
	}
	
	/**
	 * Metodo que manda a buscar el solicitante mediante el codigo ingresado.
	 */
	private void buscarSolicitante(){
		Alumno alumnoSolicitante = null;
		Profesor profesorSoliciante = null;
		solicitante = DAO.buscaSolicitante(textCodigoSolicitante.getText());
		if(!solicitante.equals(null)){
			textNombreSolicitante.setText(solicitante.getNombreCompleto());
			if(solicitante instanceof Alumno){
				alumnoSolicitante = (Alumno)solicitante;
				textGrado.setText(alumnoSolicitante.getGrado());
				textGrupo.setText(alumnoSolicitante.getGrupo());
				textTurno.setText(alumnoSolicitante.getTurno());
			}else{
				profesorSoliciante = (Profesor)solicitante;
				textGrado.setText("");
				textGrupo.setText("");
				textTurno.setText("");
			}
		}
	}
	/**
	 * Configura el tamaño de todas las columnas que tiene la tabla
	 * para que puedan ser redimencionadas y ocupen el mismo porcentaje
	 * de ancho al momento de que la ventana se redimenciona.
	 */
	private void configurarColumnasTablas(){
		tablaPrestamos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tablaRegistroExamenes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		ObservableList<TableColumn<Prestamo, ?>> columnas = tablaPrestamos.getColumns();
		ObservableList<TableColumn<RegistroHorarioExamen, ?>> columnasRegistroExamen = tablaRegistroExamenes.getColumns();
		int cantidadColumnas = columnas.size();
		int cantidadColumnasTablaRegistroExamenes = columnasRegistroExamen.size();
		
		for(int i=0;i<cantidadColumnas;i++){
			columnas.get(i).setMaxWidth(1f * Integer.MAX_VALUE*(100/cantidadColumnas));
		}
		
		for(int i=0;i<cantidadColumnasTablaRegistroExamenes;i++){
			columnasRegistroExamen.get(i).setMaxWidth(1f * Integer.MAX_VALUE*(100/cantidadColumnas));
		}
	}
	/**
	 * Transforma el boton de "hamburguesa" en una flecha al ser presionado.
	 */
	private void transformacionAFlecha() {
		// TODO Auto-generated method stub
		
		HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(menuAmburguesa);
		
		transition.setRate(-1);

		menuAmburguesa.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
			transition.setRate(transition.getRate()*-1);
			transition.play();
			
			if(sideBar.isShown()){
				sideBar.setScaleZ(0);
				sideBar.setTranslateZ(-1);
				sideBar.close();
			}else{
				sideBar.setScaleZ(1);
				sideBar.setTranslateZ(1);
				sideBar.open();

			}
		});
	}

	
}
