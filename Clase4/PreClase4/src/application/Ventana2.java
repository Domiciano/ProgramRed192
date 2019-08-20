package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ventana2 extends Stage{
	private Scene scene;
	private Label instruccionesL;
	private TextField mensajeTF;
	private Button enviarB;
	private TextArea mensajesTA;
	
	
	public Ventana2() {
		mensajeTF = new TextField();
		mensajesTA = new TextArea();
		instruccionesL = new Label("Escriba el mensaje que quiere enviar y luego pulse el boton enviar");
		enviarB = new Button("Enviar");
		
		VBox root = new VBox();
		root.getChildren().add(instruccionesL);
		root.getChildren().add(mensajeTF);
		root.getChildren().add(enviarB);
		root.getChildren().add(mensajesTA);
		scene = new Scene(root,400,400);
		this.setScene(scene);
	}
}
