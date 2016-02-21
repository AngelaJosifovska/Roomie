package mk.ukim.finki.roomie.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import mk.ukim.finki.roomie.model.User;

import org.springframework.stereotype.Repository;

@Repository
public class PersonalProfileRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public User findByUserID(int id) {
		CriteriaBuilder criteria = em.getCriteriaBuilder();
		CriteriaQuery<User> critQuery = criteria.createQuery(User.class);
		Root<User> root = critQuery.from(User.class);
		
//		Join<User, PersonalProfile> profile = root.join("PersonalProfile.for_user");
		
		Predicate userID = criteria.equal(root.get("id"), id);
		critQuery.where(userID);
		
		TypedQuery<User> query = em.createQuery(critQuery);
		
		return query.getSingleResult();
	}
}
