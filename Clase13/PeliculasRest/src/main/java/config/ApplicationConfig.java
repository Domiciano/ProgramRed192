package config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import services.ActorServices;
import services.EchoServices;

@ApplicationPath("web")
public class ApplicationConfig extends Application{

	@Override
	public Set<Class<?>> getClasses() {
		
		Set<Class<?>> recursos = new HashSet<Class<?>>();
		recursos.add(EchoServices.class);
		recursos.add(ActorServices.class);
		return recursos;
	}

	
	
}
