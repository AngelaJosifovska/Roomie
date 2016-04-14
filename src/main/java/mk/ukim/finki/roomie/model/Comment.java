package mk.ukim.finki.roomie.model;

import java.text.SimpleDateFormat;
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
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name="comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	
	@Column(nullable=false,length=1000)
	private String body;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, insertable=true, updatable=false, columnDefinition = "default CURRENT_TIMESTAMP")
	private Date created_at;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, insertable=true, updatable=true, columnDefinition = "default CURRENT_TIMESTAMP")
	private Date updated_at;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="from_user")
	private User user;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="on_rental")
	private RentalUnit rentalUnit;
	
	@Column(insertable=false,updatable=false)
	private Integer from_user;
	
	@Column(insertable=false,updatable=false)
	private Integer on_rental;
	
	@PrePersist
	public void onCreate(){
		this.created_at = new Date();
		this.updated_at = new Date();
	}
	
	@PreUpdate
	public void onPersist(){
		this.updated_at = new Date();
	}
	
	public void setCreated_at(Date date){
		this.created_at = date;
	}
	
	public String getCreated_at() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@JsonProperty
	public User getUser() {
		return user;
	}
	@JsonIgnore
	public void setUser(User user) {
		this.user = user;
	}
	
	@JsonIgnore
	public void setRentalUnit(RentalUnit rentalUnit) {
		this.rentalUnit = rentalUnit;
	}
		
	@JsonIgnore
	public RentalUnit getRentalUnit() {
		return rentalUnit;
	}

	public Integer getFrom_user() {
		return from_user;
	}

	public void setFrom_user(Integer from_user) {
		this.from_user = from_user;
	}

	public Integer getOn_rental() {
		return on_rental;
	}

	public void setOn_rental(Integer on_rental) {
		this.on_rental = on_rental;
	}
	
	
}
