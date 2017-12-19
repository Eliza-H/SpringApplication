package com.example.spring.dao;

import com.example.spring.dao.hibernate.factory.HibernateSessionManager;
import com.example.spring.model.LikeParameters;
import com.example.spring.model.Likes;
import com.example.spring.model.ServiceItem;
import com.example.spring.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by elh on 17.09.17.
 */
@Repository
public class ServiceDaoImpl implements ServiceDao {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private HibernateSessionManager hibernateSessionManager;

    @Override
    public void add(ServiceItem service) {
        em.persist(service);
    }

    @Override
    public List<ServiceItem> list() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<ServiceItem> criteriaQuery = criteriaBuilder.createQuery(ServiceItem.class);
        Root<ServiceItem> from = criteriaQuery.from(ServiceItem.class);
//        criteriaQuery.orderBy(criteriaBuilder.desc(from.get("id"));
        TypedQuery<ServiceItem> query = em.createQuery(criteriaQuery);
//        query.setMaxResults(10);
        return query.getResultList();
    }

    @Override
    public void delete(Long id) throws Exception {
        ServiceItem o = em.find(ServiceItem.class, id);
        if (o == null) {
            throw new Exception("element doesnt exists");
        }
        em.remove(o);
    }

    @Override
    public List<ServiceItem> getLiked(Long userId) {
        List results = em.createNativeQuery("SELECT * FROM likes \n" +
                "    INNER JOIN services \n" +
                "        ON likes.service_item_id = services.id\n" +
                "WHERE user_id="+userId, ServiceItem.class).getResultList();

        return results;
    }

    @Override
    public void like(LikeParameters likeParameters) {
        Query nativeQuery = em.createNativeQuery("INSERT INTO LIKES(USER_ID, SERVICE_ITEM_ID) VALUES(" + likeParameters.getUserId() + "," + likeParameters.getServiceId() + ")");
        nativeQuery.executeUpdate();
//        User user = em.find(User.class, likeParameters.getUserId());
//        ServiceItem serviceItem = em.find(ServiceItem.class, likeParameters.getServiceId());
//        serviceItem.getUsers().add(user);
//        em.persist(serviceItem);

    }

    @Override
    public void unlike(LikeParameters likeParameters) {
        Query nativeQuery = em.createNativeQuery(" DELETE FROM LIKES WHERE user_id ='" + likeParameters.getUserId() + "' AND id = '" + likeParameters.getServiceId() + "'");
        nativeQuery.executeUpdate();
    }
}
















