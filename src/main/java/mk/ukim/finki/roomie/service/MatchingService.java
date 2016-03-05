package mk.ukim.finki.roomie.service;

import java.util.List;

import mk.ukim.finki.roomie.helper.PotentialMatch;
import mk.ukim.finki.roomie.model.Match;

public interface MatchingService {
	
	public PotentialMatch getNextSuggestionForMatching(int id);
	
	public List<Match> getAllMatchedPairsByUserID(int id, int page, int maxResults);
	
	public Long getTotal(int rental_id);
	
	public Match storeMatch(Match match);
}
