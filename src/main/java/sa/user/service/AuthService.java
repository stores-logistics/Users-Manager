package sa.user.service;

import sa.user.model.User;
import sa.user.service.LdapService;
import sa.user.service.UserService;

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
    UserService userService = new UserService();

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
