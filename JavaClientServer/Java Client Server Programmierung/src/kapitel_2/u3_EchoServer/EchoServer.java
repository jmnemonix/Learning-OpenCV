package kapitel_2.u3_EchoServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import general.Datagrams;
import general.Ports;

public class EchoServer {
	/**
	 * 
	 * Aufruf: java -cp bin EchoServer <port>
	 * 
	 */
	
	public static void main(String[] args) {
		
		int port = Ports.STD_PORT_3;
		
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		}
		
		try ( DatagramSocket socket = new DatagramSocket(port) ) {
			
			DatagramPacket packetIn  = Datagrams.newDatagramPacket();
			DatagramPacket packetOut = Datagrams.newDatagramPacket();
			
			System.out.println("(EchoServer) Server gestartet ...");
			
			while (true) {
				
				socket.receive(packetIn);
				System.out.println("(EchoServer) Empfangen: " + packetIn.getLength() + " bytes");
				String received = new String(packetIn.getData(), 0, packetIn.getLength());
				System.out.println("(EchoServer) Data: " + received);
				
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
