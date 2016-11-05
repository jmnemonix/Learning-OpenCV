package kapitel_2.u5_chat;

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

import kapitel_1.u4_IP_Adressen_Sockets.Lookup;
import kapitel_2.u5_chat.xmlconfig.SetupData;

public class Talk implements ActionListener, Runnable {
	
//	private static final String NAME = "Talk";
	
	// --- Networking ---
	private String user;
	private int    localPort  = 50000;
	private String remoteHost;
	private int    remotePort = 50000;

	private static final int BUFFER_SIZE = 508;
	
	private DatagramSocket socket;
	private DatagramPacket packetOut;
	
	// --- Interface ---
	private JTextField input;
	private JTextArea output;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		try {
			new Talk(args, frame);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public Talk(String[] args, JFrame frame) throws SocketException, UnknownHostException { //throws Exception {

		setParameters(args);
		
		if ( 	user == null   || remoteHost == null ||
				localPort == 0 || remotePort == 0 ) {
//			throw new RuntimeException("Parameter Fehlen!");
			
			try {
				File file = new File( "data/kap_2.5_chat_setup.xml" );				
				SetupData sdata = JAXB.unmarshal( file, SetupData.class );
				
				user       = sdata.getMyName();
				localPort  = sdata.getMyPort();
				remoteHost = sdata.getRemoteHost();
				remotePort = sdata.getRemotePort();
			} catch (Exception e) {
				throw new RuntimeException("Parameter Fehlen!");
			}
		}
		
		InetAddress remoteAddress = Lookup.lookup(remoteHost);
		
		frame.setTitle("Talk - " + user);
		
		socket = new DatagramSocket(localPort);
		
		packetOut = new DatagramPacket(
				new byte[BUFFER_SIZE], BUFFER_SIZE,
				remoteAddress, remotePort);
		
		frame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosed(WindowEvent e) {
				if (socket != null) {
					socket.close();
				}
				System.exit(0);
			}
		});
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2));
		input = new JTextField(40);
		input.setFont(new Font("Monospaced", Font.PLAIN, 14));
		panel.add(input);
		
		JButton send = new JButton("Senden");
		
		send.addActionListener(this);
		
		panel.add(send);
		frame.add(panel, BorderLayout.NORTH);
		
		output = new JTextArea(10,45);
		output.setFont(new Font("Monospaced", Font.PLAIN, 14));
		output.setLineWrap(true);
		output.setWrapStyleWord(true);
		output.setEditable(false);
		frame.add(new JScrollPane(output), BorderLayout.CENTER);
		
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
					output.append(text);
					output.append("\n");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			String message = user + ": " + input.getText();
			byte[] data = message.getBytes();
			
			packetOut.setData(data);
			packetOut.setLength(data.length);
			
			socket.send(packetOut);
			
			input.requestFocus();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	private void setParameters(String[] args) {
		for ( int i = 0; i < args.length; i++ ) {
			
			switch (args[i]) {
			
				case "-user":
					user = args[i+1];
					break;
					
				case "-localPort":
					localPort = Integer.parseInt(args[i+1]);
					break;
					
				case "-remoteHost":
					remoteHost = args[i+1];
					break;
					
				case "-remotePort":
					localPort = Integer.parseInt(args[i+1]);
					break;
	
				default:
					break;
			}
			
		}
	}

	

}
