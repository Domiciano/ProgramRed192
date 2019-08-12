package cliente;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainCliente {

	public static void main(String[] args) {
		
		//Solicitar una conexion
		try {
			Socket socket = new Socket("127.0.0.1", 5555);
			
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter writer = new OutputStreamWriter(os);
			BufferedWriter bwriter = new BufferedWriter(writer);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Enviando dato...");
			bwriter.write("Hola desde el cliente\n");
			bwriter.flush();
			System.out.println("Dato enviado");
			

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			while(true) {}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
