package mk.ukim.finki.roomie.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity
@Table(name="property_pictures")
public class PropertyPicture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = true, length = 50)
	private String caption;
	
	@Column(nullable = false)
	private String location;
	
	@Column(nullable = false)
	private String thumb_location;
	
	@JsonIgnore
	@Column(nullable = true)
	private String mime_type;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, insertable = true, updatable = false, columnDefinition = "default CURRENT_TIMESTAMP")
	private Date created_at;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, insertable = true, updatable = true, columnDefinition = "default CURRENT_TIMESTAMP")
	private Date updated_at;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "on_rental")
	private RentalUnit rentalUnit;
	
	@PrePersist
	public void onCreate(){
		this.created_at = new Date();
		this.updated_at = new Date();
	}
	
	@PreUpdate
	public void onPersist(){
		this.updated_at = new Date();
	}

	public PropertyPicture() {
		super();
	}

	public PropertyPicture(String caption, String location, String thumb_location) {
		super();
		this.caption = caption;
		this.location = location;
		this.thumb_location = thumb_location;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getThumb_location() {
		return thumb_location;
	}

	public void setThumb_location(String thumb_location) {
		this.thumb_location = thumb_location;
	}

	public String getMime_type() {
		return mime_type;
	}

	public void setMime_type(String mime_type) {
		this.mime_type = mime_type;
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

	public RentalUnit getRentalUnit() {
		return rentalUnit;
	}

	public void setRentalUnit(RentalUnit rentalUnit) {
		this.rentalUnit = rentalUnit;
	}
}
