package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import dao.DAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pojos.Articulo;

public class MainFrameController implements Initializable{

	@FXML private JFXDrawer sideBar;
	@FXML private Pane seccionCreacionDeExamenes;
	@FXML private SplitPane seccionCreacionDePrestamos;
	@FXML private JFXHamburger menuAmburguesa;
	@FXML private TableView<String> tablaPrestamos;
	@FXML private JFXListView<Articulo> listaArticulosPrestados;
	@FXML private JFXTextArea areaDescripcionArticulo;
	@FXML private JFXTextField textNombreArticulo,textCodigoArticulo;
	@FXML private JFXButton botonBuscarArticulo;
	@FXML private ImageView imagenArticulo;
	@FXML private Label etiquetaBorrarTodo;
	
	public static JFXDrawer sideBarVista;
	public static Pane seccionExamenes;
	public static SplitPane seccionPrestamosVista;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
		etiquetaBorrarTodo.setOnMouseClicked(e->borrarCampos());
	}
	
	
	private void borrarCampos(){
		textNombreArticulo.setText("");
		areaDescripcionArticulo.setText("");
		textCodigoArticulo.setText("");
	}
	
	private void buscarArticulo(){
		String codigoArticulo = textCodigoArticulo.getText();
		Articulo articulo=DAO.consultarArticulo(codigoArticulo);
		if(!articulo.equals(null)){
			textNombreArticulo.setText(articulo.getCategoria());
			areaDescripcionArticulo.setText(articulo.getDescripcion());
			imagenArticulo.setImage(new Image( "file:/"+articulo.getUrlImagen() ));
			System.out.println(imagenArticulo.getImage());
		}else{
			
		}
	}
	
	private void configurarColumnasTabla(){
		tablaPrestamos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		ObservableList<TableColumn<String , ?>> columnas = tablaPrestamos.getColumns();
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
			
				sideBar.close();
			}else{
				
				sideBar.open();

			}
		});
	}

	
}
