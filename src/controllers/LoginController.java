package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import dao.DAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import pojos.Usuario;

public class LoginController implements Initializable {

	@FXML private JFXTextField textUsuario;
	@FXML private JFXPasswordField textPassword;
	@FXML private JFXButton botonIngresar;
	@FXML private JFXButton botonCerrar;
	@FXML private Label labelMensaje;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		botonCerrar.setOnAction(e->{
			cerrarLogin();
		});
		
		botonIngresar.setOnAction(e->{
			if(camposLlenos()){
				verificaUsuario();
			}else{
				
			}
		});
	}
	
	private void cerrarLogin(){
		Stage escenarioActual =(Stage) botonCerrar.getScene().getWindow();
		escenarioActual.close();
	}
	
	private boolean camposLlenos(){
		boolean camposLlenos=true;
		if(textUsuario.getText().length()<=0){
			textUsuario.setUnFocusColor(Paint.valueOf("#c61a1a"));
			textUsuario.setFocusColor(Paint.valueOf("#820f0f"));
			labelMensaje.setText("Ingrese su nombre de usuario");
			labelMensaje.setStyle("-fx-text-fill:#c21818;");
			camposLlenos=false;
		}
		if(textPassword.getText().length()<=0){
			textPassword.setUnFocusColor(Paint.valueOf("#c61a1a"));
			textPassword.setFocusColor(Paint.valueOf("#820f0f"));
			labelMensaje.setText("Ingrese una contraseña");
			labelMensaje.setStyle("-fx-text-fill:#c21818;");
			camposLlenos=false;
		}
		if(textUsuario.getText().length()<=0 && textPassword.getText().length()<=0){
			textUsuario.setUnFocusColor(Paint.valueOf("#c61a1a"));
			textUsuario.setFocusColor(Paint.valueOf("#820f0f"));
			
			textPassword.setUnFocusColor(Paint.valueOf("#c61a1a"));
			textPassword.setFocusColor(Paint.valueOf("#820f0f"));
			labelMensaje.setText("Ingrese usuario y contraseña");
			labelMensaje.setStyle("-fx-text-fill:#c21818;");
			camposLlenos=false;
		}
		return camposLlenos;
	}
	
	private void verificaUsuario(){
		Vector<Usuario> usuarios = DAO.verificaUsuario(textUsuario.getText(), textPassword.getText());
		if(usuarios.size()==1){
			labelMensaje.setText("Acceso correcto");
			labelMensaje.setStyle("-fx-text-fill:#23bb58;");
		}else{
			labelMensaje.setText("Verifique su usuario y contreseña");
			labelMensaje.setStyle("-fx-text-fill:#c21818;");
		}
	}
}
