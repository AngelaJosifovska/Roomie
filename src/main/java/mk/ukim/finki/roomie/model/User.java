package mk.ukim.finki.roomie.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

enum RegistrationStatus {
	personal,
	roommate,
	complete
}
enum Role {
	admin,
	user
}

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String email;

	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false,columnDefinition = "default 'user'")
	@Enumerated(EnumType.STRING)
	private Role role=Role.user;
	
	@Column(nullable=false,columnDefinition = "default true")
	private boolean profile_active=true;

	@Column(nullable=true,columnDefinition = "default NULL")
	private String remember_token=null;
	
	@Column(nullable=false,columnDefinition = "default 'personal'")
	@Enumerated(EnumType.STRING)
	private RegistrationStatus registration_status=RegistrationStatus.personal;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, insertable=true, updatable=false, columnDefinition = "default CURRENT_TIMESTAMP")
	private Date created_at;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, insertable=true, updatable=true, columnDefinition = "default CURRENT_TIMESTAMP")
	private Date updated_at;
	
	@PrePersist
	void onCreate(){
		this.created_at = new Date();
		this.updated_at = new Date();
	}
	@PreUpdate
	void onPersist(){
		this.updated_at = new Date();
	}
	
	public User(){
		
	}
    public User(String name){
		this.name=name;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public boolean getProfile_active() {
		return profile_active;
	}
	public void setProfile_active(boolean profile_active) {
		this.profile_active = profile_active;
	}
	@JsonIgnore
	public String getRemember_token() {
		return remember_token;
	}
	@JsonProperty
	public void setRemember_token(String remember_token) {
		this.remember_token = remember_token;
	}
	public RegistrationStatus getRegistration_status() {
		return registration_status;
	}
	public void setRegistration_status(RegistrationStatus registration_status) {
		this.registration_status = registration_status;
	}
	public void setCreated_at(Date date){
		this.created_at=date;
	}
	public String getCreated_at() {
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formated = format.format(created_at);
		return formated;
	}
	@JsonIgnore
	public Date getCreated_at_Date() {
		return created_at;
	}
	public String getUpdated_at() {
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formated = format.format(updated_at);
		return formated;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email
				+ ", password=" + password + ", role=" + role
				+ ", profile_active=" + profile_active + ", remember_token="
				+ remember_token + ", registration_status="
				+ registration_status + ", created_at=" + created_at
				+ ", updated_at=" + updated_at + "]";
	}

	
	
	

}


