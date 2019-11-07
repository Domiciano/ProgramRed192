package config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("web")
public class ApplicationConfig extends Application{

	@Override
	public Set<Class<?>> getClasses() {
		// TODO Auto-generated method stub
		Set<Class<?>> recursos = new HashSet<Class<?>>();
		recursos.add(services.FirstService.class);
		recursos.add(services.DateService.class);
		recursos.add(services.MathService.class);
		return recursos;
	}

	
	
}
