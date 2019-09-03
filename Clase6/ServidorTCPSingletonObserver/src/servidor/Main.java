package servidor;

import servidor.comunicacion.Receiver.OnMessageListener;
import servidor.comunicacion.TCPConnection;

public class Main implements OnMessageListener{
	
	public static void main(String[] args) {
		Main main = new Main();
	}
	
	public Main() {
		TCPConnection conexion = TCPConnection.getInstance().setPuerto(5000);
		conexion.setMain(this);
		conexion.waitForConnection();
	}

	@Override
	public void onMessage(String msg) {
				
	}
}
