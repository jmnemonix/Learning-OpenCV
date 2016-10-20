package kapitel_2.u4_Socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import general.tools.Random;
import general.values.DatagramSetup;
import general.values.PortSetup;

public class Sender {

	public static void main(String[] args) throws Exception {
		
		int    localPort = PortSetup.STD_PORT_1;
		String host      = "localhost";
		int    port      = PortSetup.STD_PORT_3;
		String msg       = "Hallo World! Random: " + Random.fromRage(0,99);
		
		if ( args.length == 4 ){
			localPort = Integer.parseInt( args[0] );
			host      = args[1];
			port      = Integer.parseInt( args[2] );
			msg       = args[3];
		}
		
		System.out.println("(Sender) starting on port: " + localPort);

		try ( DatagramSocket socket = new DatagramSocket(localPort) ) {
			
			System.out.println("(Sender) sending: '" + msg + "' to: " + host + ":" + port );
			
			InetAddress addr = InetAddress.getByName(host);
			DatagramPacket packet = new DatagramPacket(new byte[DatagramSetup.BUFFER_SIZE], DatagramSetup.BUFFER_SIZE, addr, port);
			byte[] data = msg.getBytes();
			packet.setData(data);
			packet.setLength(data.length);
			
			socket.send(packet);
		}

	}

}
