package mk.ukim.finki.roomie.service;

import java.util.List;

import mk.ukim.finki.roomie.model.RentalUnit;
import mk.ukim.finki.roomie.model.User;

public interface UserService {

	public User getUserById(Integer id);
	
	public List<User> getAllUsers();
	
    public List<User> getAllUsers(int page, int maxResults);
	
	public Long getTotal();
	
	public User storeUser(User user);
	
	public User updateUser(User user);
	
}
