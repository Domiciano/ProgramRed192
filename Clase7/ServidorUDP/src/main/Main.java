package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Main {

	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket(5000);
			
			//172.30.152.75
			while(true) {
			//Recibir mensaje
				byte[] buffer = new byte[200];
				DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
				System.out.println("Recibiendo...");
				socket.receive(receivedPacket);
				
				byte[] bytesRecibidos = receivedPacket.getData();
				String mensajeRecibido = new String(bytesRecibidos);
				
				System.out.println("Recibido> "+mensajeRecibido.trim());
			}
			
			 
			/*
			String enviar = "Respuesta desde el server";
			DatagramPacket sendingPacket = new DatagramPacket(enviar.getBytes(), enviar.getBytes().length,
					InetAddress.getByName("127.0.0.1"), 6000);
			socket.send(sendingPacket);
			*/
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
