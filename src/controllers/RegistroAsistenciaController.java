package controllers;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.Vector;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import dao.DAO;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import pojos.Profesor;
import pojos.RegistroEntradaSalida;
import util.Toast;
/***
 * 
 * RegistroAsistenciaController.
 * Clase que controla los eventos de la ventana registroAsistencia
 */
public class RegistroAsistenciaController implements Initializable{
	private String horaEtiqueta,minutoEtiqueta,segundosEtiqueta;
	@FXML private Label etiquetaReloj,etiquetaNombreProfesor,etiquetaTipoTipoRegistro
						,etiquetaFechaRegistro;
	@FXML private JFXTextField textCodigoProfesor;
	@FXML private JFXButton botonBuscaRegistrar;
	@FXML private TableView<RegistroEntradaSalida> tablaRegistros;
	private ObservableList<RegistroEntradaSalida> listaRegistros;
	private Timeline digitalTime;
	
	
	public void initialize(URL location, ResourceBundle resources) {
		Vector<RegistroEntradaSalida> v = DAO.dameRegistrosEntradaSalida();
		botonBuscaRegistrar.setOnAction(e->buscaProfesor());
		configuracionTabla();
		listaRegistros = FXCollections.observableArrayList();
		tablaRegistros.setItems(listaRegistros);
		iniciarReloj();
		digitalTime.play();
		if(v.size()>0){
			for(int i=0;i<v.size();i++){
				agregarRegistroATabla(v.get(i));
			}
		}
	}
	
	
	/**
	 * Calcula la hora actual del sistema para ponerla en la ventana de registroAsistencia.
	 */
	public void iniciarReloj(){
		
		digitalTime = new Timeline(
			      new KeyFrame(Duration.seconds(0),
			        new EventHandler<ActionEvent>() {
			          @Override public void handle(ActionEvent actionEvent) {
			            Calendar calendar            = GregorianCalendar.getInstance();
			             horaEtiqueta   = pad(2, '0', calendar.get(Calendar.HOUR)  +"" );
			            minutoEtiqueta = pad(2, '0', calendar.get(Calendar.MINUTE) + "");
			            segundosEtiqueta = pad(2, '0', calendar.get(Calendar.SECOND) + "");
			            
			            etiquetaReloj.setText(horaEtiqueta + ":" + minutoEtiqueta + ":" + segundosEtiqueta);
			          }
			        }
			      ),
			      new KeyFrame(Duration.seconds(1))
			    );
		
	}
	
	private String pad(int fieldWidth, char padChar, String s) {
	    StringBuilder sb = new StringBuilder();
	    for (int i = s.length(); i < fieldWidth; i++) {
	      sb.append(padChar);
	    }
	    sb.append(s);

	    return sb.toString();
	}
	
	
	public void eventoDeTeclado(KeyEvent e){
		if(e.getText().toCharArray().length>0 && e.getText().toCharArray()[0] == 13){
			buscaProfesor();
		}
	}
	
	private void agregarRegistroABaseDeDAtos(Profesor p,Timestamp t){
		RegistroEntradaSalida registroEntrada,registroSalida;
		registroEntrada = DAO.existeRegistroEntrada(p.getCodigo());
		registroSalida = DAO.existeRegistroSalida(p.getCodigo());
		if(registroEntrada == null){
			DAO.agregarRegistroEntradaSalida(p.getCodigo(), "entrada" ,t);
			llenarEtiquetasRegistro(p, t,"entrada");
			agregarRegistroATabla( new RegistroEntradaSalida(p.getNombreCompleto(),"entrada",
					DAO.idUltimoRegistroEntradaSalida(),t.toString()) );
			Toast.makeText(((Stage)botonBuscaRegistrar.getScene().getWindow()), "Se registro la entrada a nombre de "+
					p.getNombreCompleto(),
					4000, 500, 500,Color.GREEN);
		}else if(registroSalida == null){
			
			DAO.agregarRegistroEntradaSalida(p.getCodigo(), "salida" ,t);
			llenarEtiquetasRegistro(p, t, "salida");
			agregarRegistroATabla( new RegistroEntradaSalida(p.getNombreCompleto(), "salida",
					DAO.idUltimoRegistroEntradaSalida(),t.toString()) );
			Toast.makeText(((Stage)botonBuscaRegistrar.getScene().getWindow()), "Se registro la salida a nombre de "+
					p.getNombreCompleto(),
					4000, 500, 500,Color.RED);
		}else{
			System.out.println("Ya tiene registro de entrada y salida");
		}
	}
	
	private void llenarEtiquetasRegistro(Profesor p,Timestamp t,String tipoRegistro){

		etiquetaNombreProfesor.setText(p.getNombre()+' '+p.getApellidoPaterno()+' '+p.getApellidoMaterno());
		etiquetaFechaRegistro.setText(t.toString());
		etiquetaTipoTipoRegistro.setText(tipoRegistro.toUpperCase());
		if(tipoRegistro.equals("salida")){
			etiquetaTipoTipoRegistro.setTextFill(Paint.valueOf("#a41313"));
		}else{
			etiquetaTipoTipoRegistro.setTextFill(Paint.valueOf("#188c4d"));
		}

	}
	
	private void buscaProfesor(){
		Profesor profesor;
		String codigo = textCodigoProfesor.getText();
		Timestamp horaRegistro;
		if(codigo.length()>0){
			textCodigoProfesor.setText("");
			profesor = DAO.buscaProfesor(codigo);
			if(profesor!=null){
				horaRegistro = new Timestamp(new Date().getTime());
				agregarRegistroABaseDeDAtos(profesor, horaRegistro);
				
			}else{
				System.out.println("no se encontro el profesor");
			}
		}
	}
	@SuppressWarnings("unchecked")
	private void agregarRegistroATabla(RegistroEntradaSalida r){//Profesor profesor,Timestamp horaRegistro,String tipoRegistro){
		/*RegistroEntradaSalida r = new RegistroEntradaSalida(
				profesor.getNombre()+' '+profesor.getApellidoPaterno()+' '+profesor.getApellidoMaterno(),tipoRegistro,listaRegistros.size()+1,
				horaRegistro.toString());*/
		TableColumn<RegistroEntradaSalida, String> columnaNoRegistro = ((TableColumn<RegistroEntradaSalida, String>) tablaRegistros.getColumns().get(0));
		TableColumn<RegistroEntradaSalida, String> columnaNombreEmpleado = ((TableColumn<RegistroEntradaSalida, String>) tablaRegistros.getColumns().get(1)); 
		TableColumn<RegistroEntradaSalida,String > columnaTipoRegistro = ((TableColumn<RegistroEntradaSalida,String>) tablaRegistros.getColumns().get(2));
		TableColumn<RegistroEntradaSalida,String > columnaHoraRegistro = ((TableColumn<RegistroEntradaSalida,String>) tablaRegistros.getColumns().get(3));
		listaRegistros.add(0, r);
		System.out.println(listaRegistros.size());
		columnaNoRegistro.setCellValueFactory(new PropertyValueFactory<RegistroEntradaSalida,String>("idRegistro"));
		columnaNombreEmpleado.setCellValueFactory(new PropertyValueFactory<RegistroEntradaSalida,String>("nombreEmpleado"));
		columnaTipoRegistro.setCellValueFactory(new PropertyValueFactory<RegistroEntradaSalida,String>("tipoRegistro"));
		columnaHoraRegistro.setCellValueFactory(new PropertyValueFactory<RegistroEntradaSalida,String>("fechaRegistro"));
	
	}
	
	private void configuracionTabla(){
		tablaRegistros.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		ObservableList<TableColumn<RegistroEntradaSalida, ?>> columnas = tablaRegistros.getColumns();
		int cantidadColumnas = columnas.size();
		
		for(int i=0;i<cantidadColumnas;i++){
			columnas.get(i).setMaxWidth(1f * Integer.MAX_VALUE*(100/cantidadColumnas));
		}
	}

}
