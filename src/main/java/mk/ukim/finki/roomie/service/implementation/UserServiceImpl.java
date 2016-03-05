package mk.ukim.finki.roomie.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mk.ukim.finki.roomie.model.User;
import mk.ukim.finki.roomie.repository.UserRepository;
import mk.ukim.finki.roomie.service.UserService;

class MyAuthoritiy implements GrantedAuthority{
	
	private String role;
	
	public MyAuthoritiy(String role){
		this.role=role;
	}

	public String getAuthority() {
		// TODO Auto-generated method stub
		return role;
	}
	
}
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

	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
 

    public final org.springframework.security.core.userdetails.User loadUserByUsername(String username) throws UsernameNotFoundException {   	
    	User originalUser=userRepository.getUserByEmail(username);
    	 if (originalUser == null) {
             throw new UsernameNotFoundException("user not found");
         }
        GrantedAuthority authority=new MyAuthoritiy(originalUser.getRoleString());
        List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
        authorities.add(authority);
        final org.springframework.security.core.userdetails.User user = 
        new org.springframework.security.core.userdetails.User(originalUser.getEmail(),originalUser.getPassword(),authorities);      
        detailsChecker.check(user);
        return user;
    }

	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.getUserByEmail(username);
	}
    

}
