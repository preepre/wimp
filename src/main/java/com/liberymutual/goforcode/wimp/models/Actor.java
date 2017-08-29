package com.liberymutual.goforcode.wimp.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Actor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(length=75, nullable=false)
	private String firstName;
	
	@Column(length=75)
	private String lastName;
	
	private Long activeSinceYear;
	
	private Date birthDate;
	
	public Actor() {}
	
	public Actor(String firstName) {
		this.firstName = firstName;		
	}
	
	public Actor(String firstName, String lastName) {
		this.firstName = firstName;	
		this.lastName = lastName;
	}
	
	public Actor(String firstName, String lastName, Long activeSinceYear, Date birthDate) {
		this.firstName = firstName;		
		this.lastName = lastName;
		this.activeSinceYear = activeSinceYear;
		this.birthDate = birthDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Long getActiveSinceYear() {
		return activeSinceYear;
	}

	public void setActiveSinceYear(Long activeSinceYear) {
		this.activeSinceYear = activeSinceYear;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	 
}
