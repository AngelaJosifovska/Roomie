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
	    
	    Predicate ifActive = cb.equal(root.get("property_active"), true);
	    
	    cq.where(ifActive);

	    TypedQuery<RentalUnit> query = em.createQuery(cq);

	    return query.getResultList();
	}
	
	public List<RentalUnit> findAll(int page, int maxResults){
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<RentalUnit> cq = cb.createQuery(RentalUnit.class);
	    final Root<RentalUnit> root = cq.from(RentalUnit.class);

	    Predicate ifActive = cb.equal(root.get("property_active"), true);
	    cq.where(ifActive);
	    
	    cq.orderBy(cb.desc(root.get("created_at")));

	    TypedQuery<RentalUnit> query = em.createQuery(cq)
	    		.setFirstResult((page-1)*maxResults)
	    		.setMaxResults(maxResults);

	    return query.getResultList();
	}
	
	public long getTotal(){
		return this.findAll().size();
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
	

	public List<CategoryFrequency> rentalUnitsGroupBy(String category){
		  CriteriaBuilder cb = em.getCriteriaBuilder();
		  CriteriaQuery<Object[]> q = cb.createQuery(Object[].class);
		  Root<RentalUnit> c = q.from(RentalUnit.class);
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
