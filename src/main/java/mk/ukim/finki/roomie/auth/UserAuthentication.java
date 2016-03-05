package mk.ukim.finki.roomie.auth;
import java.util.Collection;

import javax.security.auth.Subject;

import mk.ukim.finki.roomie.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


public class UserAuthentication implements Authentication {

    private final User user;
    private mk.ukim.finki.roomie.model.User originalUser;
    private boolean authenticated = true;

    public UserAuthentication(User user,mk.ukim.finki.roomie.model.User originalUser) {
        this.user = user;
        this.originalUser = originalUser;
    }

    
    public String getName() {
        return user.getUsername();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    public Object getCredentials() {
        return user.getPassword();
    }

    public User getDetails() {
        return user;
    }

    public Object getPrincipal() {
        return user.getUsername();
    }
    
    public mk.ukim.finki.roomie.model.User getUser(){
    	return originalUser;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

	public boolean implies(Subject arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}