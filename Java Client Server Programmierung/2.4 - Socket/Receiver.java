import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Receiver {

	private static final int BUFFER_SIZE = 508;

	public static void main(String[] args) {

		int    port       = 50000;
		int    remotePort = 40000;
		String remoteHost = "localhost";


		if ( args.length == 3 ){
			port       = Integer.parseInt( args[0] );
			remoteHost = args[1];
			remotePort = Integer.parseInt( args[2] );
		}

		System.out.println("(Receiver) starting ... ");

		try ( DatagramSocket socket = new DatagramSocket(port) ) {

			socket.connect(InetAddress.getByName(remoteHost), remotePort);

			DatagramPacket packet = new  DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);

			while (true) {

				socket.receive(packet);
				String text = new String(packet.getData(), 0, packet.getLength() );

				System.out.println("(Receiver) " + packet.getAddress().getHostAddress() + ":" + packet.getPort() + " > " + text);

			}

		} catch ( IOException e ) {
			System.err.println(e);
		}



	}

}
