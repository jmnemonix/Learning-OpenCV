package talk;
import java.awt.EventQueue;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import talk.setup.Data;
import talk.view.MainFrame;
import talk.view.SetupFrame;

public class Talk {

//	private static final String NAME = "Talk";

	public static final boolean PRINT = true;
	public static final int BUFFER_SIZE = 508;

	private static DatagramSocket socket;
	private static DatagramPacket packetOut;
	private static InetAddress remoteAddress;
	
	private static Data data;	

	// --- Interface ---
	
	private static MainFrame mFrame;
	
	// --- Core ---
	
	private static MessageThread mThread;

	public static void main(String[] args) {
		
		SetupFrame sFrame = new SetupFrame();
		
		sFrame.pack();
		sFrame.setVisible(true);
		
	}

	public static void receive(DatagramPacket packetIn) throws IOException {

		socket.receive(packetIn);

		final String text = new String(packetIn.getData(), 0, packetIn.getLength());

		try {
			EventQueue.invokeLater(
				() -> {
					mFrame.appendText(text);
				}
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean sendMessage(String message) {
		
		String text = data.getUserName() + ": " + message;
		boolean success = false;
		
		try {
			byte[] data = text.getBytes();

			packetOut.setData(data);
			packetOut.setLength(data.length);
	
			socket.send(packetOut);
			success = true;
		} catch (IOException e) {
			System.err.println(e);
		}
		
		if (success) {
			mFrame.appendText(text);
		}
		
		return success;
		
	}
	public static void close() {
		if (socket != null) {
			socket.close();
		}
		System.exit(0);
	}
	public static void start(Data data) {
		Talk.data = data;
		
		String userName = data.getUserName();
		mFrame = new MainFrame(userName);
		
		try {
			setupNetwork();
			// Nachrichten Thread starten
			Thread t = new Thread(mThread);
			t.start();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
	private static void setupNetwork() throws UnknownHostException, SocketException {
		remoteAddress = InetAddress.getByName(data.getRemoteHost());
		
		socket = new DatagramSocket(data.getLocalPort());
		packetOut = new DatagramPacket(
				new byte[BUFFER_SIZE], BUFFER_SIZE,
				remoteAddress,
				data.getRemotePort());
		
		mThread = new MessageThread();
	}

}
