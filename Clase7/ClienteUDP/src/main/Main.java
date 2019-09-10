package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket();
			
			String mensaje = "Hola desde UDP";
			DatagramPacket sendingPacket = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, 
					InetAddress.getByName("127.0.0.1"), 5000);
			
			socket.send(sendingPacket);
			
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
