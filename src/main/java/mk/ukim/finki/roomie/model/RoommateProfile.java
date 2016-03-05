package mk.ukim.finki.roomie.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "roommate_profiles")
public class RoommateProfile {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   
	@JsonBackReference
	@OneToOne(optional = false, targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "for_user", nullable = false, referencedColumnName = "id")
    private User for_user;
	
	@Column(nullable = true)
    private Integer cleanliness;
	
	@Column(nullable = true)
    private Integer work_schedule;
	
	@Column(nullable = true)
    private Integer sleep_schedule;
	
	@Column(nullable = true)
    private Integer smoking;
	
	@Column(nullable = true)
    private Integer drinking;
	
	@Column(nullable = true)
    private Integer privacy;
	
	@Column(nullable = true)
    private Integer guests;
	
	@Column(nullable = true)
    private Integer eating_habits;
	
	@Column(nullable = true)
    private Integer pets;
	
	@Column(nullable = false)
    private Integer monthly_budget_lower_limit;
	
	@Column(nullable = false)
    private Integer monthly_budget_upper_limit;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date move_in_from;
	
	@Column(nullable = false)
    private Integer lease_length;
	
	@Column(nullable = false)
    private String looking_for;
	
	@Column(nullable = true, columnDefinition = "default NULL", length = 500)
    private String exercise;
	
	@Column(nullable = true, columnDefinition = "default NULL", length = 500)
    private String hobbies;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, insertable = true, updatable = false, columnDefinition = "default CURRENT_TIMESTAMP")
    private Date created_at;
   
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, insertable = false, updatable = true, columnDefinition = "default CURRENT_TIMESTAMP")
    private Date updated_at;
    
	@PrePersist
    public void onCreate() {
		this.created_at = new Date();
    	this.updated_at = new Date();
    }
    
    @PreUpdate
    public void onPersist(){
    	this.updated_at = new Date();
    }
    
	public RoommateProfile() {
		super();
	}

	public RoommateProfile(User for_user, Integer monthly_budget_lower_limit,
			Integer monthly_budget_upper_limit, Date move_in_from,
			Integer lease_length, String looking_for) {
		super();
		this.for_user = for_user;
		this.monthly_budget_lower_limit = monthly_budget_lower_limit;
		this.monthly_budget_upper_limit = monthly_budget_upper_limit;
		this.move_in_from = move_in_from;
		this.lease_length = lease_length;
		this.looking_for = looking_for;
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

	public Integer getCleanliness() {
		return cleanliness;
	}

	public void setCleanliness(Integer cleanliness) {
		this.cleanliness = cleanliness;
	}

	public Integer getWork_schedule() {
		return work_schedule;
	}

	public void setWork_schedule(Integer work_schedule) {
		this.work_schedule = work_schedule;
	}

	public Integer getSleep_schedule() {
		return sleep_schedule;
	}

	public void setSleep_schedule(Integer sleep_schedule) {
		this.sleep_schedule = sleep_schedule;
	}

	public Integer getSmoking() {
		return smoking;
	}

	public void setSmoking(Integer smoking) {
		this.smoking = smoking;
	}

	public Integer getDrinking() {
		return drinking;
	}

	public void setDrinking(Integer drinking) {
		this.drinking = drinking;
	}

	public Integer getPrivacy() {
		return privacy;
	}

	public void setPrivacy(Integer privacy) {
		this.privacy = privacy;
	}

	public Integer getGuests() {
		return guests;
	}

	public void setGuests(Integer guests) {
		this.guests = guests;
	}

	public Integer getEating_habits() {
		return eating_habits;
	}

	public void setEating_habits(Integer eating_habits) {
		this.eating_habits = eating_habits;
	}

	public Integer getPets() {
		return pets;
	}

	public void setPets(Integer pets) {
		this.pets = pets;
	}

	public Integer getMonthly_budget_lower_limit() {
		return monthly_budget_lower_limit;
	}

	public void setMonthly_budget_lower_limit(Integer monthly_budget_lower_limit) {
		this.monthly_budget_lower_limit = monthly_budget_lower_limit;
	}

	public Integer getMonthly_budget_upper_limit() {
		return monthly_budget_upper_limit;
	}

	public void setMonthly_budget_upper_limit(Integer monthly_budget_upper_limit) {
		this.monthly_budget_upper_limit = monthly_budget_upper_limit;
	}

	public Date getMove_in_from() {
		return move_in_from;
	}

	public void setMove_in_from(Date move_in_from) {
		this.move_in_from = move_in_from;
	}

	public Integer getLease_length() {
		return lease_length;
	}

	public void setLease_length(Integer lease_length) {
		this.lease_length = lease_length;
	}

	public String getLooking_for() {
		return looking_for;
	}

	public void setLooking_for(String looking_for) {
		this.looking_for = looking_for;
	}

	public String getExercise() {
		return exercise;
	}

	public void setExercise(String exercise) {
		this.exercise = exercise;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
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
