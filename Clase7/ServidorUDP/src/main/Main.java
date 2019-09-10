package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Main {

	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket(5000);
			
			//Recibir mensaje
			byte[] buffer = new byte[50];
			DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
			System.out.println("Recibiendo...");
			socket.receive(receivedPacket);
			
			byte[] bytesRecibidos = receivedPacket.getData();
			String mensajeRecibido = new String(bytesRecibidos);
			
			System.out.println("Recibido> "+mensajeRecibido);
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
