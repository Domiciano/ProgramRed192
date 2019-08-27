package servidor;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import servidor.comunicacion.Receiver.OnMessageListener;
import servidor.comunicacion.TCPConnection;

public class Ventana1 extends Stage implements OnMessageListener{

	private Scene scene;
	private Label subtitulo;
	private Label instruccionesL;
	
	
	public Ventana1() {
		subtitulo = new Label("Pantalla de conexión");
		instruccionesL = new Label(">Espere hasta que un cliente se conecte a usted...");
		
		VBox root = new VBox();
		root.getChildren().add(subtitulo);
		root.getChildren().add(instruccionesL);
		
		scene = new Scene(root,400,200);
		this.setScene(scene);
		this.setTitle("Servidor");
		
		TCPConnection conexion = TCPConnection.getInstance();
		conexion.setPuerto(5000);
		conexion.setMain(this);
		new Thread(
				()->{
					conexion.waitForConnection();			
				}
				).start();
		
		
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
