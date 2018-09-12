package com.example.medico.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Medicine")
public class Medicine {

	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	@Column(name="Name")
	String name;
	
	@Column(name="Description")
	String description;
	
	@JoinColumn(name="Company_ID")
	@ManyToOne(fetch=FetchType.EAGER)
	Company company;
	
	@JoinColumn(name="Unit_ID")
	@ManyToOne(fetch=FetchType.EAGER)
	Unit unit;
	
	@Column(name="Dosage")
	String dosage;
	
	@Column(name="Price")
	Double price;
	
	@JoinColumn(name="Type_ID")
	@ManyToOne(fetch=FetchType.EAGER)
	Type type;
	
	@JsonIgnore
	@JoinColumn(name="Operator_ID")
	@ManyToOne(fetch=FetchType.EAGER)
	User operator;
	
	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@ElementCollection
	@CollectionTable(name="Medicine_Images")
	List<String> images= new ArrayList<>();

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	@Override
	public String toString() {
		return "Medicine [id=" + id + ", name=" + name + ", description=" + description + ", company=" + company
				+ ", unit=" + unit + ", dosage=" + dosage + ", price=" + price + ", type=" + type + "]";
	}
	
	
}
