package ldap.client.controller;
import javax.naming.NamingException;
import javax.naming.NamingEnumeration;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.Properties;
import ldap.client.connection.GetContext;
import ldap.client.connection.LoadConfig;
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
	public void searchUid(InitialLdapContext context, String uid, Auth obj) {
		Properties properties = LoadConfig.getProperties(false);
		//
		String base = properties.getProperty("BASE_DN"); System.out.println("basedn\t: "+base);
		String filter = "(uid="+uid+")";
		SearchControls controls = new SearchControls();
		//
		controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		try {
			NamingEnumeration<SearchResult> namingEnu = context.search(base, filter, controls);
			while(namingEnu.hasMoreElements()) {
				SearchResult res = namingEnu.nextElement();
				Attributes allAtr = res.getAttributes();
				//
				Attribute dNAtr = allAtr.get("displayName"); obj.displayName = dNAtr.toString().replace("displayName: ", "");
				Attribute initialsAtr = allAtr.get("initials"); obj.initials = initialsAtr.toString().replace("initials: ", "");
				Attribute uidAtr = allAtr.get("uid"); obj.uid = uidAtr.toString().replace("uid: ", "");
				Attribute mailAtr = allAtr.get("mail"); obj.mail = mailAtr.toString().replace("mail: ", "");
				Attribute gecosAtr = allAtr.get("gecos"); obj.gecos = gecosAtr.toString().replace("gecos: ", "");
				//
				System.out.println("data\t: "+dNAtr.toString().replaceAll("displayName: ", "")+"\n\t: "+initialsAtr+"\n\t: "+uidAtr+"\n\t: "+mailAtr+"\n\t: "+gecosAtr);
			}
		} catch(NamingException e) {System.out.println(">> LdapOperation/searchUid: "+e);}
		//		
	}
}
