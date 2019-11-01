package aplicativo;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("aplicativo")
public class Aplicativo {
	
	@POST
	@Path("hello")
	@Produces(MediaType.APPLICATION_JSON)
	public String Hello() {
		return "<h1>STEVE</h1>";
	}
}
