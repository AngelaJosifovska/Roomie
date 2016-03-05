package mk.ukim.finki.roomie.service.implementation;

import mk.ukim.finki.roomie.model.User;
import mk.ukim.finki.roomie.repository.PersonalProfileRepository;
import mk.ukim.finki.roomie.service.PersonalProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalProfileServiceImpl implements PersonalProfileService {
	
	@Autowired
	private PersonalProfileRepository repo;
	
	public User getSinglePersonalProfile(int userID) {
		return repo.findByUserID(userID);
	}
}
