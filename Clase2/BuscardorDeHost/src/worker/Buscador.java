package worker;

import java.net.InetAddress;
import java.net.NetworkInterface;

public class Buscador extends Thread{

	OnHostListener listener;
	
	
	@Override
	public void run() {
		try {
			InetAddress address = InetAddress.getLocalHost();
			String local = address.getHostAddress();
			System.out.println(local);
			NetworkInterface myNI = NetworkInterface.getByInetAddress(address);
			int prefix = myNI.getInterfaceAddresses().get(0).getNetworkPrefixLength();
			System.out.println("Prefijo de la red: " + prefix);			
			
			String[] ip = local.split("\\.");
			//Transformo la ip de un arreglo de string a un arreglo de int
			int[] localhost = new int[] {
					Integer.parseInt(ip[0]),
					Integer.parseInt(ip[1]),
					Integer.parseInt(ip[2]),
					Integer.parseInt(ip[3]),
			};
			
			
			int bitsSubred = prefix;
			int bitsHost = 32 - bitsSubred;
			System.out.println("La dirección IP tiene " + bitsSubred + " bits para la subred.");
			System.out.println("La dirección IP tiene " + bitsHost + " bits para la host.");
			
			
			int[] mascara = obtenerArregloDeMascara(prefix);
			int[] subred = calcularSubred(localhost,mascara);
			
			
			System.out.println("La máscara es "+mascara[0]+"."+mascara[1]+"."+mascara[2]+"."+mascara[3]);
			System.out.println("La dirección de host es "+ip[0]+"."+ip[1]+"."+ip[2]+"."+ip[3]);
			System.out.println("La dirección de subred es "+subred[0]+"."+subred[1]+"."+subred[2]+"."+subred[3]);
			
			int[] direccion = subred;
			for(int i=1 ; i<Math.pow(2, bitsHost) ; i++) {
				direccion[3] = (i%256);
				if(i/256 >= 1) direccion[2] = ((i/256) % 256);
				//65536 = 256^2
				if(i/65536 >= 1) direccion[1] = (i/65536) % 256;
				//16777216 = 256^3
				if(i/16777216 >= 1) direccion[0] = (i/16777216) % 256;
				
				System.out.println("Probando> "+direccion[0]+"."+direccion[1]+"."+direccion[2]+"."+direccion[3]);
				
				InetAddress addi = InetAddress.getByName(direccion[0]+"."+direccion[1]+"."+direccion[2]+"."+direccion[3]);
				if(addi.isReachable(500)) {
					if(listener != null) listener.onHostDetected("<<<"+addi.getHostAddress()+">>>");
				}
			}
		}catch (Exception e) {
			
		}
	}
	
	private int[] calcularSubred(int[] ip, int[] mascara) {
		//Direccion de subred = Multiplicar el host por la máscara bit a bit
		int[] output = new int[4];
		output[0] = ip[0] & mascara[0];
		output[1] = ip[1] & mascara[1];
		output[2] = ip[2] & mascara[2];
		output[3] = ip[3] & mascara[3];
		return output;
	}

	private int[] obtenerArregloDeMascara(int prefix) {
		// Se utilizan operadores >> y << que son operaciones de bits
		// para obtener los números de la máscara
		long base = (int) (Math.pow(2, prefix)-1)<<(32-prefix);
		System.out.println("MASCARA BINARIA> "+Long.toBinaryString(base));
		
		//Uso long para las operaciones para tener hasta 64 bits
		//Un entero long tiene 64 bits en total. un Int tiene 32.
		long byte4 = base & 255L;
		long byte3 = (base & (255L<<8))>>8;
		long byte2 = (base & (255L<<16))>>16;
		long byte1 = (base & (255L<<24))>>24;
		
		int[] output = new int[4];
		output[0] = (int) byte1;
		output[1] = (int) byte2;
		output[2] = (int) byte3;
		output[3] = (int) byte4;
		return output;
	}

	public void setOnHostListener(OnHostListener listener) {
		this.listener = listener;
	}
	
	public interface OnHostListener {
		public void onHostDetected(String host);
	}
	
	

}
