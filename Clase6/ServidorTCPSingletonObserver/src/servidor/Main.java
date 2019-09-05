package servidor;

import java.util.Scanner;

import com.google.gson.Gson;

import model.Mensaje;
import model.Usuario;
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
		Gson g = new Gson();
		
		//Regular expressions
		
		if(msg.contains("\"mensaje\"") && msg.contains("\"fecha\"")) {
			Mensaje m  = g.fromJson(msg.trim(), Mensaje.class);
			System.out.println(">>>" + m.getMensaje());
			String contenido = m.getMensaje();
			
			if(contenido.contains("::")) {
				//paola::Mensaje directo
				String usuario = contenido.split("::")[0];
				String cuerpo = contenido.split("::")[1];
				Mensaje directo = new Mensaje(cuerpo, m.getFecha());
				
				TCPConnection.getInstance().sendDirect(g.toJson(directo), usuario);
				
			}else {
				TCPConnection.getInstance().sendBroadcast(msg);	
			}
			
			
		}else if(msg.contains("\"nombre\"") && msg.contains("\"codigo\"")) {
			Usuario u = g.fromJson(msg, Usuario.class);
			TCPConnection.getInstance().indentifyUser(u);
		}
		
		
	}
}
