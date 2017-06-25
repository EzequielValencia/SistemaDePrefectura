package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

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
		botonAsistenciaProfesores.setOnAction(e->abrirVistaAsistenciaProfesores());
	}

	private void cambiaMenuHamburguesa(){
		System.out.println("Cambiando menu");
		HamburgerBackArrowBasicTransition trancision = new HamburgerBackArrowBasicTransition(MainFrameController.menu);
		trancision.setRate(trancision.getRate()*-1);
		trancision.play();
		trancision.play();
	}
	private void abrirVistaAsistenciaProfesores(){
		cambiaMenuHamburguesa();
		MainFrameController.visorSecciones1.setVisible(false);
		MainFrameController.visorSecciones.setVisible(true);
	}
	
	private void abrirVistaPrestamos(){
		cambiaMenuHamburguesa();
		MainFrameController.visorSecciones.setVisible(false);
		MainFrameController.visorSecciones1.setVisible(true);
		
	}
}
