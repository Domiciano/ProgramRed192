package cliente.controller;

import cliente.comunicacion.TCPConnection;

import com.google.gson.Gson;

import cliente.comunicacion.Receiver.OnMessageListener;
import cliente.model.Usuario;
import cliente.view.Login;
import cliente.view.Chat;
import javafx.application.Platform;
import javafx.event.ActionEvent;

public class LoginController implements OnMessageListener{

	private Login referencia;
	private Usuario usuario;
	private Gson gson;
	
	public LoginController(Login referencia) {
		this.referencia = referencia;
		gson = new Gson();
		initView();
	}
	
	
	
	private void initView() {
		referencia.getConectarB().setOnAction(
				(ActionEvent event) -> {
					TCPConnection.getInstance().setIp(referencia.getIpTF().getText().trim());
					TCPConnection.getInstance().setPuerto( Integer.parseInt(referencia.getPuertoTF().getText().trim()) );
					TCPConnection.getInstance().setMain(this);
					new Thread(
							()->{
								TCPConnection.getInstance().requestConnection();
								usuario = new Usuario(referencia.getNombreTF().getText().trim(),referencia.getCodigoTF().getText().trim());
								TCPConnection.getInstance().sendMessage(gson.toJson(usuario));
								Platform.runLater(
										()->{
											referencia.close();
											Chat ventana2 = new Chat();
											ventana2.show();
										}
										);								
							}
							).start();
				});
		
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
					
				}
				);		
	}

}
