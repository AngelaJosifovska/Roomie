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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

enum Class{
	Small,
	Standard,
	Large,
	Luxury
}

@Entity
@Table(name="rental_units")
public class RentalUnit {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	private String type;	
	private Integer rent;
	private Date move_in_from;
	private Integer lease_length;
	private String address;
	private String city;
	
	@Column(name="class",nullable=false)
	@Enumerated(EnumType.STRING)
	private Class mclass;
	
	private String description;
	private Integer num_bedrooms;
	private Integer num_bathrooms;
	private String furniture;
	private boolean pets;
	private boolean private_bathroom;
	private boolean wifi;
	private boolean air_conditioning;
	private boolean cable;
	private boolean satellite;
	private boolean elevator;
	private boolean laundry;
	private boolean gym;
	private boolean doorman;
	private boolean property_active;

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
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getRent() {
		return rent;
	}
	public void setRent(Integer rent) {
		this.rent = rent;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@JsonProperty("class")
	public Class getMclass() {
		return mclass;
	}
	@JsonProperty("class")
	public void setMclass(Class mclass) {
		this.mclass = mclass;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getNum_bedrooms() {
		return num_bedrooms;
	}
	public void setNum_bedrooms(Integer num_bedrooms) {
		this.num_bedrooms = num_bedrooms;
	}
	public Integer getNum_bathrooms() {
		return num_bathrooms;
	}
	public void setNum_bathrooms(Integer num_bathrooms) {
		this.num_bathrooms = num_bathrooms;
	}
	public String getFurniture() {
		return furniture;
	}
	public void setFurniture(String furniture) {
		this.furniture = furniture;
	}
	public boolean isPets() {
		return pets;
	}
	public void setPets(boolean pets) {
		this.pets = pets;
	}
	public boolean isPrivate_bathroom() {
		return private_bathroom;
	}
	public void setPrivate_bathroom(boolean private_bathroom) {
		this.private_bathroom = private_bathroom;
	}
	public boolean isWifi() {
		return wifi;
	}
	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}
	public boolean isAir_conditioning() {
		return air_conditioning;
	}
	public void setAir_conditioning(boolean air_conditioning) {
		this.air_conditioning = air_conditioning;
	}
	public boolean isCable() {
		return cable;
	}
	public void setCable(boolean cable) {
		this.cable = cable;
	}
	public boolean isSatellite() {
		return satellite;
	}
	public void setSatellite(boolean satelite) {
		this.satellite = satelite;
	}
	public boolean isElevator() {
		return elevator;
	}
	public void setElevator(boolean elevator) {
		this.elevator = elevator;
	}
	public boolean isLaundry() {
		return laundry;
	}
	public void setLaundry(boolean laundry) {
		this.laundry = laundry;
	}
	public boolean isGym() {
		return gym;
	}
	public void setGym(boolean gym) {
		this.gym = gym;
	}
	public boolean isDoorman() {
		return doorman;
	}
	public void setDoorman(boolean doorman) {
		this.doorman = doorman;
	}
	public boolean isProperty_active() {
		return property_active;
	}
	public void setProperty_active(boolean property_active) {
		this.property_active = property_active;
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
	@JsonProperty
	public User getUser() {
		return user;
	}
	@JsonIgnore
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
	
	

}
