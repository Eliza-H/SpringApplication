package com.example.spring.dao;

import com.example.spring.model.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("ProductDao")
public class ProductDaoImpl {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public List<Product> getLast() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> from = criteriaQuery.from(Product.class);
        criteriaQuery.orderBy(criteriaBuilder.asc(from.get("creationDate")));
        return em.createQuery(criteriaQuery).getResultList();
    }

    public void add(Product product) {
        em.persist(product);
    }

    public Product getById(Long id) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Product> q = criteriaBuilder.createQuery(Product.class);
        Root<Product> from = q.from(Product.class);
        q.where(criteriaBuilder.equal(from.get("id"), id));
        return em.createQuery(q).getSingleResult();

    }
}
