package mk.ukim.finki.roomie.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.ukim.finki.roomie.model.User;
import mk.ukim.finki.roomie.repository.UserRepository;
import mk.ukim.finki.roomie.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		return userRepository.getUserById(id);
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	public User storeUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.saveOrUpdate(user);
	}

	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.saveOrUpdate(user);
	}

	public List<User> getAllUsers(int page, int maxResults) {
		// TODO Auto-generated method stub
		return userRepository.findAll(page, maxResults);
	}

	public Long getTotal() {
		// TODO Auto-generated method stub
		return userRepository.getTotal();
	}

}
