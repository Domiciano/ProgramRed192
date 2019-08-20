package server;

import java.util.Calendar;
import java.util.Scanner;


public class MainServer implements server.Receiver.OnMessageListener{

	public static void main(String[] args) {
		MainServer serverexe = new MainServer();
	}
	
	public MainServer() {
		
		//Calendar c = Calendar.getInstance();
		//System.out.println(c.getTime());
		
		
		TCPConnection connection = TCPConnection
				.getInstance()
				.setPuerto(5000);
		
		connection.setMain(this);
				
		connection.waitForConnection();
		
		
		
		
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
