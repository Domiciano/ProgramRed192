package cliente.view;

import cliente.comunicacion.Receiver.OnMessageListener;
import cliente.controller.ChatController;
import cliente.comunicacion.TCPConnection;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Chat extends Stage{
	private Scene scene;
	private Label instruccionesL;
	private TextField mensajeTF;
	private Button enviarB;
	private TextArea mensajesTA;
	
	//Controlador	
	private ChatController controller;
	
	public Chat() {
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
		this.setTitle("Cliente");
		
		controller = new ChatController(this);
	}

	public Button getEnviarB() {
		return enviarB;
	}

	public TextArea getMensajesTA() {
		return mensajesTA;
	}

	public TextField getMensajeTF() {
		return mensajeTF;
	}
	
	
	

}
