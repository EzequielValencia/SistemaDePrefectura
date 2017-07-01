package application;
	
import dao.DAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class MainSistemaPrefectura extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
			Scene scene = new Scene(root,400,400);
			
			
			primaryStage.setScene(scene);

			primaryStage.getIcons().add(new Image("/images/icon.png"));
			//primaryStage.initStyle( StageStyle.UNDECORATED );
			primaryStage.show();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		boolean seConecto = DAO.conectarDB();
		System.out.println(seConecto);
		if(seConecto){
			launch(args);
		}else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Look, an Error Dialog");
			alert.setContentText("Ooops, there was an error!");

			alert.showAndWait();
		}
	}
	
}
