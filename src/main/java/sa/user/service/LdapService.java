package sa.user.service;

import com.novell.ldap.*;
import java.io.UnsupportedEncodingException;
import javax.faces.context.FacesContext;

public class LdapService {

    private LDAPConnection lc = new LDAPConnection();


    public Boolean login(String user, String password){
        if (connect()) {
            if (validateUser(user, password)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public Boolean connect() {

        String ldapHost = "35.193.251.100";
        String dn = "cn=admin,dc=arqsoft,dc=unal,dc=edu,dc=co";
        String password = "admin";

        int ldapPort =  LDAPConnection.DEFAULT_PORT;
        int ldapVersion = LDAPConnection.LDAP_V3;

        try {
            lc.connect(ldapHost, ldapPort);
            System.out.println("Connecting to LDAP Server...");
            lc.bind(ldapVersion, dn, password.getBytes("UTF8"));
            System.out.println("Authenticated in LDAP Server...");
            return true;
        } catch (LDAPException | UnsupportedEncodingException ex) {
            System.out.println("ERROR when connecting to LDAP Server...");
            return false;
        }
    }

    public Boolean validateUser(String username, String password){

        String dn = "cn=" + username + ",ou=academy,dc=arqsoft,dc=unal,dc=edu,dc=co";
        try {
            lc.bind(dn, password);
            return true;
        } catch (LDAPException ex) {
            return false;
        }
    }
    
    public String getData(String username){
        String dn = "cn=" + username + ",ou=academy,dc=arqsoft,dc=unal,dc=edu,dc=co";
        LDAPEntry foundEntry = null;
        LDAPAttribute uid = null;
        String getAttrs[] = { "uid"};
        String values[] = {};
        String a  = "";
        try{
            foundEntry = lc.read(dn, getAttrs);
            uid = foundEntry.getAttribute("uid");
            values = uid.getStringValueArray();
            a = values[0];
            return a;
        } catch (LDAPException ex){
            return "LDAPException found";
        }
    }
}
