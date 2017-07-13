package controllers;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import dao.DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import pojos.Profesor;
import pojos.RegistroEntradaSalida;
/***
 * 
 * RegistroAsistenciaController.
 * Clase que controla los eventos de la ventana registroAsistencia
 */
public class RegistroAsistenciaController implements Runnable,Initializable{
	private String horaEtiqueta,minutoEtiqueta,segundosEtiqueta;
	@FXML private Label etiquetaReloj,etiquetaNombreProfesor,etiquetaTipoTipoRegistro
						,etiquetaFechaRegistro;
	@FXML private JFXTextField textCodigoProfesor;
	@FXML private JFXButton botonBuscaRegistrar;
	@FXML private TableView<RegistroEntradaSalida> tablaRegistros;
	private ObservableList<RegistroEntradaSalida> listaRegistros;
	private Thread hiloReloj;
	@Override
	/**
	 * run
	 * Metodo para correr el hilo y estar fijando la hora en la ventana
	 */
	public void run() {
		System.out.println(Thread.currentThread().getName());
		while(true){
			calculaHora();
			System.out.println(horaEtiqueta+":"+minutoEtiqueta+":"+segundosEtiqueta);
			etiquetaReloj.setText(horaEtiqueta+":"+minutoEtiqueta+":"+segundosEtiqueta);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * Calcula la hora actual del sistema para ponerla en la ventana de registroAsistencia.
	 */
	public void calculaHora(){
		int horaActual,minutoActual,segundoActual;
		Calendar calendario = new GregorianCalendar();
		Date fechaHoraActual = new Date();
		calendario.setTime(fechaHoraActual);
		
		horaActual = calendario.get(Calendar.HOUR_OF_DAY);
		minutoActual = calendario.get(Calendar.MINUTE);
		segundoActual = calendario.get(Calendar.SECOND);
		
		horaEtiqueta = horaActual>9? ""+horaActual:"0"+horaActual;
		minutoEtiqueta = minutoActual>9? ""+minutoActual:"0"+minutoActual;
		segundosEtiqueta = segundoActual>9?""+segundoActual:"0"+segundoActual;
		
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		/*hiloReloj = new Thread(this);
		hiloReloj.setName("Holi reloj");
		hiloReloj.start();*/
		botonBuscaRegistrar.setOnAction(e->buscaProfesor());
		configuracionTabla();
		listaRegistros = FXCollections.observableArrayList();
		tablaRegistros.setItems(listaRegistros);
	}
	
	
	public void eventoDeTeclado(KeyEvent e){
		if(e.getText().toCharArray().length>0 && e.getText().toCharArray()[0] == 13){
			buscaProfesor();
		}
	}
	
	private void buscaProfesor(){
		Profesor profesor;
		String codigo = textCodigoProfesor.getText();
		if(codigo.length()>0){
			textCodigoProfesor.setText("");
			profesor = DAO.buscaProfesor(codigo);
			if(profesor!=null){
				etiquetaNombreProfesor.setText(profesor.getNombre()+' '+profesor.getApellidoPaterno()+' '+profesor.getApellidoMaterno());
				etiquetaFechaRegistro.setText(new Timestamp(new Date().getTime()).toString());
				etiquetaTipoTipoRegistro.setText("SALIDA");
				etiquetaTipoTipoRegistro.setTextFill(Paint.valueOf("#a41313"));
				agregarRegistroATabla(profesor);
			}else{
				System.out.println("no se encontro el profesor");
			}
		}
	}
	@SuppressWarnings("unchecked")
	private void agregarRegistroATabla(Profesor profesor){
		RegistroEntradaSalida r = new RegistroEntradaSalida(
				profesor.getNombre()+' '+profesor.getApellidoPaterno()+' '+profesor.getApellidoMaterno(),"",0,
				new Timestamp(new Date().getTime()).toString());
		TableColumn<RegistroEntradaSalida, String> columnaNoRegistro = ((TableColumn<RegistroEntradaSalida, String>) tablaRegistros.getColumns().get(0));
		TableColumn<RegistroEntradaSalida, String> columnaNombreEmpleado = ((TableColumn<RegistroEntradaSalida, String>) tablaRegistros.getColumns().get(1)); 
		TableColumn<RegistroEntradaSalida,String > columnaTipoRegistro = ((TableColumn<RegistroEntradaSalida,String>) tablaRegistros.getColumns().get(2));
		TableColumn<RegistroEntradaSalida,String > columnaHoraRegistro = ((TableColumn<RegistroEntradaSalida,String>) tablaRegistros.getColumns().get(3));
		
		listaRegistros.add(r);
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
