package services;

import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Stateless
@Path("math")
public class MathService {
	
	
	@GET
	@Path("echo/{id}")
	public String echo(@PathParam("id") String id , Alfa alfa) {
		return id+alfa.nombre;
	}
	
	@GET
	@Path("suma")
	public String suma(@QueryParam("A") String A, @QueryParam("B") String B) {
		int a = Integer.parseInt(A);
		int b = Integer.parseInt(B);
		int suma = a+b;
		
		
		
		return ""+suma;
	}
	//Wildfly
	
	@POST
	@Path("mult")
	public String mult(@FormParam("A") String A, @FormParam("B") String B) {
		int a = Integer.parseInt(A);
		int b = Integer.parseInt(B);
		int mult = a*b;
		return ""+mult;
	}
	
}
