package mk.ukim.finki.roomie.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "locations")
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private double latitude;
	
	@Column(nullable = false)
	private double longitude;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, insertable=true, updatable=false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
	private Date created_at;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, insertable=true, updatable=true, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
	private Date updated_at;
	
	@PrePersist
	public void onCreate(){
		this.created_at = new Date();
		this.updated_at = new Date();
	}
	
	@PreUpdate
	public void onPersist(){
		this.updated_at = new Date();
	}

	public Location() {
		super();
	}

	public Location(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
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
}
