package controllers;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
/***
 * 
 * RegistroAsistenciaController.
 * Clase que controla los eventos de la ventana registroAsistencia
 */
public class RegistroAsistenciaController implements Runnable,Initializable{
	private String horaEtiqueta,minutoEtiqueta,segundosEtiqueta;
	private Label etiquetaReloj;
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
		etiquetaReloj = new Label();
		hiloReloj = new Thread(this);
		hiloReloj.setName("Holi reloj");
		hiloReloj.start();
		
	}
	
	

}
