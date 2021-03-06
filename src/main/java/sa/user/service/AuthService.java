package sa.user.service;

import sa.user.model.User;
import sa.user.service.LdapService;
import sa.user.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ejb.Stateless;
import javax.ejb.EJB;
import java.util.List;

@Stateless
public class AuthService {

    @PersistenceContext
    EntityManager entityManager;

    User response = new User();
    LdapService ldapService = new LdapService();

    @EJB
    UserService userService;

    public User login(User user) {

        String username = user.getUsername();
        String password = user.getPassword();

        if (ldapService.connect()) {
            if (ldapService.validateUser(username, password)) {
                //response = ldapService.getData(username);
                response = userService.getUserByUsername(username);
            } else {
                response = null;
            }
        } else {
            response = null;
        }
        return response;
    }
}
