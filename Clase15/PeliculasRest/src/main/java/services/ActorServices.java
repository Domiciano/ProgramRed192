package services;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

import db.MySQLConnection;
import model.Actor;

@Stateless
@Path("actor")
public class ActorServices {

	//JAX-RS
	
	@GET
	@Path("get/{id}")
	@Produces("application/json")
	public Actor getActor(@PathParam("id") String id) {
		MySQLConnection connection = new MySQLConnection();
		Actor actor = connection.getActorByID(  Integer.parseInt(id)  );
		connection.close();
		
		if(actor != null)
			return actor;
		else 
			return new Actor(-1, "Not found", "not found");
	}
	
	@GET
	@Path("get/all")
	@Produces("application/json")
	public ArrayList<Actor> getAllActors() {
		MySQLConnection connection = new MySQLConnection();
		ArrayList<Actor> actores = connection.getAllActors();
		connection.close();
		return actores;
	}
	
	
	@POST
	@Path("insert")
	@Consumes("application/json")
	public String addActor( Actor actor ) {
		MySQLConnection connection = new MySQLConnection();
		connection.insertActor(actor);
		connection.close();
		return actor.getNombre() + " Agregado!";		
	}
	
	@DELETE
	@Path("delete/{id}")
	@Produces("application/json")
	public String deleteActor(@PathParam("id") String id) {
		MySQLConnection connection = new MySQLConnection();
		connection.deleteActor(id);
		connection.close();
		return "Actor con id "+id + " ha sido eliminado!";
	}
	
}
