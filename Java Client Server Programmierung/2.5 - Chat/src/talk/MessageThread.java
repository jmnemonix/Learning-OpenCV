package talk;

import java.io.IOException;
import java.net.DatagramPacket;

public class MessageThread implements Runnable {

	@Override
	public void run() {
		DatagramPacket packetIn = new DatagramPacket(new byte[Talk.BUFFER_SIZE], Talk.BUFFER_SIZE);

		while (true) {
			try {
				Talk.receive(packetIn);
			} catch (IOException e) {
				break;
			}
		}
	}
	
	
}
