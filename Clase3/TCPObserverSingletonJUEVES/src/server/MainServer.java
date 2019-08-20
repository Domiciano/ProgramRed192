package server;

import java.util.Calendar;
import java.util.Scanner;

import server.TCPConnection.OnMessageListener;

public class MainServer implements OnMessageListener{

	public static void main(String[] args) {
		MainServer serverexe = new MainServer();
	}
	
	public MainServer() {
		
		//Calendar c = Calendar.getInstance();
		//System.out.println(c.getTime());
		
		
		TCPConnection connection = TCPConnection
				.getInstance()
				.setPuerto(5555)
				.waitForConnection();
		
		connection.setListener(this);
		connection.listenToMessages();
		
		Scanner scan = new Scanner(System.in);
		while(true) {
			String line = scan.nextLine();
			connection.sendMessage(line);
		}
		
	}

	@Override
	public void onMessage(String msg) {
		System.out.println(">>"+msg);
	}

}
