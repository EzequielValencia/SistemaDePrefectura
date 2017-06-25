package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

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
		System.out.println(textUsuario.getStyle());
		botonCerrar.setOnAction(e->{
			cerrarLogin();
		});
		
		botonIngresar.setOnAction(e->{
				verificaUsuario();
		});
	}
	
	private void cerrarLogin(){
		Stage escenarioActual =(Stage) botonCerrar.getScene().getWindow();
		escenarioActual.close();
	}
	
	private void verificaUsuario(){
		Vector<Usuario> usuarios = DAO.verificaUsuario(textUsuario.getText(), textPassword.getText());
		if(usuarios.size()==1){
			abreVentanaPricipal();
		}else{
			labelMensaje.setText("Verifique su usuario y contreseña");
			labelMensaje.setStyle("-fx-text-fill:#c21818;");
		}
	}
	
	private void abreVentanaPricipal(){
		Stage esenarioActual;
		Parent root;
		Scene escena;
		Rectangle2D tamanioPantalla = Screen.getPrimary().getBounds();
		
		esenarioActual = (Stage) botonIngresar.getScene().getWindow();
		try {
			root = FXMLLoader.load(getClass().getResource("/view/mainFrame.fxml"));
			escena = new Scene(root,tamanioPantalla.getWidth(),700);
			
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
