package mk.ukim.finki.roomie.helper;

import mk.ukim.finki.roomie.model.User;

public class PotentialMatch {

	private int potential_id;
	private Boolean interested_for_me;
	private User potential_user;

	public PotentialMatch() {
		super();
	}

	public PotentialMatch(int potential_id, Boolean interested_for_me,
			User potential_user) {
		super();
		this.potential_id = potential_id;
		this.interested_for_me = interested_for_me;
		this.potential_user = potential_user;
	}

	public PotentialMatch(int potential_id, Boolean interested_for_me) {
		super();
		this.potential_id = potential_id;
		this.interested_for_me = interested_for_me;
	}

	public int getPotential_id() {
		return potential_id;
	}

	public void setPotential_id(int potential_id) {
		this.potential_id = potential_id;
	}

	public Boolean getInterested_for_me() {
		return interested_for_me;
	}

	public void setInterested_for_me(Boolean interested_for_me) {
		this.interested_for_me = interested_for_me;
	}

	public User getPotential_user() {
		return potential_user;
	}

	public void setPotential_user(User potential_user) {
		this.potential_user = potential_user;
	}

	@Override
	public String toString() {
		return "PotentialMatch [potential_id=" + potential_id
				+ ", interested_for_me=" + interested_for_me
				+ ", potential_user=" + potential_user + "]";
	}
}
