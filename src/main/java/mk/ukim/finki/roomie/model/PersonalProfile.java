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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mk.ukim.finki.roomie.model.enums.Gender;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "personal_profiles")
public class PersonalProfile {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   
	@JsonBackReference
	@OneToOne(optional = false, targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "for_user", nullable = false, referencedColumnName = "id")
    private User for_user;
   
    @Column(nullable = false)
    private String first_name;
    
    @Column(nullable = false)
    private String last_name;
      
    @Column(nullable = false, columnDefinition = "default 'Male'")
    @Enumerated(EnumType.STRING)
    private Gender gender;
   
    @Column(nullable = false, columnDefinition = "default 'Single'")
    private String relationship_status;
   
    @Column(nullable = true, columnDefinition = "default NULL")
    private String education;
    
    @Column(nullable = true, columnDefinition = "default NULL")
    private String occupation;
    
    @Column(nullable = true, columnDefinition = "default NULL")
    private String workplace;
    
    @Column(nullable = true, columnDefinition = "default NULL")
    private String hometown;
    
    @Column(nullable = true, columnDefinition = "default NULL")
    private String location;
    
    @Column(nullable = true, columnDefinition = "default NULL", length = 1000)
    private String description;
   
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, insertable = true, updatable = false, columnDefinition = "default CURRENT_TIMESTAMP")
    private Date created_at;
   
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, insertable = false, updatable = true, columnDefinition = "default CURRENT_TIMESTAMP")
    private Date updated_at;
   
    @PrePersist
    void onCreate(){
//        this.created_at = new Timestamp(new Date().getTime());
    	this.created_at = new Date();
    }
    
    @PreUpdate
    void onPersist(){
//        this.updated_at = new Timestamp(new Date().getTime());
    	this.updated_at = new Date();
    }

	public PersonalProfile() {
		super();
	}

	public PersonalProfile(User for_user, String first_name, String last_name,
			Gender gender, String relationship_status) {
		super();
		this.for_user = for_user;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.relationship_status = relationship_status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getFor_user() {
		return for_user;
	}

	public void setFor_user(User for_user) {
		this.for_user = for_user;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getRelationship_status() {
		return relationship_status;
	}

	public void setRelationship_status(String relationship_status) {
		this.relationship_status = relationship_status;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getWorkplace() {
		return workplace;
	}

	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated_at() {
		return created_at;
	}

//	public void setCreated_at(Timestamp created_at) {
//		this.created_at = created_at;
//	}

	public Date getUpdated_at() {
		return updated_at;
	}

//	public void setUpdated_at(Timestamp updated_at) {
//		this.updated_at = updated_at;
//	}
    
}
