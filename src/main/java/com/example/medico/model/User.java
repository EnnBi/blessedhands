package com.example.medico.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="User")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	@JsonIgnore
	 @ManyToMany(cascade=CascadeType.ALL)
	  @JoinTable( 
	        name = "User_Roles", 
	        joinColumns =@JoinColumn(name = "user_id", referencedColumnName = "id"), 
	        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")) 
	    private List<Role> roles;

	
	
	public User() {
	}

	
	public User(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;

	}


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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ ", email=" + email + ", image=" + image + ", enabled=" + enabled + ", roles=" + roles + "]";
	}

	
	
}
