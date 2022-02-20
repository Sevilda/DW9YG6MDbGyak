package ldap;

import java.util.Enumeration;
import java.util.Iterator;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSearchResults;

public class LDAP_Main {

	public static void main(String[] args) {

		check_connection();
		
		query();

	}

	public static void check_connection() {

		int ldapPort = LDAPConnection.DEFAULT_PORT;
		int ldapVer = LDAPConnection.LDAP_V3;

		String ldapHost = "127.0.0.1";

		String loginName = "ou=csop_SH,ou=ev2020,ou=meinfo,dc=maxcrc,dc=com";
		String pw = "S121";

		LDAPConnection ldapconn = new LDAPConnection();

		boolean correct = true;

		try {
			ldapconn.connect(ldapHost, ldapPort);
			System.out.println("S1");

			ldapconn.bind(ldapVer, loginName, pw.getBytes());
			System.out.println("S2");

			ldapconn.disconnect();
		} catch (Exception e) {
			correct = false;
		}

		System.out.println("\n---\n");
	}

	public static void query() {

		int ldapPort = LDAPConnection.DEFAULT_PORT;
		int ldapVer = LDAPConnection.LDAP_V3;

		String ldapHost = "127.0.0.1";

		String loginName = "ou=csop_SH,ou=ev2020,ou=meinfo,dc=maxcrc,dc=com";
		String pw = "S121";

		int sScope = LDAPConnection.SCOPE_BASE;
		// using an already existing entry
		String sBase = "cn=SSSSSS,ou=csop_SH,ou=ev2020,ou=meinfo,dc=maxcrc,dc=com";
		String sFilter = "";

		LDAPConnection ldapconn = new LDAPConnection();

		try {
			ldapconn.connect(ldapHost, ldapPort);
			System.out.println("S1");

			ldapconn.bind(ldapVer, loginName, pw.getBytes());
			System.out.println("S2");

			LDAPSearchResults searchResults = ldapconn.search(sBase, sScope, sFilter, null, false);

			while (searchResults.hasMore()) {
				LDAPEntry entry = null;
				try {
					entry = searchResults.next();
					System.out.println("\n DN> " + entry.getDN());

					LDAPAttributeSet attSet = entry.getAttributeSet();
					Iterator atts = attSet.iterator();

					while (atts.hasNext()) {
						LDAPAttribute att = (LDAPAttribute) atts.next();
						//get name with SN
						if (att.getName().compareTo("sn") == 0) {
							Enumeration<String> allValues = att.getStringValues();
							String attValue = allValues.nextElement();
							System.out.println("  " + att.getName() + " :: " + attValue);
						}
					}
				} catch (LDAPException e) {
					e.printStackTrace();
				}
			}

			ldapconn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\n---\n");
	}

}
