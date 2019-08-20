package client;

import java.util.Scanner;

public class ClientMain {

	public static void main(String[] args) {
		TCPConnection connection = new TCPConnection();
		connection.setIp("127.0.0.1");
		connection.setPuerto(5555);
		connection.requestConnection();
		
		/*
		Scanner scan = new Scanner(System.in);
		while(true) {
			String line = scan.nextLine();
			connection.sendMessage(line);
		}
		*/
		connection.sendFile("D:/Enviados/JAVA.pdf");
		
	}

}