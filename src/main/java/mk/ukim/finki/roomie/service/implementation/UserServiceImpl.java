package mk.ukim.finki.roomie.service.implementation;

import java.util.ArrayList;
import java.util.List;

import mk.ukim.finki.roomie.helper.CategoryFrequency;
import mk.ukim.finki.roomie.model.User;
import mk.ukim.finki.roomie.repository.UserRepository;
import mk.ukim.finki.roomie.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@SuppressWarnings("serial")
class MyAuthority implements GrantedAuthority{
	
	private String role;
	
	public MyAuthority(String role){
		this.role=role;
	}

	public String getAuthority() {
		return role;
	}
	
}

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

	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
 

    public final org.springframework.security.core.userdetails.User loadUserByUsername(String username) throws UsernameNotFoundException {   	
    	User originalUser=userRepository.getUserByEmail(username);
    	 if (originalUser == null) {
             throw new UsernameNotFoundException("user not found");
         }
        GrantedAuthority authority=new MyAuthority(originalUser.getRoleString());
        List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
        authorities.add(authority);
        final org.springframework.security.core.userdetails.User user = 
        new org.springframework.security.core.userdetails.User(originalUser.getEmail(),originalUser.getPassword(),authorities);      
        detailsChecker.check(user);
        return user;
    }

	public User getUserByUsername(String username) {
		return userRepository.getUserByEmail(username);
	}

	public List<CategoryFrequency> usersGroupBy(String category) {
		return null;
	}

	public int getNumberOfNewUsers() {
		// TODO Auto-generated method stub
		return userRepository.numberOfNewUsers();
	}
    

}
