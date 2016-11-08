package demo.hello;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloResource {
	
	public HelloResource() {
		System.out.println("New Resource (Hello)! " + this);
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getPlainMessage() {
		return "Herzlich Willkommen!";
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getHTMLMessage() {
		return "<html><body><h1>Herzlich Willkommen!</h1></body></html>";
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public String getXMLMessage() {
		return "<?xml version=\"1.0\"?><hello>Herzlich Willkommen!</hello>";
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getPlainMessageExtended() {
		return "Herzlich Willkommen! Und viele Grüße!";
	}

}
