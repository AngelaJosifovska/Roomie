package mk.ukim.finki.roomie.service;

import java.util.List;

import mk.ukim.finki.roomie.helper.CategoryFrequency;
import mk.ukim.finki.roomie.model.PersonalProfile;
import mk.ukim.finki.roomie.model.User;

public interface PersonalProfileService {
	
	public User getSinglePersonalProfile(int userID);
	
	public User storePersonalProfile(PersonalProfile personalProfile);
	
	public User updatePersonalProfile(PersonalProfile personalProfile);
	
	public List<CategoryFrequency> usersGroupBy(String category);

}
