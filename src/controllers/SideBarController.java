package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class SideBarController implements Initializable{
	@FXML
	private ImageView imagenUsuario;
	@FXML
	private JFXButton botonRegistroPrestamos;
	@FXML 
	private JFXButton botonAsistenciaProfesores;
	@FXML
	private JFXButton botonChat;
	@FXML
	private JFXButton botonCreacionDeExamenes;
	@FXML
	private JFXButton botonSalir;
	


	//private GridPane seccionPrestamos;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Image image = new Image("/images/avatar.png");
		imagenUsuario.setImage(image);
		agregarAcionesABotones();
		crearSeccionPrestamos();
	}

	private void crearSeccionPrestamos(){
		
	}

	private void agregarAcionesABotones(){
		botonSalir.setOnAction(e->System.exit(0));
		botonRegistroPrestamos.setOnAction(e->abrirVistaPrestamos());
		botonCreacionDeExamenes.setOnAction(e->abrirVistaExamenes());
		botonChat.setOnAction(e->{Toast.makeText(((Stage)botonChat.getScene().getWindow()), "Mensaje de prueba", 3500, 500, 500);});
	}
	
	private void abrirVistaExamenes(){
		
		MainFrameController.seccionExamenes.setVisible(true);
		MainFrameController.seccionPrestamosVista.setVisible(false);
		MainFrameController.sideBarVista.close();
	}
	
	private void abrirVistaPrestamos(){
		
		MainFrameController.seccionExamenes.setVisible(false);
		MainFrameController.seccionPrestamosVista.setVisible(true);
		MainFrameController.sideBarVista.close();
	}
}


 final class Toast
{
    public static void makeText(Stage ownerStage, String toastMsg, int toastDelay, int fadeInDelay, int fadeOutDelay)
    {
        Stage toastStage=new Stage();
        toastStage.initOwner(ownerStage);
        toastStage.setResizable(false);
        toastStage.initStyle(StageStyle.TRANSPARENT);

        Text text = new Text(toastMsg);
        text.setFont(Font.font("Verdana", 40));
        text.setFill(Color.RED);

        StackPane root = new StackPane(text);
        root.setStyle("-fx-background-radius: 20; -fx-background-color: rgba(0, 0, 0, 0.2); -fx-padding: 50px;");
        root.setOpacity(0);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        toastStage.setScene(scene);
        toastStage.show();

        Timeline fadeInTimeline = new Timeline();
        KeyFrame fadeInKey1 = new KeyFrame(Duration.millis(fadeInDelay), new KeyValue (toastStage.getScene().getRoot().opacityProperty(), 1)); 
        fadeInTimeline.getKeyFrames().add(fadeInKey1);   
        fadeInTimeline.setOnFinished((ae) -> 
        {
            new Thread(() -> {
                try
                {
                    Thread.sleep(toastDelay);
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                   Timeline fadeOutTimeline = new Timeline();
                    KeyFrame fadeOutKey1 = new KeyFrame(Duration.millis(fadeOutDelay), new KeyValue (toastStage.getScene().getRoot().opacityProperty(), 0)); 
                    fadeOutTimeline.getKeyFrames().add(fadeOutKey1);   
                    fadeOutTimeline.setOnFinished((aeb) -> toastStage.close()); 
                    fadeOutTimeline.play();
            }).start();
        }); 
        fadeInTimeline.play();
    }
}
