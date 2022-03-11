package ldap.client.controller;
import java.awt.Desktop;
import java.net.URL;
import javax.swing.*;
/**
 *
 * @author	Muhammad Nur Irsyad
 * @id		1807422020
 * @class	CCIT SEC 8 (TMJ)
 */
public class Util {
	private final Logger objLog = new Logger();
	//
	public String randomGen(int len) {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"0123456789";
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			int index
				= (int)(AlphaNumericString.length()
						* Math.random());
			sb.append(AlphaNumericString
						  .charAt(index));
		}
	   return sb.toString();		
	}
	
	public void requestPassword(JTextField inUsername, JList listLogging, JScrollPane listScrollPane) {
		// username harus cocok dgn di lookup LDAP
		if (!inUsername.getText().equals("username or uid") && inUsername.getText().length() > 0) {
			String ticket = this.randomGen(8);
			String subject = "Reset%20Password%20Request%20%7C%20PNJ%20OpenLDAP%20Client%20%7C%20"+inUsername.getText()+"%20%7C%20%23"+ticket;
			String body = "ticket:%20%23"+ticket+"%0Auid:%20"+inUsername.getText()+"%0Agroup:%20TMJ_CCIT_SEC_8%0A--%0APlease%20describe%20your%20issues%20here%20...";
			String to = "hotpotcookie@gmail.com";
			//
			String urlString = "https://mail.google.com/mail/?view=cm&fs=1&to="+to+"&su="+subject+"&body="+body;
			try {
				Desktop.getDesktop().browse(new URL(urlString).toURI());
				this.objLog.appendLog(inUsername.getText()+" send pass-request", listLogging, listScrollPane);				
			} catch (Exception e) {e.printStackTrace();}	
		}
	}		
	
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
	
	public void placeHolderPassword(JPasswordField inPassword, int bol) {
		String getPass = String.valueOf(inPassword.getPassword());		
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