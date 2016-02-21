package mk.ukim.finki.roomie.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.ukim.finki.roomie.model.RoommateProfile;
import mk.ukim.finki.roomie.repository.RoommateProfileRepository;
import mk.ukim.finki.roomie.service.RoommateProfileService;

@Service
public class RoommateProfileServiceImplementation implements RoommateProfileService {
	
	@Autowired
	private RoommateProfileRepository repo;

	public RoommateProfile getSingleRoommateProfile(int userID) {
		return repo.findByUserID(userID);
	}

}
