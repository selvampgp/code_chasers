package com.codechasers.license.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.core.GenericTypeResolver;

public abstract class BaseAbstractDao<E> {

    private final SessionFactory sessionFactory;
    private final Class<?> entityClass;

    @Inject
    public BaseAbstractDao(SessionFactory factory) 
	{
        this.sessionFactory = factory;
        this.entityClass = GenericTypeResolver.resolveTypeArgument(getClass(), BaseAbstractDao.class);
    }

    protected E get(Serializable id) {
        return (E) currentSession().get(entityClass, checkNotNull(id));
    }

    private <P>P checkNotNull(P entity) {
		if (entity == null) throw new RuntimeException("Expected not null");
		return entity;
	}
    public List<E> create(List<E> entityList) {
		int DEFAULT_BATCH_SIZE = 100;
		Session currentSession = currentSession();
		for ( int i = 0; i < entityList.size(); i++ ) {
			currentSession.saveOrUpdate(checkNotNull(entityList.get(i)));
		    if ( i % DEFAULT_BATCH_SIZE == 0 && i > 0) { 
		    	currentSession.flush();
		    	currentSession.clear();
		    }
		}
		return entityList;
	}
	public Set<E> create(Set<E> entitySet) {
		int DEFAULT_BATCH_SIZE = 100;
		Session currentSession = currentSession();
		int i=0;
		for(E e:entitySet){
			currentSession.saveOrUpdate(checkNotNull(e));
		    if ( i % DEFAULT_BATCH_SIZE == 0 && i > 0) { 
		    	currentSession.flush();
		    	currentSession.clear();
		    }
			i++;
		}
		return entitySet;
	}
	
	public List<E> mergeList(List<E> entityList) {
		int DEFAULT_BATCH_SIZE = 100;
		Session currentSession = currentSession();
		for ( int i = 0; i < entityList.size(); i++ ) {
			currentSession.merge(entityList.get(i));
		    if ( i % DEFAULT_BATCH_SIZE == 0 && i > 0) { 
		    	currentSession.flush();
		    	currentSession.clear();
		    }
		}
		return entityList;
	}
	public E findById(Long id)
	{
		return get(id);
	}

    
  
	
	public E findById(Integer id)
	{
		return get(id);
	}
	
    public E persist(E entity)
    {
        currentSession().saveOrUpdate(checkNotNull(entity));
        return entity;
    }
   
    public void delete(Long id) 
    {
        currentSession().delete(findById(id));
    }

    public void delete(Integer id) 
    {
        currentSession().delete(findById(id));
    }
    
    public E update(E entity)
    {
        return (E) currentSession().merge(entity);
    }

    public List<E> findAll(){
        return list(criteria());
    }

    public List getQuery(String query){
    	return currentSession().getNamedQuery(query).list();
    }

    protected Session currentSession() {
        return this.sessionFactory.getCurrentSession();
    }
    
    protected void closeCurrentSessionFactory() {
        this.sessionFactory.close();
    }

    protected Criteria criteria() {
        return currentSession().createCriteria(entityClass);
    }

    protected Query namedQuery(String queryName) throws HibernateException {
        return currentSession().getNamedQuery(checkNotNull(queryName));
    }

    protected E uniqueResult(Criteria criteria) throws HibernateException {
        return (E) checkNotNull(criteria).uniqueResult();
    }

    protected List<E> list(Criteria criteria) throws HibernateException {
        return checkNotNull(criteria).list();
    }

}
