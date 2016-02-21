package mk.ukim.finki.roomie.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mk.ukim.finki.roomie.model.User;

@Repository
public class UserRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public User getUserById(Long id){
		 CriteriaBuilder cb = em.getCriteriaBuilder();
		 CriteriaQuery<User> cq = cb.createQuery(User.class);
		 final Root<User> root = cq.from(User.class);

		 Predicate byId = cb.equal(root.get("id"), id);

		 cq.where(byId);

		 TypedQuery<User> query = em.createQuery(cq);

		 return query.getSingleResult();
	}
	public List<User> findAll(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<User> cq = cb.createQuery(User.class);
	    final Root<User> root = cq.from(User.class);

	    TypedQuery<User> query = em.createQuery(cq);

	    return query.getResultList();
	}
	@Transactional
	public User saveOrUpdate(User entity) {
	    if (entity.getId() != null && !em.contains(entity)) {
	      User old=getUserById(entity.getId());
	      entity.setCreated_at(old.getCreated_at_Date());
	      entity = em.merge(entity);
	    } else {
	      em.persist(entity);
	    }
	    em.flush();
	    System.out.println(entity.toString());
	    return entity;
	}


}
