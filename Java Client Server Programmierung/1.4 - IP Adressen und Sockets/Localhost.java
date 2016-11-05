import java.net.InetAddress;
import java.net.UnknownHostException;

public class Localhost {

	public static void main(String[] args) throws UnknownHostException {

		InetAddress addresse = InetAddress.getLocalHost();
		System.out.printf("%s/%s%n", addresse.getHostName(), addresse.getHostAddress());
	}

}
