package demo.hello;

import java.net.URI;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;

/**
 * 
 * @author jano
 *
 *  Test the server with;
 *  
 *  curl -i -H "Accept: text/plain" http://localhost:8080/rest/hallo
 *  curl -i -H "Accept: text/html" http://localhost:8080/rest/hello
 *  curl -i -H "Accept: text/xml" http://localhost:8080/rest/hello
 *  curl -i -H "Accept: text/plain" http://localhost:8080/rest/hello/extended
 *
 */

public class Server {

	private static final String BASE_URL = "http://localhost:8080/rest";
	
	public static void main(String[] args) throws Exception {
		
		URI endpoint = new URI(BASE_URL);
		
		ResourceConfig rc = ResourceConfig.forApplicationClass(MyApplication.class);
		JdkHttpServerFactory.createHttpServer(endpoint, rc);
		
		System.out.println("Server gestartet: " + BASE_URL);
	}

}
