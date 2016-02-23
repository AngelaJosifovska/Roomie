package mk.ukim.finki.roomie.web;

import java.util.List;

/**
 * A temporary helper class that wraps the JSON responses
 * @author Viktor
 *
 */
public class HelperResponseWrapper<T> {
	private List<T> data;

	public HelperResponseWrapper(List<T> data) {
		super();
		this.data = data;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
