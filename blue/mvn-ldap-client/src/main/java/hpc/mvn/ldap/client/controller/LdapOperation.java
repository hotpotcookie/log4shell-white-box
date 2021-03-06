package hpc.mvn.ldap.client.controller;
//----------
import hpc.mvn.ldap.client.connection.*;
//----------
import javax.naming.NamingException;
import javax.naming.NamingEnumeration;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.Properties;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
/**
 *
 * @author	Muhammad Nur Irsyad
 * @id		1807422020
 * @class	CCIT SEC 8 (TMJ)
 */
public class LdapOperation {	
	static InitialLdapContext context;
	private static final Logger logger = LogManager.getLogger(LdapOperation.class);
	
	//----------
	public InitialLdapContext verifiyContext(boolean stat) {
		context = GetContext.getContext(stat);
		if (context != null) {if (stat) {System.out.println("ldapcon\t: "+context);}}
		return context;
	}

	//----------	
	public void searchUid(InitialLdapContext context, String uid, Auth obj) {
		Properties properties = LoadProperties.getConfig(false,"config.properties");
		String base = properties.getProperty("BASE_DN");
		String filter = "(uid="+uid+")";
		SearchControls controls = new SearchControls();
		controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		//----------		
		try {
			NamingEnumeration<SearchResult> namingEnu = context.search(base, filter, controls);
			while(namingEnu.hasMoreElements()) {
				SearchResult res = namingEnu.nextElement();
				Attributes allAtr = res.getAttributes();
				Attribute dNAtr = allAtr.get("displayName"); obj.displayName = dNAtr.toString().replace("displayName: ", "");
				Attribute initialsAtr = allAtr.get("initials"); obj.initials = initialsAtr.toString().replace("initials: ", "");
				Attribute uidAtr = allAtr.get("uid"); obj.uid = uidAtr.toString().replace("uid: ", "");
				Attribute mailAtr = allAtr.get("mail"); obj.mail = mailAtr.toString().replace("mail: ", "");
				Attribute gecosAtr = allAtr.get("gecos"); obj.gecos = gecosAtr.toString().replace("gecos: ", "");
			}
			GetContext.closeContext(context);
		} catch(NamingException e) {System.out.println(">> LdapOperation/searchUid: "+e); logger.error(e);}
	}

	//----------	
	public boolean searchUid(InitialLdapContext context, String uid) {
		Properties properties = LoadProperties.getConfig(false,"config.properties");
		String base = properties.getProperty("BASE_DN");
		String filter = "(uid="+uid+")";
		SearchControls controls = new SearchControls();
		controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		boolean found = false;
		//----------		
		try {
			NamingEnumeration<SearchResult> namingEnu = context.search(base, filter, controls);
			while(namingEnu.hasMoreElements()) {
				SearchResult res = namingEnu.nextElement();
				Attributes allAtr = res.getAttributes();
				Attribute uidAtr = allAtr.get("uid");
				if (uidAtr != null) {found = true;}
			}
			GetContext.closeContext(context);			
		} catch(NamingException e) {System.out.println(">> LdapOperation/searchUid: "+e); logger.error(e);}
		//----------		
		return found;
	}	

	//----------	
	public void searchUid(InitialLdapContext context, String uid, Util obj) {
		Properties properties = LoadProperties.getConfig(false,"config.properties");
		String base = properties.getProperty("BASE_DN");
		String filter = "(uid="+uid+")";
		SearchControls controls = new SearchControls();
		controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		//----------		
		try {
			NamingEnumeration<SearchResult> namingEnu = context.search(base, filter, controls);
			while(namingEnu.hasMoreElements()) {
				SearchResult res = namingEnu.nextElement();
				Attributes allAtr = res.getAttributes();
				Attribute mailAtr = allAtr.get("mail"); obj.mail = mailAtr.toString().replace("mail: ", "");
			}
			GetContext.closeContext(context);
		} catch(NamingException e) {System.out.println(">> LdapOperation/searchUid: "+e); logger.error(e);}
	}	
}
