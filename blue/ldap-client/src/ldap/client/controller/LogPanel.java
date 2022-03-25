package ldap.client.controller;
//----------
import ldap.client.connection.LoadProperties;
import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Properties;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author	Muhammad Nur Irsyad
 * @id		1807422020
 * @class	CCIT SEC 8 (TMJ)
 */
public class LogPanel {
	private static final Logger logger = LogManager.getLogger(LogPanel.class);
	//----------
	public void appendLog(String msg, JList listLogging, JScrollPane listScrollPane) {
		
		//----------
		DefaultListModel lst = new DefaultListModel();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yy");
		LocalDateTime now = LocalDateTime.now();
		//----------
		for (int x = 0; x < listLogging.getModel().getSize(); x++) {
			lst.addElement(listLogging.getModel().getElementAt(x));			
		}
		//----------
		if (msg.length() >= 33) {
			msg = msg.substring(0, Math.min(msg.length(), 30));
			msg += "...";
		}
		//----------		
		lst.addElement("["+dtf.format(now)+"] "+msg);				
		listLogging.setModel(lst);
		listLogging.ensureIndexIsVisible(listLogging.getModel().getSize()-1);
		listLogging.setSelectedIndex(listLogging.getModel().getSize()-1);
		//----------
		logger.info(msg);
		System.out.println("logged");
	}
}