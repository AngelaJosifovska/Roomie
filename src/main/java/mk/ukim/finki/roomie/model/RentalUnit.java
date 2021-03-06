package mk.ukim.finki.roomie.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mk.ukim.finki.roomie.model.enums.Class;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name="rental_units")
public class RentalUnit {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	
	@Column(insertable=false,updatable=false)
	private Integer user_id;
	
	@Column(nullable = false)
	private String type;	
	
	@Column(nullable = false)
	private Integer rent;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date move_in_from;
	
	@Column(nullable = false)
	private Integer lease_length;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private String city;
	
	@Column(name="class",nullable=false)
	@Enumerated(EnumType.STRING)
	private Class mclass;
	
	@Column(columnDefinition = "default NULL", length = 1000)
	private String description;
	
	@Column(columnDefinition = "default NULL")
	private Integer num_bedrooms;
	
	@Column(columnDefinition = "default NULL")
	private Integer num_bathrooms;
	
	@Column(nullable = false)
	private String furniture;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean pets;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean private_bathroom;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean wifi;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean air_conditioning;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean cable;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean satellite;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean elevator;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean laundry;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean gym;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean doorman;
	
	@Column(nullable = false, columnDefinition = "boolean default true")
	private boolean property_active;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, insertable=true, updatable=false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
	private Date created_at;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, insertable=true, updatable=true, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
	private Date updated_at;
	

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "rentalUnit", targetEntity = PropertyPicture.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<PropertyPicture> property_picture;
	

	@OneToOne(targetEntity = Location.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(nullable = false)
	private Location location;
	
	@PrePersist
	public void onCreate(){
		this.created_at = new Date();
		this.updated_at = new Date();
	}
	
	@PreUpdate
	public void onPersist(){
		this.updated_at = new Date();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
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
	
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public List<PropertyPicture> getProperty_picture() {
		return property_picture;
	}

	public void setProperty_picture(List<PropertyPicture> property_picture) {
		this.property_picture = property_picture;
	}

	public void addPropertyPicture(PropertyPicture property_picture) {
		if(this.property_picture.isEmpty())
			this.property_picture = new ArrayList<PropertyPicture>();
		this.property_picture.add(property_picture);
	}

	
	public Location getLocation() {
		return location;
	}

	
	public void setLocation(Location location) {
		this.location = location;
	}
	@JsonProperty
	public Integer getUser_id() {
		return user_id;
	}
	
	@JsonIgnore
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	
	
}
