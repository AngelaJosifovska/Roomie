package mk.ukim.finki.roomie.helper;

public class CategoryFrequency {
	
	private String category;
	
	private Long num;

	public CategoryFrequency(String category, Long num) {
		super();
		this.category = category;
		this.num = num;
	}

	public CategoryFrequency() {
		super();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}
	
	

}
