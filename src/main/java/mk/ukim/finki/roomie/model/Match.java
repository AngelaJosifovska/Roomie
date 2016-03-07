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
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   
	@Column(nullable = false, columnDefinition = "tinyint(1) default false")
	private boolean interested = true;
   
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, insertable = true, updatable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Date created_at;
   
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, insertable = true, updatable = true, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Date updated_at;
    
    @JsonIgnore
	@ManyToOne(optional = false, targetEntity = User.class)
    @JoinColumn(name = "from_user", nullable = false, referencedColumnName = "id")
    private User from_user;
    
	@ManyToOne(optional = false, targetEntity = User.class)
    @JoinColumn(name = "to_user", nullable = false, referencedColumnName = "id")
    private User to_user;
   
    @PrePersist
    public void onCreate(){
    	this.created_at = new Date();
    	this.updated_at = new Date();
    }
    
    @PreUpdate
    public void onPersist(){
    	this.updated_at = new Date();
    }

	public Match() {
		super();
	}

	public Match(boolean interested, User from_user, User to_user) {
		super();
		this.interested = interested;
		this.from_user = from_user;
		this.to_user = to_user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isInterested() {
		return interested;
	}

	public void setInterested(boolean interested) {
		this.interested = interested;
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

	public User getFrom_user() {
		return from_user;
	}

	public void setFrom_user(User from_user) {
		this.from_user = from_user;
	}

	public User getTo_user() {
		return to_user;
	}

	public void setTo_user(User to_user) {
		this.to_user = to_user;
	}

	@Override
	public String toString() {
		return "Match [id=" + id + ", interested=" + interested
				+ ", from_user=" + from_user + ", to_user=" + to_user + "]";
	}
	
	
}
