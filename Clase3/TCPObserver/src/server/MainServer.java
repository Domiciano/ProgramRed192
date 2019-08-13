package server;

public class MainServer {

	public static void main(String[] args) {
		TCPConnection connection = new TCPConnection();
		connection.setPuerto(5555);
		connection.waitForConnection();
		
	}

}
