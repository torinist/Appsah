package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public abstract class BaseDao<T> {

	protected Class<T> entityClass;
	@PersistenceContext
	private EntityManager em;

	public BaseDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public void create(T entity) {
		em.persist(entity);
	}

	public void edit(T entity) {
		em.merge(entity);
	}

	/*
	 * removeの中にmergeがある理由はエンティティの状態をmanaged状態にするためかな
	 */
	public void remove(T entity) {
		em.remove(em.merge(entity));
	}

	public T find(Object id) {
		return em.find(entityClass, id);
	}

	public TypedQuery<T> createNamedQuery(String name) {
		return em.createNamedQuery(name, entityClass);
	}
}
