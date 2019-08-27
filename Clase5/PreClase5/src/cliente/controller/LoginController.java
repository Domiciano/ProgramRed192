package cliente.controller;

import cliente.comunicacion.TCPConnection;
import cliente.comunicacion.Receiver.OnMessageListener;
import cliente.view.Ventana1;
import cliente.view.Ventana2;
import javafx.application.Platform;
import javafx.event.ActionEvent;

public class LoginController implements OnMessageListener{

	private Ventana1 referencia;
	
	public LoginController(Ventana1 referencia) {
		this.referencia = referencia;
		
		initView();
	}
	
	
	
	private void initView() {
		referencia.getConectarB().setOnAction(
				(ActionEvent event) -> {
					TCPConnection.getInstance().setIp(referencia.getIpTF().getText().trim());
					TCPConnection.getInstance().setPuerto( Integer.parseInt(referencia.getPuertoTF().getText().trim()) );
					TCPConnection.getInstance().setMain(this);
					new Thread(
							()->TCPConnection.getInstance().requestConnection()
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
					if(msg.equals("HOLA")) {
						referencia.close();
						Ventana2 ventana2 = new Ventana2();
						ventana2.show();
					}
				}
				);		
	}

}
