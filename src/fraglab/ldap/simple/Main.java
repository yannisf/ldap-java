package fraglab.ldap.simple;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.Hashtable;

public class Main {

    public static void main(String[] args) throws NamingException {
        Hashtable<String, Object> env = new Hashtable<String, Object>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:389/dc=fraglab,dc=net");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "cn=admin,dc=fraglab,dc=net");
        env.put(Context.SECURITY_CREDENTIALS, "yannis");
        DirContext ctx = new InitialDirContext(env);
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        searchControls.setCountLimit(10);
        NamingEnumeration<SearchResult> namingEnumeration =
                ctx.search("", "(uid=*)", new Object[]{}, searchControls);
        while (namingEnumeration.hasMore()) {
            SearchResult sr = namingEnumeration.next();
            System.out.println("DN: " + sr.getName());
            System.out.println(sr.getAttributes().get("uid"));
            System.out.println("Password:" + new String((byte[]) sr.getAttributes().get("userPassword").get()));
        }
        ctx.close();
    }

}
