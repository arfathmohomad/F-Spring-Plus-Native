package lk.ijse.dep.pos.dao;

import lk.ijse.dep.pos.AppInitializer;
import lk.ijse.dep.pos.dao.custom.CustomerDAO;
import lk.ijse.dep.pos.dao.custom.ItemDAO;
import lk.ijse.dep.pos.entity.SuperEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Calendar;
import java.util.List;

public abstract class CrudDAOImpl<T extends SuperEntity, ID extends Serializable> implements CrudDAO<T, ID> {

    @Autowired
    protected SessionFactory sessionFactory;
    private Class<T> entity;

    public CrudDAOImpl() {
        entity = (Class<T>)(((ParameterizedType)(this.getClass().getGenericSuperclass())).
                getActualTypeArguments()[0]);
    }

    @Override
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void delete(ID id) throws Exception {
        getSession().delete(getSession().load(entity, id));
    }

    @Override
    public List<T> findAll() throws Exception {
        return getSession().createQuery("FROM " + entity.getName()).list();
    }

    @Override
    public T find(ID id) throws Exception {
        return getSession().get(entity, id);
    }

    @Override
    public void save(T entity) throws Exception {
        getSession().save(entity);
    }

    @Override
    public void update(T entity) throws Exception {
        getSession().merge(entity);
    }


}
