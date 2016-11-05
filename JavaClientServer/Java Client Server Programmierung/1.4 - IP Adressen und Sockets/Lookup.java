package kapitel_1.u4_IP_Adressen_Sockets;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 
 * Aufruf: java -cp bin Lookup <hostname> 
 * 
 */

public class Lookup {

	public static void main(String[] args) throws UnknownHostException {
		
		InetAddress address = lookup( args[0] );
		System.out.printf("Looking up %s -> %s/%s%n", args[0], address.getHostName(), address.getHostAddress());
		
	}
	
	public static InetAddress lookup(String hostname) throws UnknownHostException {
		return InetAddress.getByName( hostname ) ;
	}

}
