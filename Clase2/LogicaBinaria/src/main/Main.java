package main;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) {
		
		for(int i=0 ; i<1 ; i++) {
			System.out.println("Numero " + i%256);
		}

		
		try {
			InetAddress local = InetAddress.getLocalHost();
			int prefix = NetworkInterface.getByInetAddress(local).getInterfaceAddresses().get(0).getNetworkPrefixLength();
			System.out.println(local.getHostAddress());
			System.out.println(prefix);
			
			long aux = (long) (Math.pow(2, prefix) - 1);
			System.out.println(Long.toBinaryString(aux));
			
			long mascara = aux<<16;
			System.out.println(Long.toBinaryString(mascara));
			System.out.println(mascara);
			
			//. es un comodin en expresiones regulares
			String[] ip = local.getHostAddress().split("\\.");
			
			long byte1 = Long.parseLong(ip[0]) & 255;
			long byte2 = Long.parseLong(ip[1]) & 255;
			long byte3 = Long.parseLong(ip[2]) & 0;
			long byte4 = Long.parseLong(ip[3]) & 0;
			
			System.out.println(byte1+"."+byte2+"."+byte3+"."+byte4);
			
			System.out.println( 172 & 92 );
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
