package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ventana1 extends Stage{

	private Scene scene;
	private Label instruccionesL;
	private TextField ipTF;
	private TextField puertoTF;
	private Button conectarB;
	
	public Ventana1() {
		ipTF = new TextField();
		puertoTF = new TextField();
		instruccionesL = new Label("Ingrese la dirección IP y el puerto del servidor al que se quiere conectar");
		conectarB = new Button("Conectar");
		
		conectarB.setOnAction(
				(ActionEvent event) -> {
					Ventana2 ventana2 = new Ventana2();
					ventana2.show();
				});
		
		VBox root = new VBox();
		root.getChildren().add(instruccionesL);
		root.getChildren().add(ipTF);
		root.getChildren().add(puertoTF);
		root.getChildren().add(conectarB);
		scene = new Scene(root,400,400);
		this.setScene(scene);
	}
	
}
