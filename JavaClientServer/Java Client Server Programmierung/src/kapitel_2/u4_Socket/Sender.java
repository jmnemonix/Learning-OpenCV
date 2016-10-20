package kapitel_2.u4_Socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import general.values.MyBuffer;

public class Sender {

	public static void main(String[] args) throws Exception {
		
		if ( args.length < 4 ){
			String[] nargs = { "40000", "localhost", "50000", "Hallo World! Random: " + rndm(0,22) };
			args = nargs;
		}
		
		int localPort = Integer.parseInt( args[0] );
		String host = args[1];
		int port = Integer.parseInt( args[2] );
		String msg = args[3];
		
		System.out.println("(Sender) starting on port: " + localPort);
		

		try ( DatagramSocket socket = new DatagramSocket(localPort) ) {
			
			System.out.println("(Sender) sending: '" + msg + "' to: " + host + ":" + port );
			
			InetAddress addr = InetAddress.getByName(host);
			DatagramPacket packet = new DatagramPacket(new byte[MyBuffer.SIZE], MyBuffer.SIZE, addr, port);
			byte[] data = msg.getBytes();
			packet.setData(data);
			packet.setLength(data.length);
			
			socket.send(packet);
		}

	}
	private static int rndm(int min, int max) {
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}

}
