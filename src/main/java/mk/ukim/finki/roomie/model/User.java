package mk.ukim.finki.roomie.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mk.ukim.finki.roomie.model.enums.RegistrationStatus;
import mk.ukim.finki.roomie.model.enums.Role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
 
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   
    @Column(nullable=false)
    private String name;
   
    @Column(nullable=false)
    private String email;
   
    @Column(nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
   
    @Column(nullable=false, columnDefinition = "default 'user'")
    @Enumerated(EnumType.STRING)
	private Role role = Role.user;
   
	@Column(nullable=false,columnDefinition = "default true")
	private boolean profile_active = true;
   
    @JsonIgnore
    @Column(nullable=true,columnDefinition = "default NULL")
	private String remember_token = null;
   
    @Column(nullable=false,columnDefinition = "default 'personal'")
    @Enumerated(EnumType.STRING)
	private RegistrationStatus registration_status = RegistrationStatus.personal;
   
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, insertable=true, updatable=false, columnDefinition = "default CURRENT_TIMESTAMP")
    private Date created_at;
   
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, insertable=true, updatable=true, columnDefinition = "default CURRENT_TIMESTAMP")
    private Date updated_at;
    
    @JsonManagedReference
    @OneToOne(mappedBy = "for_user", targetEntity = RoommateProfile.class)
    private RoommateProfile roommate_profile;
    
    @JsonManagedReference
    @OneToOne(mappedBy = "for_user", targetEntity = PersonalProfile.class)
    private PersonalProfile personal_profile;
    
    @JsonManagedReference
    @OneToOne(mappedBy = "user", targetEntity = ProfileImage.class, cascade = CascadeType.ALL)
    private ProfileImage profile_image;
   
    @PrePersist
    public void onCreate(){
    	this.created_at = new Date();
    	this.updated_at = new Date();
    }
    
    @PreUpdate
    public void onPersist(){
    	this.updated_at = new Date();
    }
   
    public User(){
       
    }
    
    public User(String name, String email, String password) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
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

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Role getRole() {
        return role;
    }
    public String getRoleString(){
    	return role.name();
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
    
    public Date getCreated_at() {
        return created_at;
    }
    
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    
    public Date getUpdated_at() {
        return updated_at;
    }
    
    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

	public RoommateProfile getRoommate_profile() {
		return roommate_profile;
	}

	public void setRoommate_profile(RoommateProfile roommate_profile) {
		this.roommate_profile = roommate_profile;
	}

	public PersonalProfile getPersonal_profile() {
		return personal_profile;
	}

	public void setPersonal_profile(PersonalProfile personal_profile) {
		this.personal_profile = personal_profile;
	}
   
   	public ProfileImage getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(ProfileImage profile_image) {
		this.profile_image = profile_image;
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
