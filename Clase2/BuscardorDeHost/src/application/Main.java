package application;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import worker.Buscador;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application implements Buscador.OnHostListener {
	
	TextArea area;
	Button boton_buscar;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			VBox root = new VBox();
			
			area = new TextArea();
			boton_buscar = new Button("Buscar");
			boton_buscar.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					//Activar hilo cuando damos click al botón
					Buscador h = new Buscador();
					h.setOnHostListener(Main.this);
					h.start();
				}
			});
			
			root.getChildren().add(boton_buscar);
			root.getChildren().add(area);
			
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//Cerrar el programa al cerrar la ventana
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {	
				@Override
				public void handle(WindowEvent event) {
					Platform.exit();
			        System.exit(0);
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void onHostDetected(String host) {
		area.appendText(host + "\n");
	}
}
