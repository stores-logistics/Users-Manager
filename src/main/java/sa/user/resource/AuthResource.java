package sa.user.resource;

import sa.user.model.User;
import sa.user.service.AuthService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.persistence.Entity;
import java.net.URI;
import java.util.List;

@Path("/auth")
public class AuthResource {

    ResponseBuilder response;

    @Context
    UriInfo uriInfo;

    @EJB
    AuthService authService;

    @POST
    public User login(User user) {
        User reponse = authService.login(user);
	    return reponse;
        //response = Response.status(Response.Status.OK);
        //response.entity(reponse);
        //return response.build();
    }

}
