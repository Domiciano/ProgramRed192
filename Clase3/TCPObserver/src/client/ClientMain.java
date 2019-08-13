package client;

public class ClientMain {

	public static void main(String[] args) {
		TCPConnection connection = new TCPConnection();
		connection.setIp("127.0.0.1");
		connection.setPuerto(5555);
		connection.requestConnection();
	}

}
