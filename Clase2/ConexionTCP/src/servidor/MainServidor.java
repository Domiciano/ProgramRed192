package servidor;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServidor {
	public static void main(String[] args) {
		
		//Vamos a esperar un cliente
		try {
			//Estoy escuchando en el canal 5555
			ServerSocket server = new ServerSocket(5555);
			
			//Aceptar el cliente cuando solicite la conexión
			//HANDSHAKE
			System.out.println("Esperando conexión...");
			Socket socket = server.accept(); //--> Queda estancado hasta que el cliente mande la solicitud
			System.out.println("Conexion aceptada");
			
			
			InputStream is = socket.getInputStream();
			InputStreamReader reader = new InputStreamReader(is);
			BufferedReader breader = new BufferedReader(reader);
			
			while(true) {
				System.out.println("Esperando mensaje...");
				String mensajeRecibido = breader.readLine();
				System.out.println("Mensaje recibido");
				System.out.println(mensajeRecibido);
			}
			
			//socket.close();
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
