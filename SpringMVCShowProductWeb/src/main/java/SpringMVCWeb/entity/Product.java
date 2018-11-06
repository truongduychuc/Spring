 package SpringMVCWeb.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Products")
public class Product implements Serializable {
	private static final long serialVersionUID=-1000119078147252957L; // use need having this property for class implemented Serializable 
	
	@Id
	@Column(name="Code",length=20,nullable=false)
	private String code;
	
	@Column(name="Name",length=255,nullable=false)
	private String name;
	
	@Column(name="Price",nullable=false)
	private double price;
	@Temporal(TemporalType.TIMESTAMP)  // @Temporal using with column which have data types such as: date, time, datetime...
	@Column(name="Create_Date",nullable=false)
	private Date createDate;
	
	@Lob   //use to annotate that this column correspond with a lob data type column in sql
	@Column(name="Image",length=Integer.MAX_VALUE,nullable=false)
	private byte[] image;
	
	@Column(name="Description",nullable=true)
	private String description;
	
	public Product() {		
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date date) {
		this.createDate = date;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
