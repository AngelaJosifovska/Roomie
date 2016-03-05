package mk.ukim.finki.roomie.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.ukim.finki.roomie.model.User;
import mk.ukim.finki.roomie.repository.UserRepository;
import mk.ukim.finki.roomie.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User getUserById(Integer id) {
		return userRepository.getUserById(id);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User storeUser(User user) {
		return userRepository.saveOrUpdate(user);
	}

	public User updateUser(User user) {
		return userRepository.saveOrUpdate(user);
	}

	public List<User> getAllUsers(int page, int maxResults) {
		return userRepository.findAll(page, maxResults);
	}

	public Long getTotal() {
		return userRepository.getTotal();
	}

}
