package mk.ukim.finki.roomie.service;

import java.util.List;

import mk.ukim.finki.roomie.model.User;

public interface UserService {

	public User getUserById(Long id);
	
	public List<User> getAllUsers();
	
	public User storeUser(User user);
	
	public User updateUser(User user);
	
}
