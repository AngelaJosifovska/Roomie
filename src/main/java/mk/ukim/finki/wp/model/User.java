package mk.ukim.finki.wp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	private String name;
	private String email;
	private String password;
	private String role;
	private Integer profile_active;
	private String remember_token;
	
	

}
