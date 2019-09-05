package servidor.comunicacion;

import java.io.IOException;
import java.net.Socket;

import model.Usuario;
import servidor.comunicacion.Receiver.OnMessageListener;

//Vinculo con el cliente
public class Connection {
	private OnMessageListener main;
	private Socket socket;
	private Sender sender;
	private Receiver receiver;
	private Usuario user;
	
	public Connection(Socket socket) {
		this.socket = socket;
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

		public void init() {
			initReaderAndWriter();
		}

		public void setObserver(OnMessageListener main) {
			this.main = main;
		}



		public Usuario getUser() {
			return user;
		}



		public void setUser(Usuario user) {
			this.user = user;
		}
	
		
		
		
}
