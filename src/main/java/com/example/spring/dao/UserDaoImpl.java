package com.example.spring.dao;

import com.example.spring.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;

    @Override
    public void save(User user) {
        em.persist(user);
    }


	public User findById(int id) {
		CriteriaQuery<User> criteriaQuery = em.getCriteriaBuilder().createQuery(User.class);
		criteriaQuery.from(User.class);
		return em.createQuery(criteriaQuery).getSingleResult();
	}

    @Override
    public List<User> listOfUsers() {
        CriteriaQuery<User> criteriaQuery = em.getCriteriaBuilder().createQuery(User.class);
        criteriaQuery.from(User.class);
        return em.createQuery(criteriaQuery).getResultList();
    }

    public User findByLogin(String login) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root).where(criteriaBuilder.equal(root.get("login"), login));
		return  em.createQuery(query).getSingleResult();
	}
}
