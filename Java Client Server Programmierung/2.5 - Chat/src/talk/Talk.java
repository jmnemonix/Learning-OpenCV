package talk;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.bind.JAXB;

import talk.setup.Data;
import talk.view.MainFrame;

import javax.swing.WindowConstants;

public class Talk implements Runnable {

//	private static final String NAME = "Talk";

	// --- Networking ---
//	private String user;
//	private int    localPort  = 40000;
//	private String remoteHost = "localhost";
//	private int    remotePort = 50000;

	private static final int BUFFER_SIZE = 508;

	private static DatagramSocket socket;
	private static DatagramPacket packetOut;
	
	private static Data data;	

	// --- Interface ---
	
	private static MainFrame mFrame;

	public static void main(String[] args) {

		try {
			new Talk(args);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}

	}

	public Talk(String[] args) throws SocketException, UnknownHostException { //throws Exception {

//		setParameters(args);
//
//		if ( 	user == null   || remoteHost == null ||
//				localPort == 0 || remotePort == 0 ) {
////			throw new RuntimeException("Parameter Fehlen!");
//
//			try {
//				
//
////				user       = sdata.;
////				localPort  = sdata.getMyPort();
////				remoteHost = sdata.getRemoteHost();
////				remotePort = sdata.getRemotePort();
//				System.out.println("XML loaded!");
//			} catch (Exception e) {
//				throw new RuntimeException("Parameter Fehlen!");
//			}
//		}

//		InetAddress remoteAddress = InetAddress.getByName(remoteHost);
//
//		
//
//		socket = new DatagramSocket(localPort);
//
//		packetOut = new DatagramPacket(
//				new byte[BUFFER_SIZE], BUFFER_SIZE,
//				remoteAddress, remotePort);
		
		// Nachrichten Thread starten
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		DatagramPacket packetIn = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);

		while (true) {
			try {
				receive(packetIn);
			} catch (IOException e) {
				break;
			}
		}
	}

	private void receive(DatagramPacket packetIn) throws IOException {

		socket.receive(packetIn);

		final String text = new String(packetIn.getData(), 0, packetIn.getLength());

		try {
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					mFrame.appendText(text);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public static boolean sendMessage(String message) {
		
		String msg = data.getUserName() + ": " + message;
		
		try {
			byte[] data = msg.getBytes();

			packetOut.setData(data);
			packetOut.setLength(data.length);
	
			socket.send(packetOut);
		} catch (IOException e) {
			System.err.println(e);
		}
		
	}
	public static void close() {
		if (socket != null) {
			socket.close();
		}
		System.exit(0);
	}

	public static void loadData(Data data) {
		this.data = data;
	}
	
	
	
	
	
	
	
	

	

//	private void setParameters(String[] args) {
//		for ( int i = 0; i < args.length; i++ ) {
//
//			switch (args[i]) {
//
//				case "-user":
//					user = args[i+1];
//					break;
//
//				case "-localPort":
//					localPort = Integer.parseInt(args[i+1]);
//					break;
//
//				case "-remoteHost":
//					remoteHost = args[i+1];
//					break;
//
//				case "-remotePort":
//					localPort = Integer.parseInt(args[i+1]);
//					break;
//
//				default:
//					break;
//			}
//
//		}
//	}



}
