package cliente.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.google.gson.Gson;

import cliente.comunicacion.TCPConnection;
import cliente.model.Mensaje;
import cliente.comunicacion.Receiver.OnMessageListener;
import cliente.view.Chat;
import javafx.application.Platform;

public class ChatController implements OnMessageListener{
	
	private Chat referencia;
	private Gson gson;
	
	public ChatController(Chat referencia) {
		this.referencia = referencia;
		gson = new Gson();
		initView();
	}

	private void initView() {
		
		TCPConnection.getInstance().setMain(this);
		
		referencia.getEnviarB().setOnAction(
				(event)->{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Mensaje mensaje = new Mensaje(referencia.getMensajeTF().getText(), sdf.format(Calendar.getInstance().getTime()));
					TCPConnection.getInstance().sendMessage(gson.toJson(mensaje));
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