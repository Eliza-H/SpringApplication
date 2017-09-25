package com.example.spring.dao;

import com.example.spring.model.UserProfile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("userProfileDao")
public class UserProfileDaoImpl implements UserProfileDao{
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<UserProfile> findAll(){ //TODO order
		CriteriaQuery<UserProfile> criteriaQuery = em.getCriteriaBuilder().createQuery(UserProfile.class);
		criteriaQuery.from(UserProfile.class);
		return em.createQuery(criteriaQuery).getResultList();
	}

	public UserProfile findById(int id) {
		CriteriaQuery<UserProfile> criteriaQuery = em.getCriteriaBuilder().createQuery(UserProfile.class);
		criteriaQuery.from(UserProfile.class);
		return em.createQuery(criteriaQuery).getSingleResult();
	}

	public UserProfile findByType(String type) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<UserProfile> query = criteriaBuilder.createQuery(UserProfile.class);
		Root<UserProfile> root = query.from(UserProfile.class);
		query.select(root).where(criteriaBuilder.equal(root.get("type"), type));
		return  em.createQuery(query).getSingleResult();
	}
}
