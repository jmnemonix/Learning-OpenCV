package experimente.echoserver.rcommand;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

public class CommandServer {

	private static final int BUFFER_SIZE = 508;
	
	private final String NAME = "cserver v0.1";
	
	private int port = 1;
	
	private AdvancedCommand aCommand = new AdvancedCommand();
	private boolean lastCommand = false;
	
	public CommandServer() {
		this(50000);
	}

	public CommandServer(int port) {
		super();
		this.port = port;
	}
	
	public static void main(String[] args) {
		CommandServer cs = new CommandServer();
		
		cs.run();
	}

	public void run() {
				
		
		try ( DatagramSocket socket = new DatagramSocket(port) ) {
			
			DatagramPacket packetIn  = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
			DatagramPacket packetOut = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
			
			print("Server gestartet ...");
			
			while (true) {
				
				socket.receive(packetIn);
				print("Empfangen: " + packetIn.getLength() + " bytes");
				String received = new String(packetIn.getData(), 0, packetIn.getLength());
				

				print("received cmd: " + received);
				byte[] answer = parse(received);
				
				print("answer: " + new String(answer, 0, answer.length));
				
				packetOut.setData(answer);
				packetOut.setLength(answer.length);
				packetOut.setSocketAddress(packetIn.getSocketAddress());
				
				socket.send(packetOut);
				
				if (lastCommand) break;
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		print("Exit");
	}
	private byte[] parse(String s) {
		
		switch (s) {
		
			case "getDateTime":
				
				return getTime();
				
			case "getPort":
//				return ByteBuffer.allocate(4).putInt(port).array();
				byte[] val = new Integer(port).toString().getBytes();
				return val;
				
			case "exit":
				lastCommand = true;
				return "Tschuess!".getBytes();
	
			default:
				return aCommand.parse(s);
			
		}
	}
	private static byte[] getTime() {
		String time = (new Date()).toString();
		
		return time.getBytes();
	}
	

	
	private void print(String s) {
		System.out.println("("+NAME+") "+s);
	}
	

}
