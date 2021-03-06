package hpc.mvn.ldap.client;
//----------
import hpc.mvn.ldap.client.view.Login;
import hpc.mvn.ldap.client.connection.GetContext;
import hpc.mvn.ldap.client.controller.LdapOperation;
//----------
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import javax.naming.ldap.InitialLdapContext;
/**
 *
 * @author	Muhammad Nur Irsyad
 * @id		1807422020
 * @class	CCIT SEC 8 (TMJ)
 */
public class Main {
	private static final Logger logger = LogManager.getLogger(Main.class);
	//----------
	public static void main(String[] args) {
		System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "true");
		System.setProperty("com.sun.jndi.cosnaming.object.trustURLCodebase", "true");   		
		//----------		
		System.out.println("initialization ("+System.getProperty("os.name")+") in JDK "+System.getProperty("java.version")+" ...");	
		logger.debug("launching ldap-client ...");
		//----------
		LdapOperation ldapop = new LdapOperation(); 
		InitialLdapContext context = ldapop.verifiyContext(true);
		//----------
		if (context != null) {
			GetContext.closeContext(context); 
			Login home = new Login(); 
			home.setVisible(true);
			System.out.println("launching ldap-client ("+home.isVisible()+") ...");			
		}
		//----------		
//		logger.debug("This is a Debug Message!");
//		logger.info("This is an Info Message!");
//		logger.error("And here comes the Error Message!", new RuntimeException("RunRunRun"));
	}
}