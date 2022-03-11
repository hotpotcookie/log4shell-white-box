package ldap.client.controller;
import javax.swing.*;
/**
 *
 * @author	Muhammad Nur Irsyad
 * @id		1807422020
 * @class	CCIT SEC 8 (TMJ)
 */
public class Auth {
	private final Logger objLog = new Logger();
	private final Util objUtil = new Util();
	public void performLogin(JTextField inUsername, JPasswordField inPassword, JList listLogging, JScrollPane listScrollPane, JLabel valDisplayName, JLabel valUID, JLabel valMail, JLabel valGecos, JLabel valInitials) {
		String[][] arr = {{"1807422020","okok"},{"1807422024","ojan"}};
		//
		String getPass = String.valueOf(inPassword.getPassword());
		if ((!inUsername.getText().equalsIgnoreCase("username or uid")) && (!getPass.equalsIgnoreCase("password"))) {
			for (String[] arr1 : arr) {
				if (inUsername.getText().equalsIgnoreCase(arr1[0])) {
					if (getPass.equals(arr1[1])) {
						this.objLog.appendLog(inUsername.getText()+" logged in!", listLogging, listScrollPane);
						//
						valDisplayName.setText("Muhammad Nur Irsyad"); valDisplayName.setForeground(new java.awt.Color(103,171,159));
						valUID.setText("1807422020"); valUID.setForeground(new java.awt.Color(103,171,159));
						valMail.setText("muhammad.nurirsyad.tik18@mhsw.pnj.ac.id"); valMail.setForeground(new java.awt.Color(103,171,159));
						valGecos.setText("TMJ_CCIT_SEC_8"); valGecos.setForeground(new java.awt.Color(103,171,159));
						valInitials.setText(("IRSYAD").toUpperCase()); valInitials.setForeground(new java.awt.Color(103,171,159));						
					}
					break;
				}
			}
		}
	}
	public void performLogout(JTextField inUsername, JPasswordField inPassword, JList listLogging, JScrollPane listScrollPane, JLabel valDisplayName, JLabel valUID, JLabel valMail, JLabel valGecos, JLabel valInitials) {		
		if (!valUID.getText().equals("uid")) {
			this.objUtil.placeHolderUsername(inUsername, 3);
			this.objUtil.placeHolderPassword(inPassword, 3);
			this.objLog.appendLog(valUID.getText()+" logged out!", listLogging, listScrollPane);		
			//
			valDisplayName.setText("displayName"); valDisplayName.setForeground(new java.awt.Color(153,153,153));
			valUID.setText("uid"); valUID.setForeground(new java.awt.Color(153,153,153));
			valMail.setText("mail"); valMail.setForeground(new java.awt.Color(153,153,153));
			valGecos.setText("gecos"); valGecos.setForeground(new java.awt.Color(153,153,153));
			valInitials.setText("initials"); valInitials.setForeground(new java.awt.Color(153,153,153));
		}
	}
}