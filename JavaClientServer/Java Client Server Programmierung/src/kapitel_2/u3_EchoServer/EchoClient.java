package kapitel_2.u3_EchoServer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class EchoClient {
	/**
	 * 
	 * Aufruf: java -cp bin EchoClient <hostname> <port> <Msg>
	 * 
	 */
	
	private static final int BUFSIZE = 508;
	private static final int TIMEOUT = 2000;
	
	public static void main(String[] args) {
		
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		byte[] data = args[2].getBytes();
		
		try ( DatagramSocket socket = new DatagramSocket() ) {
			
			socket.setSoTimeout(TIMEOUT);
			
			InetAddress addr = InetAddress.getByName(host);
			DatagramPacket packetOut = new DatagramPacket(data, data.length, addr, port);
			
			socket.send(packetOut);
			
			DatagramPacket packetIn = new DatagramPacket(new byte[BUFSIZE], BUFSIZE);
			
			socket.receive(packetIn);
			
			String received = new String (packetIn.getData(), 0, packetIn.getLength());
			
			System.out.println("Received: " + received);
			
		} catch (SocketException e) {
			System.err.println(e);
		} catch (Exception e ) {
			System.err.println(e);
		}
	}

}
