package worker;

public class WorkerAlt implements Runnable{
	int contador;
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(5000);
				contador ++;
				System.out.println(contador);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	
}
