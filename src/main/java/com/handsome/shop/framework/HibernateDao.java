package com.handsome.shop.framework;

import com.wangrj.java_lib.hibernate.Q;
import com.wangrj.java_lib.hibernate.Where;
import com.wangrj.java_lib.java_util.ReflectUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.List;

/**
 * by wangrongjun on 2018/4/12.
 */
public class HibernateDao<T> implements Dao<T> {

    @Resource
    private SessionFactory sessionFactory;
    private Class<T> entityClass;

    public Session getSession() {
//        Session session;
//        try {
//            session = sessionFactory.getCurrentSession();
//        } catch (HibernateException e) {
////            logger.error(e.toString());
//            session = sessionFactory.openSession();
//        }
//        return session;
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    protected Class<T> getEntityClass() {
        if (entityClass == null) {
            entityClass = ReflectUtil.getGenericClass(getClass());
        }
        return entityClass;
    }

    protected String getTableName() {
        Entity tableAnno = getEntityClass().getAnnotation(Entity.class);
        if (tableAnno != null) {
            String name = tableAnno.name();
            if (name.length() > 0) {
                return name;
            }
        }
        return getEntityClass().getSimpleName();
    }

    protected Class<?> getIdFieldType() {
        Field field = ReflectUtil.findByAnno(getEntityClass(), Id.class);
        if (field == null) {
            throw new RuntimeException(getEntityClass().getName() + " : id field not found");
        }
        return field.getType();
    }

    protected List<T> executeNativeQuery(String sql) {
        return executeNativeQuery(sql, 0, 0);
    }

    protected List<T> executeNativeQuery(String sql, int offset, int rowCount) {
        Session session = getSession();
        NativeQuery<T> query = session.createNativeQuery(sql, getEntityClass());
        if (offset >= 0 && rowCount > 0) {
            query.setFirstResult(offset);
            query.setMaxResults(rowCount);
        }
        return query.list();
    }

    protected List<T> executeQuery(String hql) {
        return executeQuery(hql, 0, 0);
    }

    protected List<T> executeQuery(String hql, int offset, int rowCount) {
        Session session = getSession();
        Query<T> query = session.createQuery(hql, getEntityClass());
        if (offset >= 0 && rowCount > 0) {
            query.setFirstResult(offset);
            query.setMaxResults(rowCount);
        }
        return query.list();
    }

    protected int executeQueryCount(String hql) {
        Session session = getSession();
        Long count = (Long) session.createQuery(hql).uniqueResult();
        return count.intValue();
    }

    @Override
    public boolean insert(T entity) {
        Session session = getSession();
        session.save(entity);
        return true;
    }

    @Override
    public boolean delete(Where where) {
        Session session = getSession();
        String hql = "delete from " + getTableName() + (where == null ? "" : where);
        session.createQuery(hql, getEntityClass()).executeUpdate();
        return true;
    }

    @Override
    public boolean deleteAll() {
        Session session = getSession();
        String hql = "delete from " + getTableName();
        session.createQuery(hql, getEntityClass()).executeUpdate();
        return true;
    }

    @Override
    public boolean deleteById(long id) {
        Session session = getSession();
        session.delete(queryById(id));
        return true;
    }

    @Override
    public boolean update(T entity) {
        Session session = getSession();
        session.update(entity);
        return true;
    }

    @Override
    public T queryById(long id) {
        Session session = getSession();
        T entity;
        switch (getIdFieldType().getSimpleName()) {
            case "int":
            case "Integer":
                entity = session.get(getEntityClass(), (int) id);
                break;
            case "long":
            case "Long":
                entity = session.get(getEntityClass(), id);
                break;
            default:
                throw new RuntimeException(getEntityClass().getName() + " : id must be number");
        }
        return entity;
    }

    @Override
    public List<T> queryAll() {
        Session session = getSession();
        String hql = "from " + getTableName();
        return session.createQuery(hql, getEntityClass()).list();
    }

    @Override
    public List<T> query(Where where) {
        Session session = getSession();
        String hql = "from " + getTableName() + (where == null ? "" : where);
        return session.createQuery(hql, getEntityClass()).list();
    }

    @Override
    public List<T> query(Q q) {
        Where where = q.getWhere();
        String hql = "from " + getTableName() + (where == null ? "" : where);
        hql += Q.createOrderBy(q.getOrderBy());
        Session session = getSession();
        Query<T> query = session.createQuery(hql, getEntityClass());
        if (q.getOffset() >= 0 && q.getRowCount() > 0) {
            query.setFirstResult(q.getOffset());
            query.setMaxResults(q.getRowCount());
        }
        return query.list();
    }

    @Override
    public int queryCount(Where where) {
        Session session = getSession();
        String hql = "select count(*) from " + getTableName();
        hql += where == null ? "" : where;
        Long count = (Long) session.createQuery(hql).uniqueResult();
        return count.intValue();
    }

}
