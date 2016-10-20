package kapitel_2.u4_Socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import general.values.MyBuffer;

public class Sender {

	public static void main(String[] args) throws Exception {
		
		if ( args.length < 4 ){
			String[] nargs = { "40000", "localhost", "50000", "Hallo World!"};
			args = nargs;
		}
		
		int localPort = Integer.parseInt( args[0] );
		String host = args[1];
		int port = Integer.parseInt( args[2] );
		String msg = args[3];
		

		try ( DatagramSocket socket = new DatagramSocket(localPort) ) {
			
			InetAddress addr = InetAddress.getByName(host);
			DatagramPacket packet = new DatagramPacket(new byte[MyBuffer.SIZE], MyBuffer.SIZE, addr, port);
			byte[] data = msg.getBytes();
			packet.setData(data);
			packet.setLength(data.length);
			
			socket.send(packet);
			
		}

	}

}
