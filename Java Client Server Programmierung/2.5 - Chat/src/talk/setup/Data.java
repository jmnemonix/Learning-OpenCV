package talk.setup;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Data {

	private String userName = null;
	private int localPort;

	private String remoteHost = null;
	private int remotePort;

	public Data() {
		super();
	}
	public Data(String userName, int localPort, String remoteHost, int remotePort) {
		super();
		this.userName = userName;
		this.localPort = localPort;
		this.remoteHost = remoteHost;
		this.remotePort = remotePort;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getLocalPort() {
		return localPort;
	}
	public void setLocalPort(int localPort) {
		this.localPort = localPort;
	}
	public String getRemoteHost() {
		return remoteHost;
	}
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}
	public int getRemotePort() {
		return remotePort;
	}
	public void setRemotePort(int remotePort) {
		this.remotePort = remotePort;
	}
}
