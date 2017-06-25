package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainFrameController implements Initializable{

	@FXML private JFXDrawer sideBar;
	@FXML private  Pane visorDeSecciones,visorDeSecciones1;
	public static Pane visorSecciones,visorSecciones1;
	@FXML private JFXHamburger menuAmburguesa;
	public static JFXHamburger menu;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		visorSecciones = visorDeSecciones;
		visorSecciones1 = visorDeSecciones1;
		menu = menuAmburguesa;
		// TODO Auto-generated method stub
		try {
			
			VBox sideBar = FXMLLoader.load(getClass().getResource("/view/sideBar.fxml"));
			
			this.sideBar.setSidePane(sideBar);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		transformacionAFlecha();
	}

	
	private void transformacionAFlecha() {
		// TODO Auto-generated method stub
		
		HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(menuAmburguesa);
		
		transition.setRate(-1);

		menuAmburguesa.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
			transition.setRate(transition.getRate()*-1);
			transition.play();
			
			if(sideBar.isShown()){
			
				sideBar.close();
			}else{
				
				sideBar.open();

			}
		});
	}

}
