package cliente.controller;

import cliente.comunicacion.TCPConnection;
import cliente.comunicacion.Receiver.OnMessageListener;
import cliente.view.Ventana2;
import javafx.application.Platform;

public class ChatController implements OnMessageListener{
	
	private Ventana2 referencia;
	
	public ChatController(Ventana2 referencia) {
		this.referencia = referencia;
		initView();
	}

	private void initView() {
		
		TCPConnection.getInstance().setMain(this);
		
		referencia.getEnviarB().setOnAction(
				(event)->{
					TCPConnection.getInstance().sendMessage(referencia.getMensajeTF().getText());
					referencia.getMensajeTF().setText("");
				}
				);
		
		referencia.setOnCloseRequest(
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
					referencia.getMensajesTA().appendText(msg+"\n");
				}
				);	
	}
	
	
}