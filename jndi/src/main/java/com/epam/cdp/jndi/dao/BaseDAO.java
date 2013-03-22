package com.epam.cdp.jndi.dao;

import java.util.Collection;

public interface BaseDAO<T, I> {

	void create(T entity);

	T getById(I id);

	void update(T entity);

	void remove(T entity);

	Collection<T> getAll();
}
