package chat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainChat extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		// TODO Auto-generated method stub
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/chat/vistaChat.fxml"));
			Scene scene = new Scene(root,400,300);
			

			primaryStage.setScene(scene);

			
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
