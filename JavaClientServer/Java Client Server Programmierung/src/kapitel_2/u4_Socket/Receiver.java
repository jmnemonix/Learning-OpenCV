package kapitel_2.u4_Socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import general.values.MyBuffer;

public class Receiver {
	
//	private static final int BUFSIZE = 508;

	public static void main(String[] args) {
		
		if ( args.length < 3 ){
			String[] nargs = { "50000", "localhost", "40000"};
			args = nargs;
		}
		
		int port = Integer.parseInt( args[0] );
		String remoteHost = args[1];
		int remotePort = Integer.parseInt( args[2] );
		
		try ( DatagramSocket socket = new DatagramSocket(port) ) {
			
			socket.connect(InetAddress.getByName(remoteHost), remotePort);
			
			DatagramPacket packet = new  DatagramPacket(new byte[MyBuffer.SIZE], MyBuffer.SIZE);
			
			while (true) {
				
				socket.receive(packet);
				String text = new String(packet.getData(), 0, packet.getLength() );
				
				System.out.println(packet.getAddress().getHostAddress() + ":" + packet.getPort() + " > " + text);
				
			}
			
		} catch ( IOException e ) {
			System.err.println(e);
		}
		
		

	}

}