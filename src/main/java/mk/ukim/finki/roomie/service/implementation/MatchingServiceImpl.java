package mk.ukim.finki.roomie.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.ukim.finki.roomie.helper.PotentialMatch;
import mk.ukim.finki.roomie.model.Match;
import mk.ukim.finki.roomie.repository.MatchingRepository;
import mk.ukim.finki.roomie.service.MatchingService;

@Service
public class MatchingServiceImpl implements MatchingService {
	
	@Autowired
	private MatchingRepository matchingRepository;

	public PotentialMatch getNextSuggestionForMatching(int user_id) {
		return matchingRepository.getNextSuggestion(user_id);
	}

	public List<Match> getAllMatchedPairsByUserID(int user_id, int page, int maxResults) {
		return matchingRepository.getAllMatchesForUser(user_id, page, maxResults);
	}

	public Long getTotal(int user_id) {
		return matchingRepository.getTotal(user_id);
	}

	public Match storeMatch(Match match) {
		return matchingRepository.saveOrUpdate(match);
	}

}
