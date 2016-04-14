package mk.ukim.finki.roomie.service.implementation;

import java.util.List;

import mk.ukim.finki.roomie.helper.CategoryFrequency;
import mk.ukim.finki.roomie.model.PersonalProfile;
import mk.ukim.finki.roomie.model.User;
import mk.ukim.finki.roomie.repository.PersonalProfileRepository;
import mk.ukim.finki.roomie.service.PersonalProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalProfileServiceImpl implements PersonalProfileService {
	
	@Autowired
	private PersonalProfileRepository personalProfileRepository;
	
	public User getSinglePersonalProfile(int userID) {
		return personalProfileRepository.findByUserID(userID);
	}

	public List<CategoryFrequency> usersGroupBy(String category) {
		return personalProfileRepository.usersGroupBy(category);
	}

	public User storePersonalProfile(PersonalProfile personalProfile) {
		// TODO Auto-generated method stub
		return personalProfileRepository.saveOrUpdate(personalProfile);		
	}
	public User updatePersonalProfile(PersonalProfile personalProfile) {
		// TODO Auto-generated method stub
		return personalProfileRepository.saveOrUpdate(personalProfile);		
	}
}
