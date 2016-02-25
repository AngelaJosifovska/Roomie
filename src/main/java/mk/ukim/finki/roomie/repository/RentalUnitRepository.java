package mk.ukim.finki.roomie.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import mk.ukim.finki.roomie.model.RentalUnit;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RentalUnitRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public RentalUnit getRentalUnitById(Integer id){
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
	    cq.select(root);

	    TypedQuery<RentalUnit> query = em.createQuery(cq);

	    return query.getResultList();
	}
	public List<RentalUnit> findAll(int page, int maxResults){
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<RentalUnit> cq = cb.createQuery(RentalUnit.class);
	    final Root<RentalUnit> root = cq.from(RentalUnit.class);
	    cq.select(root);

	    TypedQuery<RentalUnit> query = em.createQuery(cq)
	    		.setFirstResult((page-1)*maxResults)
	    		.setMaxResults(maxResults);

	    return query.getResultList();
	}
	public long getTotal(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(RentalUnit.class)));
		return em.createQuery(cq).getSingleResult();
	}
	
	@Transactional
	public RentalUnit saveOrUpdate(RentalUnit entity) {
	    if(entity.getId() != null && !em.contains(entity)) {
//	      RentalUnit old=getRentalUnitById(entity.getId());
//	      entity.setCreated_at(old.getCreated_at_Date());
	      entity = em.merge(entity);
	    } else {
	      em.persist(entity);
	    }
	    em.flush();
	    
	    System.out.println(entity.toString());
	    
	    return entity;
	}


}
