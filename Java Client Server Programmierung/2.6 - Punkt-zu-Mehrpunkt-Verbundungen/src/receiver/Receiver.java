package receiver;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Receiver {
	private static final int BUFFER_SIZE = 508;
	
	public static void main(String[] args) {
		
		String multicastAddress = args[0];
		int port = Integer.parseInt(args[1]);
		
		try(MulticastSocket socket = new MulticastSocket(port)) {
			socket.setTimeToLive(1);
			InetAddress group = InetAddress.getByName(multicastAddress);
			socket.joinGroup(group);
			
			DatagramPacket packetIn = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
			
			while(true) {
				socket.receive(packetIn);
				String received = new String(packetIn.getData(), 0, packetIn.getLength());
				System.out.println(received);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
