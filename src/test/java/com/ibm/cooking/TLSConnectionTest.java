package com.ibm.cooking;

import org.junit.Test;

import javax.naming.Context;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.StartTlsRequest;
import javax.naming.ldap.StartTlsResponse;
import java.util.Hashtable;

/**
 * Created by zhaodonglu on 2017/3/16.
 */
public class TLSConnectionTest {
  @Test
  public void testTcp() throws Exception {
    // Set up environment for creating initial context
    Hashtable env = new Hashtable(11);
    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

    // Must use the name of the server that is found in its certificate
    env.put(Context.PROVIDER_URL, "ldap://192.168.142.129:389");

    // Create initial context
    LdapContext ctx = new InitialLdapContext(env, null);

    // Perform read
    System.out.println(ctx.getAttributes("dc=com"));

    // Close the context when we're done
    ctx.close();

  }

  @Test
  public void testSSL() throws Exception {
    // Set up environment for creating initial context
    Hashtable env = new Hashtable(11);
    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

    // Must use the name of the server that is found in its certificate
    env.put(Context.PROVIDER_URL, "ldaps://192.168.142.129:389");

    // Create initial context
    LdapContext ctx = new InitialLdapContext(env, null);

    // Perform read
    System.out.println(ctx.getAttributes("dc=com"));

    // Close the context when we're done
    ctx.close();

  }

  @Test
  public void testTLSNegotiateByDefaultJVMTrust() throws Exception {
    // Set up environment for creating initial context
    Hashtable env = new Hashtable(11);
    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

    // Must use the name of the server that is found in its certificate
    env.put(Context.PROVIDER_URL, "ldap://LDAP_Server:389");

    // Create initial context
    LdapContext ctx = new InitialLdapContext(env, null);

    // Start TLS
    StartTlsResponse tls = (StartTlsResponse) ctx.extendedOperation(new StartTlsRequest());

    // ... do something useful with ctx that requires secure connection

    tls.negotiate();

    System.out.println(ctx.getAttributes("dc=COM"));

    // Stop TLS
    tls.close();

    // ... do something useful with ctx that doesn't require security

    // Perform read
    System.out.println(ctx.getAttributes("dc=com"));

    // Close the context when we're done
    ctx.close();

  }

}
