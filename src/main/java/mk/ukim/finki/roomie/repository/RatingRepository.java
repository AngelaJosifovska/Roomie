package mk.ukim.finki.roomie.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import mk.ukim.finki.roomie.model.Rating;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RatingRepository {

	@PersistenceContext
	private EntityManager em;
	
	public Rating getRatingByRentalUnitID(Integer rental_id){
		 CriteriaBuilder cb = em.getCriteriaBuilder();
		 CriteriaQuery<Rating> cq = cb.createQuery(Rating.class);
		 final Root<Rating> root = cq.from(Rating.class);

		 Predicate byId = cb.equal(root.get("rentalUnit"), rental_id);

		 cq.where(byId);

		 TypedQuery<Rating> query = em.createQuery(cq);

		 return query.getSingleResult();
	}
	
	public Double getAverageRatingForRentalUnit(Integer rental_id){	    
		String queryText = "SELECT AVG(rating_points) FROM Rating WHERE on_rental = (:rental_id)";
	    TypedQuery<Double> query = em.createQuery(queryText, Double.class);
	    query.setParameter("rental_id", rental_id);
	    
	    return query.getSingleResult();
	}
	
	@Transactional
	public Rating saveOrUpdate(Rating entity) {
	    if(entity.getId() != null && !em.contains(entity)) {
//	      Rating old=getRatingById(entity.getId());
//	      entity.setCreated_at(old.getCreated_at_Date());
	      entity = em.merge(entity);
	    } else {
	      em.persist(entity);
	    }
	    em.flush();
	    return entity;
	}
}
