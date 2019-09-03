package servidor;

import java.util.Scanner;

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
		
		/*
		Scanner scan = new Scanner(System.in);
		while(true) {
			String line = scan.nextLine();
			TCPConnection.getInstance().sendBroadcast(line);
		}
		*/
		
	}

	@Override
	public void onMessage(String msg) {
		System.out.println(msg);
		TCPConnection.getInstance().sendBroadcast(msg);
	}
}
