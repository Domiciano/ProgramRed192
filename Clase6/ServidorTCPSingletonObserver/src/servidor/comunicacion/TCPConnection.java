package servidor.comunicacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import servidor.comunicacion.Receiver.OnMessageListener;


//Adminitrador de conexiones
public class TCPConnection {
	
	private static TCPConnection instance;

	private TCPConnection() {
		
	}
	
	public synchronized static TCPConnection getInstance() {
		if(instance == null) instance = new TCPConnection();
		return instance;
	}
	
	//Atributos de objeto
	
	private ServerSocket server;
	private int puerto;
	private String ip; 
	OnMessageListener main;
	
	
	public void setMain(OnMessageListener main) {
		this.main = main;
	}
	
	public TCPConnection setIp(String ip) {
		this.ip = ip;
		return instance;
	}
	
	public TCPConnection setPuerto(int puerto) {
		this.puerto = puerto;
		return instance;
	}
	
	public void waitForConnection() {
		
		new Thread(
				()->{
					try {
						server = new ServerSocket(puerto);
						while(true) {
							System.out.println("Esperando conexión");
							Socket socket = server.accept();
							Connection c = new Connection(socket);
							c.setObserver(main);
							System.out.println("Conexion aceptada");
							c.init();
						}
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				).start();
		
	}
	
	
	
}
