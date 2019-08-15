package server;

import server.TCPConnection.OnMessageListener;

public class MainServer implements OnMessageListener{

	public static void main(String[] args) {
		MainServer serverexe = new MainServer();
	}
	
	public MainServer() {
		TCPConnection connection = new TCPConnection();
		connection.setPuerto(5555);
		//connection.setListener(this);
		connection.waitForConnection();
		//connection.listenToMessages();
		connection.receiveFile("D:/Recibidos/JAVARecibido.pdf");
	}

	@Override
	public void onMessage(String msg) {
		System.out.println(">>"+msg);
	}

}
