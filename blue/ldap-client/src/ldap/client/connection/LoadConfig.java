package ldap.client.connection;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
// import log4j
/**
 *
 * @author	Muhammad Nur Irsyad
 * @id		1807422020
 * @class	CCIT SEC 8 (TMJ)
 */
public class LoadConfig {
	public static Properties properties;
	// add logger var (ldap.client.connection.LoadConfig.class);
	//
	public static Properties getProperties() {
		String path = System.getProperty("user.dir");		
		String os = System.getProperty("os.name");		
		if (os.contains("Windows")) {path += "\\src\\ldap\\client\\environment\\config.properties";}
		else {path += "/src/ldap/client/environment/config.properties";}
		System.out.println("config\t: "+path);
		//
		try{
			FileInputStream finStream = new FileInputStream( new File(path));
			System.out.println("fstream\t: "+finStream);			
			properties = new Properties();
			properties.load(finStream);
		} catch(IOException e) {System.out.println(">> LoadConfig/getProperties: "+e);}
		//
		return properties;
	}
}