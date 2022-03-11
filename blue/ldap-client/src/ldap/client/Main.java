package ldap.client;
import javax.naming.ldap.InitialLdapContext;
import ldap.client.view.Login;
import ldap.client.controller.LdapOperation;
/**
 *
 * @author	Muhammad Nur Irsyad
 * @id		1807422020
 * @class	CCIT SEC 8 (TMJ)
 */
public class Main {
	public static void main(String[] args) {
		System.out.println("initialization ("+System.getProperty("os.name")+")...");
		LdapOperation ldapop = new LdapOperation(); 
		InitialLdapContext context = ldapop.verifiyContext();
		//
		if (context != null) {Login home = new Login(); home.setVisible(true);}
	}	
}
