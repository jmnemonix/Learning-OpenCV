import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import kapitel_1.u4_IP_Adressen_Sockets.Lookup;

public class EchoClient {
	/**
	 *
	 * Aufruf: java -cp bin EchoClient <hostname> <port> <Msg>
	 *
	 */

	private static final int BUFFER_SIZE = 508;

	private static final String NAME = "EchoClient";

	public static void main(String[] args) {

		String host = "localhost";
		int    port = 50000;
		byte[] data = "exit".getBytes();

		if (args.length == 3) {
			host = args[0];
			port = Integer.parseInt(args[1]);
			data = args[2].getBytes();
		}

		try ( DatagramSocket socket = new DatagramSocket() ) {

			socket.setSoTimeout(2000);

			InetAddress addr = Lookup.lookup(host);

			DatagramPacket packetOut = new DatagramPacket(data, data.length, addr, port);

			socket.send(packetOut);

			DatagramPacket packetIn = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);

			socket.receive(packetIn);

			String received = new String(packetIn.getData(), 0, packetIn.getLength());

			print("Empfangen: " + received);

		} catch (Exception e ) {
			e.printStackTrace();
		}

		print("Exit");
	}

	private static void print(String s) {
		System.out.println("("+NAME+") "+s);
	}

}
