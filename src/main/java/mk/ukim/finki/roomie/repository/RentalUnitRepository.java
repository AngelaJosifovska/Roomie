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

import mk.ukim.finki.roomie.model.RentalUnit;
import mk.ukim.finki.roomie.model.User;

@Repository
public class RentalUnitRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public RentalUnit getRentalUnitById(Long id){
		 CriteriaBuilder cb = em.getCriteriaBuilder();
		 CriteriaQuery<RentalUnit> cq = cb.createQuery(RentalUnit.class);
		 final Root<RentalUnit> root = cq.from(RentalUnit.class);

		 Predicate byId = cb.equal(root.get("id"), id);

		 cq.where(byId);

		 TypedQuery<RentalUnit> query = em.createQuery(cq);

		 return query.getSingleResult();
	}
	public List<RentalUnit> findAll(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<RentalUnit> cq = cb.createQuery(RentalUnit.class);
	    final Root<RentalUnit> root = cq.from(RentalUnit.class);

	    TypedQuery<RentalUnit> query = em.createQuery(cq);

	    return query.getResultList();
	}
	@Transactional
	public RentalUnit saveOrUpdate(RentalUnit entity) {
	    if(entity.getId() != null && !em.contains(entity)) {
	      RentalUnit old=getRentalUnitById(entity.getId());
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
