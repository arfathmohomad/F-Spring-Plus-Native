package lk.ijse.dep.pos.dao.custom.impl;

import lk.ijse.dep.pos.dao.custom.QueryDAO;
import lk.ijse.dep.pos.entity.CustomEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QueryDAOImpl implements QueryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public CustomEntity getOrderInfo(int orderId) throws Exception {
        return null;
    }

    @Override
    public CustomEntity getOrderInfo2(int orderId) throws Exception {
        return null;

    }

    @Override
    public List<CustomEntity> getOrdersInfo(String query) throws Exception {

        NativeQuery nativeQuery = getSession().createNativeQuery("SELECT O.id as orderId, C.customerId as customerId, C.name as customerName, O.date as orderDate, SUM(OD.qty * OD.unit_price) AS orderTotal  FROM Customer C INNER JOIN `Order` O ON C.customerId=O.customer_Id " +
                "INNER JOIN OrderDetail OD on O.id = OD.order_id WHERE O.id LIKE ?1 OR C.customerId LIKE ?2 OR C.name LIKE ?3 OR O.date LIKE ?4 GROUP BY O.id");

        nativeQuery.setParameter(1, query);
        nativeQuery.setParameter(2, query);
        nativeQuery.setParameter(3, query);
        nativeQuery.setParameter(4, query);


        Query query1 = nativeQuery.setResultTransformer(Transformers.aliasToBean(CustomEntity.class));

        List<CustomEntity> list = query1.list();

        return list;


    }

}