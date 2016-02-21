package mk.ukim.finki.roomie.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import mk.ukim.finki.roomie.model.Comment;
import mk.ukim.finki.roomie.model.RentalUnit;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CommentRepository {

	@PersistenceContext
	private EntityManager em;
	
	public Comment getCommentById(Long id){
		 CriteriaBuilder cb = em.getCriteriaBuilder();
		 CriteriaQuery<Comment> cq = cb.createQuery(Comment.class);
		 final Root<Comment> root = cq.from(Comment.class);

		 Predicate byId = cb.equal(root.get("id"), id);

		 cq.where(byId);

		 TypedQuery<Comment> query = em.createQuery(cq);

		 return query.getSingleResult();
	}
	
	public List<Comment> findAll(long rental_id){
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Comment> cq = cb.createQuery(Comment.class);
	    final Root<Comment> root = cq.from(Comment.class);

	    cq.where(cb.equal(root.get("rentalUnit"), rental_id));
	    
	    TypedQuery<Comment> query = em.createQuery(cq);

	    return query.getResultList();
	}
	@Transactional
	public Comment saveOrUpdate(Comment entity) {
	    if(entity.getId() != null && !em.contains(entity)) {
	      Comment old=getCommentById(entity.getId());
	      entity.setCreated_at(old.getCreated_at_Date());
	      entity = em.merge(entity);
	    } else {
	      em.persist(entity);
	    }
	    em.flush();
	    return entity;
	}
	
	@Transactional
	  public void delete(Long id) {
	    CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaDelete<Comment> cd = cb.createCriteriaDelete(Comment.class);
	    final Root<Comment> root = cd.from(Comment.class);


	    CriteriaQuery<Comment> cq = cb.createQuery(Comment.class);
	    Predicate byId = cb.equal(root.get("id"), id);
	    cd.where(byId);
	    em.createQuery(cd).executeUpdate();
	    em.flush();
	  }

}
