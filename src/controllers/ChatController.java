package controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import paqueteDatos.DatosEntradaSalida;

public class ChatController implements Initializable,Runnable {
	@FXML
	private Button botonEnviar,botonCerrar;
	@FXML
	private ListView<String> listMensajesEnviados;
	@FXML
	private TextArea textMensajeAEnviar;
	@FXML
	private TextField textUsuario;
	private ObservableList<String> listaMensajes = FXCollections.observableArrayList();
	private boolean ventanaAbierta;
	private void agregaMensajeAListaMensajes(){
		String mensaje = textMensajeAEnviar.getText();
		if(!mensaje.isEmpty()){
			listaMensajes.add(mensaje);
			listMensajesEnviados.setItems(listaMensajes);
			textMensajeAEnviar.setText("");
			enviaMensaje(mensaje);
		}
	}
	
	private void enviaMensaje(String mensaje){
		try {
			System.out.println("Enviando mensaje");
			Socket socket = new Socket("192.168.1.72",5000);			
			DatosEntradaSalida datos = new DatosEntradaSalida(textUsuario.getText(),mensaje,"Yo mero");
			ObjectOutputStream datosDeSalida= new ObjectOutputStream(socket.getOutputStream());
			datosDeSalida.writeObject(datos);
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(5000);
			while(ventanaAbierta){
				Socket socketEntrada = serverSocket.accept();
				ObjectInputStream flujoEntrada = new ObjectInputStream(socketEntrada.getInputStream());
				DatosEntradaSalida datosEntrada = (DatosEntradaSalida) flujoEntrada.readObject();
				listaMensajes.add(datosEntrada.getRemitente()+": "+datosEntrada.getMensaje());
				System.out.println(datosEntrada.getMensaje());
				listMensajesEnviados.setItems(listaMensajes);
				socketEntrada.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		ventanaAbierta = true;
		botonEnviar.setOnAction(e->{
			agregaMensajeAListaMensajes();
			
		});
		
		textMensajeAEnviar.setOnKeyPressed(e->{
			int tamanioTexto = textMensajeAEnviar.getText().length();
			if(tamanioTexto%60==0 && tamanioTexto!=0){
				textMensajeAEnviar.appendText("\n");
			}
		});
		
		botonCerrar.setOnAction(e->{
			Stage escenarioActual = (Stage)botonCerrar.getScene().getWindow();
			ventanaAbierta = false;
			
			escenarioActual.hide();
		});
		
		
	}

	

}
