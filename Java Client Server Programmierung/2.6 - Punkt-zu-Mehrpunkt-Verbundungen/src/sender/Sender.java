package sender;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

public class Sender {
	private static final int BUFFER_SIZE = 508;
	private static final int LOOPS = 10;
	
	
	public static void main(String[] args) {
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		
		try(DatagramSocket socket = new DatagramSocket()) {
			InetAddress addr = InetAddress.getByName(host);
			
			DatagramPacket packet = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE, addr, port);
			
			for (int i = 0 ; i < LOOPS ; i++) {
				Random random = new Random();
				
				int value = random.nextInt();
				
				byte[] data = String.valueOf(value).getBytes();
				
				packet.setData(data);
				packet.setLength(data.length);
				socket.send(packet);
				Thread.sleep(3000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
