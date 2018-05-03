package com.example.medico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="User")
public class User {

	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	@Column(name="First_Name")
	String firstName;
	
	@Column(name="Last_Name")
	String lastName;
	
	@Column(name="Password")
	String password;
	
	@Email
	@Column(name="Email")
	String email;
	
	@Column(name="Image")
	String image;
	
	@Column(name="Enabled")
	boolean enabled;
	
	@JoinColumn(name="Role_ID")
	@ManyToOne(fetch=FetchType.EAGER)
	Role role;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
}
