package hpc.mvn.ldap.client.controller;
//----------
import hpc.mvn.ldap.client.connection.GetContext;
//----------
import java.awt.Desktop;
import java.net.URL;
import javax.naming.ldap.InitialLdapContext;
import javax.swing.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
/**
 *
 * @author	Muhammad Nur Irsyad
 * @id		1807422020
 * @class	CCIT SEC 8 (TMJ)
 */
public class Util {
	public String mail;	
	private final LogPanel objLog = new LogPanel();
	private static final Logger logger = LogManager.getLogger(Util.class);
	//----------
	public String randomGen(int len) {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"0123456789";
		StringBuilder sb = new StringBuilder(len);
		//----------		
		for (int i = 0; i < len; i++) {
			int index = (int)(AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		//----------		
	   return sb.toString();		
	}

	//----------	
	public void requestPassword(JTextField inUsername, JList listLogging, JScrollPane listScrollPane, JLabel valInitials) {
		if (valInitials.getText().equals("initials") && !inUsername.getText().equals("")) { // cek kalau user field kosong dulu, baru cek found
			LdapOperation ldapop = new LdapOperation();
			InitialLdapContext context = ldapop.verifiyContext(false);
			boolean found = ldapop.searchUid(context,inUsername.getText().replace("*", ""));		
			//----------
			if (found) {
				JFrame jf = new JFrame(); jf.setAlwaysOnTop(true);
				String authuser = (String) JOptionPane.showInputDialog(jf,"Institutional Email","Request Password Reset",JOptionPane.INFORMATION_MESSAGE,null,null,"");
				authuser = authuser.trim().toLowerCase();
				//----------								
				ldapop.searchUid(context,inUsername.getText(),this);
				//----------				
				if (authuser.equals(mail)) {
					String ticket = this.randomGen(8);
					String subject = "Reset%20Password%20Request%20%7C%20PNJ%20OpenLDAP%20Client%20%7C%20"+inUsername.getText()+"%20%7C%20%23"+ticket;
					String body = "ticket:%20%23"+ticket+"%0Auid:%20"+inUsername.getText()+"%0A--%0APlease%20describe%20your%20issues%20here%20...";
					String to = "hotpotcookie@gmail.com";
					String urlString = "https://mail.google.com/mail/?view=cm&fs=1&to="+to+"&authuser="+authuser+"&su="+subject+"&body="+body;
					//----------
					try {
						Desktop.getDesktop().browse(new URL(urlString).toURI());
						this.objLog.appendLog(inUsername.getText()+" send new password request", listLogging, listScrollPane);				
					} catch (Exception e) {System.out.println(e); logger.error(e);}	
				} else {JOptionPane.showMessageDialog(jf, "Please use your institutional email that are\nregistered to the username or uid only", "Request Password Fail", 0 ,null);}
			} else {this.objLog.appendLog(inUsername.getText()+" failed to send password request", listLogging, listScrollPane);}			
			GetContext.closeContext(context);			
		}
	}		

	//----------	
	public void placeHolderUsername(JTextField inUsername, int bol) {
		switch (bol) {
			case 1:
				if (inUsername.getText().equals("username or uid")) {
					inUsername.setForeground(new java.awt.Color(103,171,159));
					inUsername.setText("");
				}	break;
			case 0:
				if (inUsername.getText().isEmpty()) {
					inUsername.setForeground(new java.awt.Color(153,153,153));
					inUsername.setText("username or uid");
				}	break;
			default:
				inUsername.setForeground(new java.awt.Color(153,153,153));
				inUsername.setText("username or uid");
				break;			
		}
	}
	
	//----------	
	public void placeHolderPassword(JPasswordField inPassword, int bol) {
		String getPass = String.valueOf(inPassword.getPassword());		
		//----------
		switch(bol) {
			case 1:
				if (getPass.equals("password")) {
					inPassword.setForeground(new java.awt.Color(103,171,159));
					inPassword.setText("");
				}				
				break;
			case 0:
				if (getPass.toLowerCase().trim().equals("")) {
					inPassword.setForeground(new java.awt.Color(153,153,153));
					inPassword.setText("password");
				}				
				break;
			default:
				inPassword.setForeground(new java.awt.Color(153,153,153));
				inPassword.setText("password");				
				break;
		}
	}
}