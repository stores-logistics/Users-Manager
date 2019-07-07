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

        String ldapHost = "34.68.2.153";
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
        Boolean admin = validate(username, password, "Administrator");
        Boolean manager = validate(username, password, "Manager");
        Boolean passanger = validate(username, password, "Passanger");
        return (admin || manager || passanger);
    }

    public Boolean createUser(String username, String password, String role){
        try{
            if(role == 'Admin')
                role = "Administrator"
            String distinguishedName = "cn=" + username + ",ou=" + role + ",dc=arqsoft,dc=unal,dc=edu,dc=co";
            Attributes newAttributes = new BasicAttributes(true);
            Attribute oc = new BasicAttribute("objectclass");
            oc.add("top");
            oc.add("inetOrgPerson");
            oc.add("posixAccount");
            newAttributes.put(oc);
            newAttributes.put(new BasicAttribute("firstName", username));
            newAttributes.put(new BasicAttribute("lastName", username));
            newAttributes.put(new BasicAttribute("cn", username));
            newAttributes.put(new BasicAttribute("sn", username));
            newAttributes.put(new BasicAttribute("givenName", username));
            newAttributes.put(new BasicAttribute("displayName", username));
            newAttributes.put(new BasicAttribute("userName", username));
            newAttributes.put(new BasicAttribute("gidNumber", 500));
            newAttributes.put(new BasicAttribute("userPassword", password));
            newAttributes.put(new BasicAttribute("homeDirectory", "/home/users/" + username));
            System.out.println("Creating: " + username);
            ldapContext.createSubcontext(distinguishedName, newAttributes);
        }catch (Exception e){
            System.out.println("create error: " + e);
            e.printStackTrace();
        }
    }

    public Boolean validate(String username, String password, String role){
        String dn = "cn=" + username + ",ou=" + role + ",dc=arqsoft,dc=unal,dc=edu,dc=co";
        try {
            lc.bind(dn, password);
            return true;
        } catch (LDAPException ex) {
            return false;
        }
    }
    
    public String getData(String username){
        String dn = "cn=" + username + ",ou=store,dc=arqsoft,dc=unal,dc=edu,dc=co";
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
