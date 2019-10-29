package services;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

import model.Actor;

@Stateless
@Path("actor")
public class ActorServices {

	//JAX-RS
	
	@GET
	@Path("get/{id}")
	@Produces("application/json")
	public Actor getActor(@PathParam("id") String id) {
		Actor actor = new Actor(Integer.parseInt(id), "Jack", "Nicholson");
		return actor;		
	}
	
	
	@POST
	@Path("insert")
	@Consumes("application/json")
	public String addActor( Actor actor ) {
		return "SERVER VA A AGREGAR: "+actor.getNombre();
	}
	
	@DELETE
	@Path("delete/{id}")
	@Produces("application/json")
	public String deleteActor(@PathParam("id") String id) {
		return "SERVER Eliminará : "+id;
	}
	
}
