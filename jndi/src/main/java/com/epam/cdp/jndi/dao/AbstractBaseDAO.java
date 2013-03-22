package com.epam.cdp.jndi.dao;

import javax.naming.Context;
import javax.naming.directory.DirContext;

import com.epam.cdp.jndi.dao.context.ContextProvider;

public abstract class AbstractBaseDAO<T, I> implements BaseDAO<T, I> {

	protected Context getContext() {
		return ContextProvider.getInstance().getContext();
	}

	protected DirContext getDirContext() {
		return ContextProvider.getInstance().getContext();
	}

	protected long getCounter() {
		return ContextProvider.getInstance().getConter();
	}

}
