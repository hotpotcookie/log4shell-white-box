package ldap.client.connection;
//----------
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
/**
 *
 * @author	Muhammad Nur Irsyad
 * @id		1807422020
 * @class	CCIT SEC 8 (TMJ)
 */
public class LoadProperties {
	public static Properties properties;
	
	//----------
	public static Properties getConfig(boolean stat, String filename) {
		String path = System.getProperty("user.dir");		
		String os = System.getProperty("os.name");		
		if (os.contains("Windows")) {path += "\\src\\ldap\\client\\environment\\"+filename;}
		else {path += "/src/ldap/client/environment/"+filename;}
		if (stat) {System.out.println("config\t: "+path);}
		//----------
		try{
			FileInputStream finStream = new FileInputStream( new File(path));
			if (stat) {System.out.println("fstream\t: "+finStream);}
			properties = new Properties();
			properties.load(finStream);
		} catch(IOException e) {System.out.println(">> LoadConfig/getProperties: "+e);}
		//----------
		return properties;
	}
}