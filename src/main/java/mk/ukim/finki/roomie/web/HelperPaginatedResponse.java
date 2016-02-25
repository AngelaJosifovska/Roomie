package mk.ukim.finki.roomie.web;

import java.util.List;

public class HelperPaginatedResponse<T> {

	private Long total;
	private Integer per_page;
	private Integer current_page;
	private Integer last_page;	
	private List<T> data;

	
	public HelperPaginatedResponse(Long total,int per_page,
			Integer current_page, List<T> data) {
		super();
		this.total = total;
		this.per_page = per_page;
		this.current_page = current_page;
		if(current_page > 1){
			this.last_page = current_page - 1;
		}else{
			this.last_page=1;
		}
		
		this.data = data;
	}


	public HelperPaginatedResponse(List<T> data) {
		super();
		this.data = data;
	}

	
	public Long getTotal() {
		return total;
	}


	public void setTotal(Long total) {
		this.total = total;
	}


	public Integer getPer_page() {
		return per_page;
	}


	public void setPer_page(Integer per_page) {
		this.per_page = per_page;
	}


	public Integer getCurrent_page() {
		return current_page;
	}


	public void setCurrent_page(Integer current_page) {
		this.current_page = current_page;
	}


	public Integer getLast_page() {
		return last_page;
	}


	public void setLast_page(Integer last_page) {
		this.last_page = last_page;
	}


	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
