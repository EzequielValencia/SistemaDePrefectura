package Prestamos;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage)  {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Prestamos/vistaPrestamos.fxml"));
			Scene scene = new Scene(root,883,516);
			scene.getStylesheets().add(getClass().getResource("/Prestamos/prestamos.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
