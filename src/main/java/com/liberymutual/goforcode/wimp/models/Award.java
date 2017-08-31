package com.liberymutual.goforcode.wimp.models;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

@JsonIdentityInfo(
	generator=ObjectIdGenerators.PropertyGenerator.class,
	property="id"
)
@Entity
public class Award {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(length=500, nullable=false)
	private String title;
	
	@Column(length=200, nullable=false)
	private String organization;
	
	@Column(nullable=true)
	private int year; 
	
	//Added relationship
//	@JsonIgnore
/*	@ManyToMany(mappedBy="actors")
	private List<Movie> movies;*/
	
	@ManyToOne
	private Actor actor;
	
	public Award() {}
	
	public Award(String title, String organization) {
		this.title = title;
		this.organization = organization;
	}
	
	public Award(String title, String organization, int year) {
		this.title = title;
		this.organization = organization;
		this.year = year;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}
	

	 
}
