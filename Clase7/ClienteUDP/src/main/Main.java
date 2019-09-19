package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {

	public static final int PORTA = 5215;
	public static final int PORTB = 5000;
	public static final int PORTC = 6000;
	public static final String NODEIPA = "172.30.174.3"; //NODO A
	public static final String NODEIPB = "172.30.180.253"; //NODO B
	public static final String NODEIPC = "172.30.86.81"; //NODO C
	
	public static String NODEIP = NODEIPA;
	public static int SENDINGPORT = PORTA;
	public static int LOCALPORT = 5000;
	
	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket(LOCALPORT);
			String local = InetAddress.getLocalHost().getHostAddress();			
			
			Scanner scan = new Scanner(System.in);
			while(true) {
				String mensaje = scan.nextLine();
				
				mensaje += ",,%IP%,,%PORT%";
				mensaje = mensaje.replace("%IP%", local);
				mensaje = mensaje.replace("%PORT%", ""+LOCALPORT);
				
				System.out.println("Enviado: " + mensaje);
				System.out.println("A la dirección: " + NODEIP + ":" + SENDINGPORT);
				DatagramPacket sendingPacket = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, 
						InetAddress.getByName(NODEIP), SENDINGPORT);				
				socket.send(sendingPacket);				
				
				//Esperando
				byte[] buf = new byte[200];
				DatagramPacket receivedPacket = new DatagramPacket(buf, buf.length);
				System.out.println("Esperando mensaje...");
				socket.receive(receivedPacket);
				System.out.println( "Recibido>"+ new String(receivedPacket.getData()).trim()  );
				System.out.println( ">>>"+ receivedPacket.getSocketAddress().toString()  );
			}
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
