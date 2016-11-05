import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class EchoServer {
	/**
	 *
	 * Aufruf: java -cp bin EchoServer <port>
	 *
	 */

	private static final int BUFFER_SIZE = 508;

	public static void main(String[] args) {

		int port = 50000;

		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		}

		try ( DatagramSocket socket = new DatagramSocket(port) ) {

			DatagramPacket packetIn  = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
			DatagramPacket packetOut = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);

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
