package sa.course.resource;

import sa.course.model.Course;
import sa.course.service.CourseService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/courses")
public class CourseResource {

    @Context
    UriInfo uriInfo;

    @EJB
    CourseService courseService;

    @GET
    public List<Course> getAllCourses(@QueryParam("first") int first, @QueryParam("maxResult") int maxResult) {
        return courseService.getAllCourses(first, maxResult);
    }

    @GET
    @Path("{code}")
    public Course getCourseByCode(@PathParam("code") long code) {
        return courseService.getCourseByCode(code);
    }

    @POST
    public Response createCourse(Course course) {
        courseService.createCourse(course);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{code}")
    public Response updateCourse(@PathParam("code") long code, Course course) {
        courseService.updateCourse(code, course);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("{code}")
    public Response deleteCourse(@PathParam("code") long code) {
        courseService.deleteCourse(code);
        return Response.status(Response.Status.OK).build();
    }

}
