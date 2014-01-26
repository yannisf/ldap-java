package fraglab.ldap.anonymous;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

public class Main {

    public static void main(String[] args) throws NamingException {
        Hashtable<String, Object> env = new Hashtable<String, Object>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:389/dc=fraglab,dc=net");
        env.put(Context.SECURITY_AUTHENTICATION, "none");
        DirContext ctx = new InitialDirContext(env);
        ctx.close();
    }

}
