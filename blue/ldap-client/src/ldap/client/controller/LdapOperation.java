package ldap.client.controller;
import javax.naming.ldap.InitialLdapContext;
import ldap.client.connection.GetContext;
/**
 *
 * @author	Muhammad Nur Irsyad
 * @id		1807422020
 * @class	CCIT SEC 8 (TMJ)
 */
public class LdapOperation {
	static InitialLdapContext context;
	//
	public InitialLdapContext verifiyContext() {
		context = GetContext.getContext();
		if (context != null) {System.out.println("ldapcon\t: "+context);}
		return context;
	}
}
