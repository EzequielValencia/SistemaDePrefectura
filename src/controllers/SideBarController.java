package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
	private JFXButton botonSalir;
	


	//private GridPane seccionPrestamos;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Image image = new Image("/images/avatar.png");
		imagenUsuario.setImage(image);
		agregarAcionesABotones();
		crearSeccionPrestamos();
	}

	private void crearSeccionPrestamos(){
		
	}

	private void agregarAcionesABotones(){
		botonSalir.setOnAction(e->System.exit(0));
		botonRegistroPrestamos.setOnAction(e->abrirVistaPrestamos());
		botonCreacionDeExamenes.setOnAction(e->abrirVistaExamenes());
		botonChat.setOnAction(e->{});
	}
	
	private void abrirVistaExamenes(){
		
		MainFrameController.seccionExamenes.setVisible(true);
		MainFrameController.seccionPrestamosVista.setVisible(false);
		MainFrameController.sideBarVista.close();
	}
	
	private void abrirVistaPrestamos(){
		
		MainFrameController.seccionExamenes.setVisible(false);
		MainFrameController.seccionPrestamosVista.setVisible(true);
		MainFrameController.sideBarVista.close();
	}
}
