package cliente;

import cliente.comunicacion.Receiver.OnMessageListener;
import cliente.comunicacion.TCPConnection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Ventana1 extends Stage implements OnMessageListener{

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
		
		VBox root = new VBox();
		root.getChildren().add(instruccionesL);
		root.getChildren().add(ipTF);
		root.getChildren().add(puertoTF);
		root.getChildren().add(conectarB);
		scene = new Scene(root,400,400);
		this.setScene(scene);
		
		conectarB.setOnAction(
				(ActionEvent event) -> {
					TCPConnection.getInstance().setIp(ipTF.getText().trim());
					TCPConnection.getInstance().setPuerto( Integer.parseInt(puertoTF.getText().trim()) );
					TCPConnection.getInstance().setMain(this);
					new Thread(
							()->TCPConnection.getInstance().requestConnection()
							).start();
					
				});
		this.setOnCloseRequest(
				(event)->{
					Platform.exit();
			        System.exit(0);
				}
				);
		
		
		
	}

	@Override
	public void onMessage(String msg) {
		Platform.runLater(
				()->{
					if(msg.equals("HOLA")) {
						this.close();
						Ventana2 ventana2 = new Ventana2();
						TCPConnection.getInstance().setMain(ventana2);
						ventana2.show();
					}
				}
				);
	}
	
}
