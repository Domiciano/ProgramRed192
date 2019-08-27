package cliente.comunicacion;

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

import cliente.comunicacion.Receiver.OnMessageListener;




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
		if(receiver!=null) receiver.setListener(main);
	}
	
	public TCPConnection setIp(String ip) {
		this.ip = ip;
		return instance;
	}
	
	public TCPConnection setPuerto(int puerto) {
		this.puerto = puerto;
		return instance;
	}
	
	public TCPConnection waitForConnection() {
		try {
			server = new ServerSocket(puerto);
			System.out.println("Esperando conexión");
			socket = server.accept();
			System.out.println("Conexion aceptada");
			initReaderAndWriter();
			sendMessage("HOLA");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public TCPConnection requestConnection() {
		try {
			System.out.println("Solicianto conexión");
			socket = new Socket(ip, puerto);
			System.out.println("Conexión establecida");
			initReaderAndWriter();
			sendMessage("HOLA");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instance;
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
