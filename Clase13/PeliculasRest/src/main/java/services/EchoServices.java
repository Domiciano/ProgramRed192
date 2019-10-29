package services;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Stateless
@Path("echo")
public class EchoServices {
	
	@GET
	@Path("index")
	public String index() {
		return "echo";
	}
	
}
