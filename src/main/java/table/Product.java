package table;

import java.sql.Date;

public class Product {
		
	private long id;
	private String name;
	private Date expdate;
	private String description;
	
	public Product(long id, String name, Date expdate, String description) {
		super();
		this.id = id;
		this.name = name;
		this.expdate = expdate;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getExpdate() {
		return expdate;
	}

	public void setExpdate(Date expdate) {
		this.expdate = expdate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
