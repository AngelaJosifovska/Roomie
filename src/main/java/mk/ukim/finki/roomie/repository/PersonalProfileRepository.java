package mk.ukim.finki.roomie.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import mk.ukim.finki.roomie.helper.CategoryFrequency;
import mk.ukim.finki.roomie.model.PersonalProfile;
import mk.ukim.finki.roomie.model.User;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PersonalProfileRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public User saveOrUpdate(PersonalProfile entity) {
	    if (entity.getId() != null && !em.contains(entity)) {
	      entity = em.merge(entity);
	    } else {
	      em.persist(entity);
	    }
	    em.flush();
	    return entity.getFor_user();
	}
	
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
	public List<CategoryFrequency> usersGroupBy(String category){
		  CriteriaBuilder cb = em.getCriteriaBuilder();
		  CriteriaQuery<Object[]> q = cb.createQuery(Object[].class);
		  Root<PersonalProfile> c = q.from(PersonalProfile.class);
		  q.multiselect(c.get(category), cb.count(c.get(category)));
		  q.groupBy(c.get(category));
		  List<Object[]> result = em.createQuery(q).getResultList();
		  List<CategoryFrequency> converted = new ArrayList<CategoryFrequency>();
		  for (Object[] objects : result) {
			 CategoryFrequency cf=new CategoryFrequency(objects[0].toString(),(Long)objects[1]);
			 converted.add(cf);
		  }
		  return converted;	  
	}
}
