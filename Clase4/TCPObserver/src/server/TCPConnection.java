package server;

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

import server.Receiver.OnMessageListener;

public class TCPConnection {
	
	private static TCPConnection instance;

	private TCPConnection() {
		
	}
	
	public synchronized static TCPConnection getInstance() {
		if(instance == null) instance = new TCPConnection();
		return instance;
	}
	
	//Atributos de objeto
	private OnMessageListener main;
	private ServerSocket server;
	private Socket socket;
	private int puerto;
	private String ip; 
	private Sender sender;
	private Receiver receiver;
	
	public void setMain(OnMessageListener main) {
		this.main = main;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public static TCPConnection setPuerto(int puerto) {
		instance.puerto = puerto;
		return instance;
	}
	
	public static TCPConnection waitForConnection() {
		try {
			instance.server = new ServerSocket(instance.puerto);
			System.out.println("Esperando conexión");
			instance.socket = instance.server.accept();
			System.out.println("Conexion aceptada");
			instance.initReaderAndWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return instance;
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
			sender = new Sender( socket.getOutputStream() );
			receiver = new Receiver( socket.getInputStream() );
			receiver.setListener(main);
			receiver.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	//Metodo de envio:
	public void sendMessage(String msg) {
		sender.sendMessage(msg);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
