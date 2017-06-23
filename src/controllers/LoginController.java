package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@FXML private JFXTextField textUsuario;
	@FXML private JFXPasswordField textPassword;
	@FXML private JFXButton botonIngresar;
	@FXML private JFXButton botonCerrar;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		botonCerrar.setOnAction(e->{
			cerrarLogin();
		});
	}
	
	public void cerrarLogin(){
		Stage escenarioActual =(Stage) botonCerrar.getScene().getWindow();
		escenarioActual.close();
	}
}
