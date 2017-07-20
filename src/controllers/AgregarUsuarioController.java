package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import dao.DAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.Toast;

public class AgregarUsuarioController implements Initializable {
	@FXML private JFXTextField textNombre,textApellidoPaterno,textApellidoMaterno,textUsuario;
	@FXML private JFXPasswordField textPassword,textPassword2;
	@FXML private JFXComboBox<String> comboTipoUsuario;
	@FXML private JFXButton botonCancelar,botonAgregar;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		comboTipoUsuario.setItems(FXCollections.observableArrayList("Coordinador","prefecto"));
		botonCancelar.setOnAction(e->{
			Stage window = ((Stage)botonCancelar.getScene().getWindow());
			window.close();
		});
		
		botonAgregar.setOnAction(e->validaCampos());
	}
	
	
	private void validaCampos(){
		String nombre,apellidoPaterno,apellidoMaterno;
		String tipoUsuario,usuario;
		String mensaje="";
		nombre = textNombre.getText().replaceAll(" ", "");
		apellidoPaterno = textApellidoPaterno.getText().replaceAll(" ", "");
		apellidoMaterno = textApellidoMaterno.getText().replaceAll(" ", "");

		tipoUsuario = comboTipoUsuario.getValue();
		usuario = textUsuario.getText().replaceAll(" ", "");
		if(estaVacio(nombre)){
			mensaje+="Nombre.\n";
		}if(estaVacio(apellidoPaterno)){
			mensaje+="Apellido Paterno.\n";
		}if(estaVacio(apellidoMaterno)){
			mensaje+="Apellido Materno.\n";
		}if(estaVacio(usuario)){
			mensaje+="Usuario.\n";
		}if(estaVacio(tipoUsuario)){
			mensaje+="Tipo de usuario";
		}
	
		if(!(mensaje.length()>0)){
			verficaPassword(nombre,apellidoPaterno,apellidoMaterno,usuario,tipoUsuario);
		}else{
			Toast.makeText(((Stage)botonCancelar.getScene().getWindow()), 
					"Falta llenar:\n"+mensaje, 3500,500,500,Color.RED);
		}
	}
	
	private void verficaPassword(String nombre, String apellidoPaterno, String apellidoMaterno, 
			String usuario, String tipoUsuario){
		String password = textPassword.getText().replaceAll(" ", "");
		String password2 = textPassword2.getText().replaceAll(" ", "");
		boolean passwordVacio = estaVacio(password),password2Vacio = estaVacio(password2);
		if(passwordVacio){
			Toast.makeText(((Stage)botonCancelar.getScene().getWindow()), 
					"Falta ingresar un password", 3500,500,500,Color.RED);
		}if(password2Vacio && !passwordVacio){
			Toast.makeText(((Stage)botonCancelar.getScene().getWindow()), 
					"Falta verificar el password", 3500,500,500,Color.RED);
		}
		if(!password.equals(password2) && (!password2Vacio && !passwordVacio)){
			Toast.makeText(((Stage)botonCancelar.getScene().getWindow()), 
					"No coinciden los passwordss", 3500,500,500,Color.RED);
		}
		if(password.equals(password2) && (!password2Vacio && !passwordVacio)){
			agegarUsuario(nombre,apellidoPaterno,apellidoMaterno,usuario,password,tipoUsuario);
		}
	}
	
	private void agegarUsuario(String nombre, String apellidoPaterno, String apellidoMaterno, String usuario,
			String password, String tipoUsuario) {
		// TODO Auto-generated method stub
		if(DAO.agregarUsuario(nombre, apellidoPaterno, apellidoMaterno, usuario, password, tipoUsuario)){
			Toast.makeText(((Stage)botonCancelar.getScene().getWindow()), 
					"Agregado correctamente", 3500,500,500,Color.GREEN);
		}else{
			Toast.makeText(((Stage)botonCancelar.getScene().getWindow()), 
					"No se pudo agregar el usuario", 3500,500,500,Color.RED);
		}
		
	}


	private boolean estaVacio(String texto){
		return texto == null | texto.isEmpty();
	}
}
