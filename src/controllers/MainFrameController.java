package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import dao.DAO;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pojos.Alumno;
import pojos.Articulo;
import pojos.Persona;
import pojos.Prestamo;
import pojos.Profesor;

public class MainFrameController implements Initializable{

	@FXML private JFXDrawer sideBar;
	@FXML private Pane seccionCreacionDeExamenes;
	@FXML private SplitPane seccionCreacionDePrestamos;
	@FXML private JFXHamburger menuAmburguesa;
	@FXML private TableView<Prestamo> tablaPrestamos;
	@FXML private JFXTextArea areaDescripcionArticulo;
	@FXML private JFXTextField textNombreArticulo,textCodigoArticulo,textCodigoSolicitante,textGrado,
	textGrupo,textTurno,textNombreSolicitante;
	@FXML private JFXButton botonBuscarArticulo,botonAgregarALista,botonEliminarDeLista,botonBuscarAlumno;
	@FXML private ImageView imagenArticulo;
	@FXML private Label etiquetaBorrarTodo,etiquetaBorrarSolicitante;
	
	public static JFXDrawer sideBarVista;
	public static Pane seccionExamenes;
	public static SplitPane seccionPrestamosVista;
	private Articulo articuloEncontrado;
	private Persona solicitante;
	private Prestamo prestamoGenerado;
	private ObservableList<Prestamo> listaArticulos;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listaArticulos = FXCollections.observableArrayList();
		solicitante = null;
		articuloEncontrado = null;
		tablaPrestamos.setItems(listaArticulos);
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
		configurarColumnasTabla();
		agregarAccionesABotonesPrestamos();
	}

	private void agregarAccionesABotonesPrestamos(){
		botonBuscarArticulo.setOnAction(e->buscarArticulo());
		etiquetaBorrarSolicitante.setOnMouseClicked(e->borrarCamposSolicitante());
		etiquetaBorrarTodo.setOnMouseClicked(e->borrarCamposArticulo());
		botonAgregarALista.setOnAction(e->agregarATablaArticulo());
		botonBuscarAlumno.setOnAction(e->buscarSolicitante());
	}
	
	
	private void borrarCamposArticulo(){
		textNombreArticulo.setText("");
		areaDescripcionArticulo.setText("");
		textCodigoArticulo.setText("");
	}
	
	private void borrarCamposSolicitante(){
		textNombreSolicitante.setText("");
		textGrado.setText("");
		textGrupo.setText("");
		textTurno.setText("");
		textCodigoSolicitante.setText("");
	}
	
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
	
	private void configurarColumnasTabla(){
		tablaPrestamos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		ObservableList<TableColumn<Prestamo, ?>> columnas = tablaPrestamos.getColumns();
		int cantidadColumnas = columnas.size();
		
		for(int i=0;i<cantidadColumnas;i++){
			columnas.get(i).setMaxWidth(1f * Integer.MAX_VALUE*(100/cantidadColumnas));
		}
	}
	
	private void transformacionAFlecha() {
		// TODO Auto-generated method stub
		
		HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(menuAmburguesa);
		
		transition.setRate(-1);

		menuAmburguesa.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
			transition.setRate(transition.getRate()*-1);
			transition.play();
			
			if(sideBar.isShown()){
				sideBar.setScaleZ(0);
				sideBar.close();
			}else{
				sideBar.setScaleZ(1);
				sideBar.open();

			}
		});
	}

	
}
