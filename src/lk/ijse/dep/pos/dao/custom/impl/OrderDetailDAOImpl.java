package lk.ijse.dep.pos.dao.custom.impl;

import lk.ijse.dep.pos.dao.CrudDAOImpl;
import lk.ijse.dep.pos.dao.custom.OrderDetailDAO;
import lk.ijse.dep.pos.entity.OrderDetail;
import lk.ijse.dep.pos.entity.OrderDetailPK;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDetailDAOImpl extends CrudDAOImpl<OrderDetail, OrderDetailPK> implements OrderDetailDAO {

    @Override
    public boolean existsByItemCode(String itemCode) throws Exception {
        return getSession().createNativeQuery("SELECT * FROM OrderDetail WHERE item_code=?1")
                .setParameter(1, itemCode).uniqueResult() != null;
    }
}
