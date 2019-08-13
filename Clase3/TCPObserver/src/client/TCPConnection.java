package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPConnection {

	private ServerSocket server;
	private Socket socket;
	private BufferedWriter bwriter;
	private BufferedReader breader;
	private int puerto;
	private String ip; 
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}
	
	public void waitForConnection() {
		try {
			server = new ServerSocket(puerto);
			System.out.println("Esperando conexión");
			socket = server.accept();
			System.out.println("Conexion aceptada");
			initReaderAndWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void requestConnection() {
		try {
			System.out.println("Solicianto conexión");
			socket = new Socket(ip, puerto);
			System.out.println("Conexión establecida");
			initReaderAndWriter();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initReaderAndWriter() {
		try {
			breader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bwriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
