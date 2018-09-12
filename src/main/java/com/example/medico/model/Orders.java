package com.example.medico.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	@Column(name="Order_ID")
	String orderId;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="order")
	List<CartItem> cartItems;
	
	@JsonIgnore
	@JoinColumn(name="User_ID")
	@ManyToOne(fetch=FetchType.EAGER)
	User placedUser;
	
	String prescriptionPhoto;
	
	@JoinColumn(name="Address_ID")
	@ManyToOne(cascade=CascadeType.ALL)
	Address address;

	@DateTimeFormat(pattern="dd-MM-yyyy HH:mm")
	Date placedDate;
	
	@Column(name="Status")
	String status;
	
	@Column(name="Total_Price")
	Double total;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
	public User getPlacedUser() {
		return placedUser;
	}

	public void setPlacedUser(User placedUser) {
		this.placedUser = placedUser;
	}

	public Date getPlacedDate() {
		return placedDate;
	}

	public void setPlacedDate(Date placedDate) {
		this.placedDate = placedDate;
	}

	public String getPrescriptionPhoto() {
		return prescriptionPhoto;
	}

	public void setPrescriptionPhoto(String prescriptionPhoto) {
		this.prescriptionPhoto = prescriptionPhoto;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	
}
