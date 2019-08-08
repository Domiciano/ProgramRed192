package main;

import worker.Worker;
import worker.WorkerAlt;

public class App implements Worker.OnCounterCount{
	
	public App() {
		Worker alfa = new Worker();
		//6. Asignar el listener
		alfa.setListener( this );
		alfa.start();
		
		WorkerAlt beta = new WorkerAlt();
		Thread h = new Thread(beta);
		//h.start();
	}

	@Override
	public void onCount(int count) {
		System.out.println(count);
		
	}
	
}
