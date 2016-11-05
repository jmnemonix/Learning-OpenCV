import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SetupData {

	private String myName = null;
	private int myPort;

	private String remoteHost = null;
	private int remotePort;

	public SetupData() {
		super();
	}
	public SetupData(String myName, int myPort, String remoteHost, int remotePort) {
		super();
		this.myName = myName;
		this.myPort = myPort;
		this.remoteHost = remoteHost;
		this.remotePort = remotePort;
	}

	public String getMyName() {
		return myName;
	}
	public void setMyName(String myName) {
		this.myName = myName;
	}
	public int getMyPort() {
		return myPort;
	}
	public void setMyPort(int myPort) {
		this.myPort = myPort;
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
