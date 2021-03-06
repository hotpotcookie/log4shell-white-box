package hpc.mvn.ldap.client.connection;
//----------
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 *
 * @author	Muhammad Nur Irsyad
 * @id		1807422020
 * @class	CCIT SEC 8 (TMJ)
 */
public class LoadProperties {
	public static Properties properties;
	private static final Logger logger = LogManager.getLogger(LoadProperties.class);	
	
	//----------
	public static Properties getConfig(boolean stat, String filename) { // pakai konsep matiin stat ke fstream, ldapcon, basedn
		String path = "src\\main\\resources\\";
		String os = System.getProperty("os.name");		
		if (os.contains("Windows")) {path += filename;}
		else {path = "src/main/resources/"+filename;}
		if (stat) {System.out.println("config\t: "+path);}
		//----------
		try{
			FileInputStream finStream = new FileInputStream( new File(path));
			if (stat) {System.out.println("fstream\t: "+finStream);}
			properties = new Properties();
			properties.load(finStream);
		} catch(IOException e) {System.out.println(">> LoadConfig/getProperties: "+e); logger.error(e);}
		//----------
		return properties;
	}
}
