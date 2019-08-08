package worker;

import java.io.Serializable;

public class Worker extends Thread {
	int contador;
	//2. Crear objeto de interfaz	
	// LAB REDES --> F0rmul4-1
	OnCounterCount listener;
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(5000);
				contador ++;
				//4. Notificar con el dato
				if(listener != null) listener.onCount(contador);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//3. Generar un metodo SET para el objeto del paso 2
	public void setListener(OnCounterCount listener) {
		this.listener = listener;
	}
	
	//1. Crear interfaz
	public interface OnCounterCount{
		public void onCount(int count);
	}
	

	
}
