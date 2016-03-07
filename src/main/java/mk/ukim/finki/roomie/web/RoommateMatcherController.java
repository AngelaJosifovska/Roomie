package mk.ukim.finki.roomie.web;

import java.util.List;

import mk.ukim.finki.roomie.helper.HelperPaginatedResponse;
import mk.ukim.finki.roomie.helper.PotentialMatch;
import mk.ukim.finki.roomie.model.Match;
import mk.ukim.finki.roomie.model.User;
import mk.ukim.finki.roomie.service.MatchingService;
import mk.ukim.finki.roomie.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	 * Display the total count of matches for a given user.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/total", method = RequestMethod.GET)
	public @ResponseBody long total(@PathVariable int id) {
		return matchingService.getTotal(id);
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
		if(nextSuggestion == null)
			return nextSuggestion;
		
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
	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Match store(@PathVariable int id, @RequestBody Match match) {
		User from_user = userService.getUserById(id);
		match.setFrom_user(from_user);
		return matchingService.storeMatch(match);
	}
}
