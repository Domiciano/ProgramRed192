package client;

import java.util.Scanner;

import client.TCPConnection.OnMessageListener;

public class ClientMain implements OnMessageListener {

	public static void main(String[] args) {
		ClientMain main = new ClientMain();		
	}
	
	public ClientMain() {
		TCPConnection connection = 
				TCPConnection.getInstance()
				.setPuerto(5555)
				.setIp("127.0.0.1");

		connection.setListener(this);
		connection.requestConnection();
		connection.listenToMessages();
		
		Scanner scan = new Scanner(System.in);
		while(true) {
			String line = scan.nextLine();
			connection.sendMessage(line);
		}
		
		//connection.sendFile("D:/Enviados/JAVA.pdf");

	}

	@Override
	public void onMessage(String msg) {
		System.out.println(">"+msg);
		
	}

}