package servidor.comunicacion;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Sender {
	private OutputStream os;
	private BufferedWriter bwriter;
	
	public Sender(OutputStream os) {
		this.os = os;
		bwriter = new BufferedWriter(new OutputStreamWriter(os));
	}
	
	public void sendMessage(String msg) {
		new Thread(
				() -> {
					try {
						System.out.println("Enviando mensaje...");
						bwriter.write(msg+"\n");
						bwriter.flush();
						System.out.println("Mensaje enviado");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				).start();
	}
	
	public void sendFile(String path) {
		try {
			File f = new File(path);
			FileInputStream fis = new FileInputStream(f);
			
			int bytesLeidos = 0;
			byte[] buffer = new byte[512];
			while( (  bytesLeidos = fis.read(buffer)  ) != -1 ) {
				os.write(buffer, 0, bytesLeidos);
			}
			fis.close();
			os.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
