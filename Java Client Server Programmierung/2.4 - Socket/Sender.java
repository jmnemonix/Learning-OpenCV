import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Sender {

	private static final int BUFFER_SIZE = 508;

	public static void main(String[] args) throws Exception {

		int    localPort = 40000;
		int    port      = 50000;
		String host      = "localhost";
		String msg       = "Hallo World! Random: " + rndmFromRage(0,99);


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
			DatagramPacket packet = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE, addr, port);
			byte[] data = msg.getBytes();
			packet.setData(data);
			packet.setLength(data.length);

			socket.send(packet);
		}

	}

	public static int rndmFromRage(int min, int max) {
		int range = (max - min) + 1;
		return (int)(Math.random() * range) + min;
	}

}
