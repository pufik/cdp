package com.epam.cdp.jndi.dao.context;

import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicLong;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import com.epam.cdp.jndi.exception.InitContextException;

public class ContextProvider {

	private static volatile ContextProvider instance = null;

	private DirContext context;

	private Hashtable<String, Object> envParams;
	
	private AtomicLong counter;

	private ContextProvider() {
		envParams = initContextParams();
		context = initContext(envParams);
		counter = new AtomicLong();
	}

	public static ContextProvider getInstance() {
		if (instance == null) {
			synchronized (ContextProvider.class) {
				if (instance == null) {
					instance = new ContextProvider();
				}
			}
		}

		return instance;
	}

	public DirContext getContext() {
		return context;
	}
	
	public long getConter(){
		return counter.incrementAndGet();
	}

	private DirContext initContext(Hashtable<String, Object> params) throws InitContextException {
		DirContext context = null;
		try {
			context = new InitialDirContext(envParams);
		} catch (NamingException e) {
			throw new InitContextException("Can't init context", e);
		}

		return context;
	}

	private Hashtable<String, Object> initContextParams() {
		// TODO: Change to properties

		Hashtable<String, Object> env = new Hashtable<String, Object>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost:10389/o=IuriiParfeniuk");
		env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
		env.put(Context.SECURITY_CREDENTIALS, "secret");

		return env;
	}
	
}
