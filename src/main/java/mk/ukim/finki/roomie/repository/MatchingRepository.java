package mk.ukim.finki.roomie.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mk.ukim.finki.roomie.helper.PotentialMatch;
import mk.ukim.finki.roomie.model.Match;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MatchingRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public PotentialMatch getNextSuggestion(int id) {
		String queryText = 
		"SELECT suggestions.potential_id, answers.interested AS interested_for_me " +
		"FROM (" + 
		    "SELECT P.id AS potential_id " + 
		    "FROM (" +
		            "SELECT users.id AS id " + 
		            "FROM users " + 
		            "WHERE users.id NOT IN (:id) AND users.profile_active = 1 AND users.registration_status = 'complete'" +
		        ") P LEFT JOIN (" +
		            "SELECT matches.to_user AS id " +
		            "FROM matches " + 
		            "WHERE matches.from_user = :id" + 
		        ") Q ON P.id = Q.id " +
		    "WHERE Q.id is NULL " +
		") suggestions LEFT JOIN (" + 
		    "SELECT * " + 
		    "FROM matches " +
		    "WHERE matches.to_user = :id" +
		") answers ON suggestions.potential_id = answers.from_user";
		
		Query query = em.createNativeQuery(queryText);
		query.setParameter("id", id);
	
		List<Object[]> allSuggestions = (List<Object[]>)query.getResultList();
		
		if(allSuggestions.isEmpty())
			return null;
		
		return new PotentialMatch((Integer)allSuggestions.get(0)[0], (Boolean)allSuggestions.get(0)[1]);
	}
	
	@SuppressWarnings("unchecked")
	public List<Match> getAllMatchesForUser(int id) {
		String queryString = "SELECT * " + 
				"FROM matches AS m1 " + 
				"WHERE m1.from_user = :id AND m1.interested = 1 AND EXISTS (" + 
					"SELECT * " + 
					"FROM matches AS m2 " + 
					"WHERE m1.from_user = m2.to_user AND m1.to_user = m2.from_user AND m2.interested = 1 " + 
				") ORDER BY m1.created_at DESC";
		
		Query query = em.createNativeQuery(queryString, Match.class);
		query.setParameter("id", id);
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Match> getAllMatchesForUser(int id, int page, int maxResults) {
		String queryString = "SELECT * " + 
				"FROM matches AS m1 " + 
				"WHERE m1.from_user = :id AND m1.interested = 1 AND EXISTS (" + 
					"SELECT * " + 
					"FROM matches AS m2 " + 
					"WHERE m1.from_user = m2.to_user AND m1.to_user = m2.from_user AND m2.interested = 1 " + 
				") ORDER BY m1.created_at DESC";
		
		Query query = em.createNativeQuery(queryString, Match.class)
				.setFirstResult((page - 1) * maxResults)
				.setMaxResults(maxResults);
		query.setParameter("id", id);
		
		return query.getResultList();
	}
	
	public long getTotal(int id){
		return this.getAllMatchesForUser(id).size();
	}
	
	@Transactional
	public Match saveOrUpdate(Match entity) {
	    if (entity.getId() != null && !em.contains(entity)) {
	      entity = em.merge(entity);
	    } else {
	      em.persist(entity);
	    }
	    em.flush();

	    return entity;
	}

}
