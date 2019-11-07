package services;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Stateless
@Path("date")
public class DateService {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
	
	@POST
	@Path("echo")
	public String echo() {
		return "echo";
	}
	
	@GET
	@Path("complete")
	public String completeDate() {
		return sdf.format(Calendar.getInstance().getTime());
	}
	
	@GET
	@Path("onlydate")
	public String onlyDate() {
		return sdfDate.format(Calendar.getInstance().getTime());
	}

	@GET
	@Path("onlytime")
	public String onlyTime() {
		return sdfTime.format(Calendar.getInstance().getTime());
	}
	
}
