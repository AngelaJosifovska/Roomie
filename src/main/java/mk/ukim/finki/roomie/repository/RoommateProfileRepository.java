package mk.ukim.finki.roomie.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import mk.ukim.finki.roomie.model.PersonalProfile;
import mk.ukim.finki.roomie.model.RoommateProfile;
import mk.ukim.finki.roomie.model.User;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RoommateProfileRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public RoommateProfile findByUserID(int id) {
		CriteriaBuilder criteria = em.getCriteriaBuilder();
		CriteriaQuery<RoommateProfile> critQuery = criteria.createQuery(RoommateProfile.class);
		Root<RoommateProfile> root = critQuery.from(RoommateProfile.class);
		
		Predicate userID = criteria.equal(root.get("for_user"), id);
		critQuery.where(userID);
		
		TypedQuery<RoommateProfile> query = em.createQuery(critQuery);
		
		return query.getSingleResult();
	}
	
	@Transactional
	public User saveOrUpdate(RoommateProfile entity) {
	    if (entity.getId() != null && !em.contains(entity)) {
	      entity = em.merge(entity);
	    } else {
	      em.persist(entity);
	    }
	    em.flush();
	    return entity.getFor_user();
	}
	
	
}
