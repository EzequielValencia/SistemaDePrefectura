package Prestamos;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.TextArea;

import javafx.scene.image.ImageView;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class PrestamosController implements Initializable{
	@FXML
	private TableView<Prestamo> tablaPrestamos;
	@FXML
	private TableColumn colMumeroPrestamo;
	@FXML
	private TableColumn colNombreArticulo;
	@FXML
	private TableColumn colDesArticulo;
	@FXML
	private TableColumn colNombreSolicitante;
	@FXML
	private TableColumn colFechaPrestamo;
	@FXML
	private TableColumn colFechaEntrega;
	@FXML
	private TableColumn colFechaEntrega1;
	@FXML
	private TextField textNombreArticulo;
	@FXML
	private ImageView imagenArticulo;
	@FXML
	private TextArea textoDescripcionArticulo;
	@FXML
	private TextField textoCantidadDisponibles,textoNombreSolicitante,textoCodigoMatricula;
	@FXML
	private Button botonAgregar,botonBuscarArticulo,botonBuscarSolicitante;
	@FXML
	private TextField textCodigoArticulo;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		configuracionTabla();
	}
	
	private void configuracionTabla() {
		// TODO Auto-generated method stub
		tablaPrestamos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		ObservableList<TableColumn<Prestamo, ?>> columnas = tablaPrestamos.getColumns(); 
		columnas.get(0).setMaxWidth(1f*Integer.MAX_VALUE*10);
		columnas.get(1).setMaxWidth(1f*Integer.MAX_VALUE*15);
		columnas.get(2).setMaxWidth(1f*Integer.MAX_VALUE*15);
		columnas.get(3).setMaxWidth(1f*Integer.MAX_VALUE*15);
		columnas.get(4).setMaxWidth(1f*Integer.MAX_VALUE*15);
		columnas.get(5).setMaxWidth(1f*Integer.MAX_VALUE*15);
		columnas.get(6).setMaxWidth(1f*Integer.MAX_VALUE*15);
	}

}
