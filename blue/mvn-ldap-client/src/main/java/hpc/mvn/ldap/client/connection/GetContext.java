package hpc.mvn.ldap.client.connection;
//----------
import java.util.Hashtable;
import java.util.Properties;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 *
 * @author	Muhammad Nur Irsyad
 * @id		1807422020
 * @class	CCIT SEC 8 (TMJ)
 */
public class GetContext {
	@SuppressWarnings("UseOfObsoleteCollectionType")
	//----------
	public static InitialLdapContext context;
	public static Hashtable<String,String> env = new Hashtable<String,String>();
	private static final Logger logger = LogManager.getLogger(GetContext.class);
		
	//----------
	public static InitialLdapContext getContext(boolean stat) {
		Properties properties = LoadProperties.getConfig(stat,"config.properties");
		//----------
		env.put(javax.naming.Context.PROVIDER_URL, properties.getProperty("PROVIDER_URL"));
		env.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(javax.naming.Context.SECURITY_AUTHENTICATION, "simple");
		env.put(javax.naming.Context.SECURITY_PRINCIPAL, properties.getProperty("SECURITY_PRINCIPAL"));
		env.put(javax.naming.Context.SECURITY_CREDENTIALS, properties.getProperty("SECURITY_CREDENTIALS"));
		env.put("com.sun.jndi.ldap.connect.timeout", "15000");
		env.put("com.sun.jndi.ldap.read.timeout", "5000");		
		//----------
		try {
			context = new InitialLdapContext(env,null);
		} catch (NamingException e) {System.out.println(">> GetContext/getContext: "+e); logger.error(e);}
		//----------
		return context;
	}
	
	//----------
	public static boolean getContext(String principal, String credentials) {
		Properties properties = LoadProperties.getConfig(false,"config.properties");
		boolean passed = true;
		//----------
		env.put(javax.naming.Context.PROVIDER_URL, properties.getProperty("PROVIDER_URL"));
		env.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(javax.naming.Context.SECURITY_AUTHENTICATION, "simple");
		env.put(javax.naming.Context.SECURITY_PRINCIPAL, principal);
		env.put(javax.naming.Context.SECURITY_CREDENTIALS, credentials);		
		env.put("com.sun.jndi.ldap.connect.timeout", "15000");
		env.put("com.sun.jndi.ldap.read.timeout", "5000");		
		//----------
		try {
			context = new InitialLdapContext(env,null);
		} catch (NamingException e) {System.out.println(">> GetContext/getContext(Str,Str): "+e); passed = false; logger.error(e);}
		//----------
		return passed;
	}

	//----------
	public static void closeContext(InitialLdapContext context) {
		try {
			if (context != null) {context.close();}
		} catch (NamingException e) {System.out.println(">> cGetContext/loseContext: "+e); logger.error(e);}
	}
}
