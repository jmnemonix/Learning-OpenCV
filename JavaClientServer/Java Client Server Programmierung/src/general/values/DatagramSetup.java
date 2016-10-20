package general.values;

import java.net.DatagramPacket;

public class DatagramSetup {
	
	public static final int BUFFER_SIZE = 508;
	
	
	public static DatagramPacket newDatagramPacket() {
		return new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
	}

}
