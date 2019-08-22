package client;

import java.util.Scanner;

import client.Receiver.OnMessageListener;



public class ClientMain implements OnMessageListener {

	public static void main(String[] args) {
		ClientMain main = new ClientMain();		
	}
	
	public ClientMain() {
		TCPConnection connection = TCPConnection.getInstance();
		connection.setPuerto(5000);
		connection.setIp("127.0.0.1");
		connection.setMain(this);
		connection.requestConnection();
		
		
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