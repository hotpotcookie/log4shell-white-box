package ldap.client.connection;
import java.util.Hashtable;
import java.util.Properties;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.NamingException;
/**
 *
 * @author	Muhammad Nur Irsyad
 * @id		1807422020
 * @class	CCIT SEC 8 (TMJ)
 */
public class GetContext {
	public static InitialLdapContext context;
	public static Hashtable<String,String> env = new Hashtable<String,String>();
	public static String PROVIDER_URL;
	public static String SECURITY_PRINCIPAL;
	public static String SECURITY_CREDENTIALS;
	//
	static {
		Properties properties = LoadConfig.getProperties();
		//
		env.put(javax.naming.Context.PROVIDER_URL, properties.getProperty("PROVIDER_URL"));
		env.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(javax.naming.Context.SECURITY_AUTHENTICATION, "simple");
		env.put(javax.naming.Context.SECURITY_PRINCIPAL, properties.getProperty("SECURITY_PRINCIPAL"));
		env.put(javax.naming.Context.SECURITY_CREDENTIALS, properties.getProperty("SECURITY_CREDENTIALS"));
		env.put("com.sun.jndi.ldap.connect.timeout", "15000");
		env.put("com.sun.jndi.ldap.read.timeout", "5000");		
	}
	//
	public static InitialLdapContext getContext() {
		try {
			context = new InitialLdapContext(env,null);
		} catch (NamingException e) {System.out.println(">> GetContext/getContext: "+e);}
		//
		return context;
	}
	//
	public static void closeContext(InitialLdapContext context) {
		try {
			if (context != null) {context.close();}
		} catch (NamingException e) {System.out.println(">> cGetContext/loseContext: "+e);}
	}
}
