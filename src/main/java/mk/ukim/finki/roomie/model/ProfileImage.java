package mk.ukim.finki.roomie.model;

import java.util.Date;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="profile_images")
public class ProfileImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = true)
	private String caption;
	
	@Column(nullable = true, length = 500)
	private String description;
	
	@Column(nullable = false)
	private String location;
	
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
	@OneToOne
	@JoinColumn(name="for_user")
	private User user;
	
	@PrePersist
	public void onCreate(){
		this.created_at = new Date();
		this.updated_at = new Date();
	}
	
	@PreUpdate
	public void onPersist(){
		this.updated_at = new Date();
	}

	public ProfileImage() {
		super();
	}

	public ProfileImage(String caption, String description, String location) {
		super();
		this.caption = caption;
		this.description = description;
		this.location = location;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
