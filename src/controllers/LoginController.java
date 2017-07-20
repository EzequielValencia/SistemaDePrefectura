package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import dao.DAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@FXML private JFXTextField textUsuario;
	@FXML private JFXPasswordField textPassword;
	@FXML private JFXButton botonIngresar;
	@FXML private JFXButton botonCerrar;
	@FXML private Label labelMensaje;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		System.out.println(textUsuario.getStyle());
		botonCerrar.setOnAction(e->{
			cerrarLogin();
		});
		
		botonIngresar.setOnAction(e->{
				verificaUsuario();
		});
	}
	/**
	 * Cierra la ventana y termina el programa
	 */
	private void cerrarLogin(){
		Stage escenarioActual =(Stage) botonCerrar.getScene().getWindow();
		escenarioActual.close();
	}
	/**
	 * Verifica la existencia del usuario y la contrase~a del usuario
	 */
	private void verificaUsuario(){
		boolean existe = DAO.verificaUsuario(textUsuario.getText(), textPassword.getText());
		if(existe){
			abreVentanaPricipal();
		}else{
			labelMensaje.setText("Verifique su usuario y contrase~a");
			labelMensaje.setStyle("-fx-text-fill:#c21818;");
		}
	}
	/**
	 * Abre la ventana principal del sistema
	 */
	private void abreVentanaPricipal(){
		Stage esenarioActual;
		Parent root;
		Scene escena;
		Rectangle2D tamanioPantalla = Screen.getPrimary().getBounds();
		
		esenarioActual = (Stage) botonIngresar.getScene().getWindow();
		try {
			root = FXMLLoader.load(getClass().getResource("/view/mainFrame.fxml"));
			escena = new Scene(root,tamanioPantalla.getWidth(),tamanioPantalla.getHeight()-80);
			
			esenarioActual.setScene(escena);
			esenarioActual.show();
			esenarioActual.setX(0);
			esenarioActual.setY(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
