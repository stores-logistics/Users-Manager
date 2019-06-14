package sa.course.service;

import sa.course.model.Course;
import sa.course.service.LdapService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class AuthService {

    @PersistenceContext
    EntityManager entityManager;

    String response = "";
    LdapService ldapService = new LdapService();

    public String login(Course course) {

        String username = course.getUsername();
        String password = course.getPassword();

        if (ldapService.connect()) {
            if (ldapService.validateUser(username, password)) {
                response = ldapService.getData(username);
            } else {
                response = "false";
            }
        } else {
            response = "false";
        }
        return response;
    }
}
