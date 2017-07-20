package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pojos.Usuario;
import util.Toast;
/**
 * 
 * SideBarController
 * Clase que controla la barra lateral del sistema
 */
public class SideBarController implements Initializable{
	@FXML
	private ImageView imagenUsuario;
	@FXML
	private JFXButton botonRegistroPrestamos;
	@FXML 
	private JFXButton botonAsistenciaProfesores;
	@FXML
	private JFXButton botonChat;
	@FXML
	private JFXButton botonCreacionDeExamenes;
	@FXML
	private JFXButton botonAgregarUsuario;
	@FXML
	private JFXButton botonSalir;
	@FXML 
	private Label etiquetaNombreDelUsuario;
	@Override
	/**
	 * initalize
	 * Metodo para inicializar el Controlador.
	 * 
	 */
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Image image = new Image("/images/avatar.png");
		imagenUsuario.setImage(image);
		agregarAcionesABotones();
		etiquetaNombreDelUsuario.setText(Usuario.getNombre());
	}

	/**
	 * Metodo para agregar la funcionalidad a los botones de la barra lateral
	 */
	private void agregarAcionesABotones(){
		botonSalir.setOnAction(e->System.exit(0));
		botonRegistroPrestamos.setOnAction(e->abrirVistaPrestamos());
		botonCreacionDeExamenes.setOnAction(e->abrirVistaExamenes());
		botonAsistenciaProfesores.setOnAction(e->abrirRelojChecador());
		botonChat.setOnAction(e->abrirChat());
		botonAgregarUsuario.setOnAction(e->abrirAgregarUsuario());
	}
	
	private void abrirAgregarUsuario(){
		Stage vistaAgregarUsuario = new Stage();
		Parent root;
		Scene escena;
		if(Usuario.getCategoria().equals("prefecto")){
			Toast.makeText(((Stage)botonAgregarUsuario.getScene().getWindow()),"No tiene permiso de usar esta opcion", 
					3500, 500, 500,Color.RED);
		}else{
			try {
				root = FXMLLoader.load(getClass().getResource("/view/vistaAgregarUsuario.fxml"));
				escena = new Scene(root, 800, 600);
				vistaAgregarUsuario.setScene(escena);
				vistaAgregarUsuario.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void abrirChat(){
		Stage vistaChat = new Stage();
		Parent root;
		Scene escena;
		
		try {
			root = FXMLLoader.load(getClass().getResource("/view/vistaChat.fxml"));
			escena = new Scene(root,600,400);
			vistaChat.setScene(escena);
			vistaChat.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Metodo que abre la ventana del reloj checador
	 */
	private void abrirRelojChecador(){
		Stage relojchecador = new Stage();
		Parent root;
		Scene scene;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/registroAsistenciaProfesores.fxml"));
			scene = new Scene(root,600,400);
			relojchecador.setScene(scene);
			relojchecador.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MainFrameController.sideBarVista.close();
	}
	/**
	 * Metodo que abre la vista de creacion de examenes
	 */
	private void abrirVistaExamenes(){
		if(Usuario.getCategoria().equals("coordinador")){
			MainFrameController.seccionExamenes.setVisible(true);
			MainFrameController.seccionPrestamosVista.setVisible(false);
			MainFrameController.sideBarVista.close();
		}else{
			Toast.makeText((Stage)botonCreacionDeExamenes.getScene().getWindow(), 
					"No tiene permiso de usar esta opcion", 3500, 500, 500,Color.RED);
		}
	}
	/**
	 * Metodo que abre la vista del sistema de prestamos
	 */
	private void abrirVistaPrestamos(){
		
		MainFrameController.seccionExamenes.setVisible(false);
		MainFrameController.seccionPrestamosVista.setVisible(true);
		MainFrameController.sideBarVista.close();
	}
}
