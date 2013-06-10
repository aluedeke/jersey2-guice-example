package net.ludeke.rest.jersey;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.ludeke.rest.guice.Service;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

	private Service service;

	@Inject
	public MyResource(Service service) {
		this.service = service;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return service.get() + " Got it!";
	}
}