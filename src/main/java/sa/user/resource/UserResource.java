package sa.user.resource;

import sa.user.model.User;
import sa.user.service.UserService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.persistence.Entity;
import java.net.URI;
import java.util.List;

@Path("/users")
public class UserResource {

    ResponseBuilder response;

    @Context
    UriInfo uriInfo;

    @EJB
    UserService userService;

    @GET
    public List<User> getAllUsers(@QueryParam("first") int first, @QueryParam("maxResult") int maxResult) {
        return userService.getAllUsers(first, maxResult);
    }

    @GET
    @Path("{code}")
    public Response getUserByCode(@PathParam("code") long code) {
        User user = userService.getUserByCode(code);
        response = Response.status(Response.Status.OK);
        response.entity(user);
        return response.build();
    }

    @POST
    public Response createUser(User user) {
        User createdUser = userService.createUser(user);
        response = Response.status(Response.Status.CREATED);
        response.entity(createdUser);
        return response.build();
    }

    @PUT
    @Path("{code}")
    public Response updateUser(@PathParam("code") long code, User user) {
        User updatedUser = userService.updateUser(code, user);
        response = Response.status(Response.Status.OK);
        response.entity(updatedUser);
        return response.build();
    }

    @DELETE
    @Path("{code}")
    public Response deleteUser(@PathParam("code") long code) {
        long deletedUserCode = userService.deleteUser(code);
        response = Response.status(Response.Status.OK);
        response.entity(deletedUserCode);
        return response.build();
    }

}
