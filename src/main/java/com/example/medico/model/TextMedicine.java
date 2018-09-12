package com.example.medico.model;	
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity	
public class TextMedicine {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	@Column(name="Name")
	String name;
	
	@Column(name="Company")
	String company;
	
	@Column(name="Dosage")
	String dosage;

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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	@Override
	public String toString() {
		return "TextMedicine [id=" + id + ", name=" + name + ", company=" + company + ", dosage=" + dosage + "]";
	}
	
	
}
