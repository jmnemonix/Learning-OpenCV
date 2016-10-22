package kapitel_2.u4_Socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import general.Datagrams;
import general.Ports;

public class Receiver {
	
	public static void main(String[] args) {
		
		int    port       = Ports.STD_PORT_3;
		String remoteHost = "localhost";
		int    remotePort = Ports.STD_PORT_1;
		
		if ( args.length == 3 ){
			port       = Integer.parseInt( args[0] );
			remoteHost = args[1];
			remotePort = Integer.parseInt( args[2] );
		}
	
		System.out.println("(Receiver) starting ... "); 
		
		try ( DatagramSocket socket = new DatagramSocket(port) ) {
			
			socket.connect(InetAddress.getByName(remoteHost), remotePort);
			
			DatagramPacket packet = new  DatagramPacket(new byte[Datagrams.BUFFER_SIZE], Datagrams.BUFFER_SIZE);
			
			while (true) {
				
				socket.receive(packet);
				String text = new String(packet.getData(), 0, packet.getLength() );
				
				System.out.println("(Receiver) " + packet.getAddress().getHostAddress() + ":" + packet.getPort() + " > " + text);
				
			}
			
		} catch ( IOException e ) {
			System.err.println(e);
		}
		
		

	}

}