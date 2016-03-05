package mk.ukim.finki.roomie.web;

import java.util.List;

import mk.ukim.finki.roomie.helper.HelperPaginatedResponse;
import mk.ukim.finki.roomie.helper.PotentialMatch;
import mk.ukim.finki.roomie.model.Match;
import mk.ukim.finki.roomie.model.User;
import mk.ukim.finki.roomie.service.MatchingService;
import mk.ukim.finki.roomie.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "public/api/Matcher")
public class RoommateMatcherController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private MatchingService matchingService;
	
	/**
	 * Display all the matches for the given user.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/all", method = RequestMethod.GET)
	public @ResponseBody HelperPaginatedResponse<Match> index(@PathVariable int id, @RequestParam int page) {
		long total = matchingService.getTotal(id);
		int maxResults = 10;
		List<Match> data = matchingService.getAllMatchedPairsByUserID(id, page, maxResults);
		HelperPaginatedResponse<Match> response = new HelperPaginatedResponse<Match>(total, maxResults, page, data);
		return response;
	}

	/**
	 * Display the next potential match.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody PotentialMatch show(@PathVariable int id) {
		PotentialMatch nextSuggestion = matchingService.getNextSuggestionForMatching(id);
		User suggestedUser = userService.getUserById(nextSuggestion.getPotential_id());
		nextSuggestion.setPotential_user(suggestedUser);

		return nextSuggestion;
	}
	
	/**
	 * Store a newly created match resource in storage.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public @ResponseBody Match store(@PathVariable int id, @RequestBody Match match) {
		return matchingService.storeMatch(match);
	}
}
