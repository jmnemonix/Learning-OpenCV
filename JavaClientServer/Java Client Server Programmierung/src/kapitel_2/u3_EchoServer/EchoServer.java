package kapitel_2.u3_EchoServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class EchoServer {
	/**
	 * 
	 * Aufruf: java -cp bin EchoServer <port>
	 * 
	 */
	
	
	private static final int BUFSIZE = 508;

	public static void main(String[] args) {

		
		int port = Integer.parseInt(args[0]);
		
		try ( DatagramSocket socket = new DatagramSocket(port) ) {
			
			DatagramPacket packetIn  = new DatagramPacket(new byte[BUFSIZE], BUFSIZE);
			DatagramPacket packetOut = new DatagramPacket(new byte[BUFSIZE], BUFSIZE);
			
			System.out.println(" Server gestartet ...");
			
			while (true) {
				
				socket.receive(packetIn);
				System.out.println("Empfangen: " + packetIn.getLength() + " bytes");
				
				packetOut.setData(packetIn.getData());
				packetOut.setLength(packetIn.getLength());
				
				packetOut.setSocketAddress(packetIn.getSocketAddress());
				
				socket.send(packetOut);
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
