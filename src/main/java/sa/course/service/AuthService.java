package sa.user.service;

import sa.user.model.User;
import sa.user.service.LdapService;
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

    public String login(User user) {

        String username = user.getUsername();
        String password = user.getPassword();

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
