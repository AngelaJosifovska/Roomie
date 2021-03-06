package mk.ukim.finki.roomie.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import mk.ukim.finki.roomie.helper.CategoryFrequency;
import mk.ukim.finki.roomie.model.User;
import mk.ukim.finki.roomie.model.enums.RegistrationStatus;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public User getUserById(Integer id){
		 CriteriaBuilder cb = em.getCriteriaBuilder();
		 CriteriaQuery<User> cq = cb.createQuery(User.class);
		 final Root<User> root = cq.from(User.class);

		 Predicate byId = cb.equal(root.get("id"), id);
		 cq.where(byId);

		 TypedQuery<User> query = em.createQuery(cq);
		 return query.getSingleResult();
	}
	
	public User getUserByEmail(String email){
		 CriteriaBuilder cb = em.getCriteriaBuilder();
		 CriteriaQuery<User> cq = cb.createQuery(User.class);
		 final Root<User> root = cq.from(User.class);

		 Predicate byId = cb.equal(root.get("email"),email);

		 cq.where(byId);

		 TypedQuery<User> query = em.createQuery(cq);
         
		 User user= query.getSingleResult();
		 
		 return user;
	}
	
	public List<User> findAll(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<User> cq = cb.createQuery(User.class);
	    final Root<User> root = cq.from(User.class);
	    
	    Predicate ifActive = cb.equal(root.get("profile_active"), true);
	    Predicate ifComplete = cb.equal(root.get("registration_status"), RegistrationStatus.complete);
	    
	    cq.where(ifActive, ifComplete);

	    TypedQuery<User> query = em.createQuery(cq);

	    return query.getResultList();
	}
	public int numberOfNewUsers(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<User> cq = cb.createQuery(User.class);
	    final Root<User> root = cq.from(User.class);
	    
	    Predicate ifActive = cb.equal(root.get("profile_active"), true);
	    Predicate ifComplete = cb.equal(root.get("registration_status"), RegistrationStatus.complete);
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DAY_OF_MONTH, -30);
	    Date monthAgo=cal.getTime();
	    Predicate ifNew = cb.greaterThanOrEqualTo(root.<Date>get("created_at"), monthAgo);
	    
	    cq.where(ifActive, ifComplete, ifNew);

	    TypedQuery<User> query = em.createQuery(cq);

	    List<User> users= query.getResultList();
	    return users.size();
	}
	
	public List<User> findAll(int page, int maxResults){
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<User> cq = cb.createQuery(User.class);
	    final Root<User> root = cq.from(User.class);
	    
	    Predicate ifActive = cb.equal(root.get("profile_active"), true);
	    Predicate ifComplete = cb.equal(root.get("registration_status"), RegistrationStatus.complete);
	    
	    cq.where(ifActive, ifComplete);
	    
	    cq.orderBy(cb.desc(root.get("created_at")));

	    TypedQuery<User> query = em.createQuery(cq)
	    		.setFirstResult((page - 1) * maxResults)
	    		.setMaxResults(maxResults);

	    return query.getResultList();
	}
	
	public long getTotal(){
		return this.findAll().size();
	}
	
	@Transactional
	public User saveOrUpdate(User entity) {
	    if (entity.getId() != null && !em.contains(entity)) {
	      entity = em.merge(entity);
	    } else {
	      em.persist(entity);
	    }
	    em.flush();
	    return entity;
	}
	
	public List<CategoryFrequency> usersGroupBy(String category){
		  CriteriaBuilder cb = em.getCriteriaBuilder();
		  CriteriaQuery<Object[]> q = cb.createQuery(Object[].class);
		  Root<User> c = q.from(User.class);
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
