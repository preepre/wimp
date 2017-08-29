package com.liberymutual.goforcode.wimp.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, length=300)
	private String title;
	
	private Date releaseDate;
	
	private Long budget;
	
	@Column(nullable=false, length=500)
	private String distributor;
	
	public Movie() {}
	
	public Movie(String title, String distributor) {
		this.title = title;
		this.distributor = distributor;
		
	}
	
	public Movie(String title, String distributor, Date releaseDate, Long budget) {
		this.title = title;
		this.distributor = distributor;
		this.releaseDate = releaseDate;
		this.budget = budget;
		
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

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Long getBudget() {
		return budget;
	}

	public void setBudget(Long budget) {
		this.budget = budget;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}
	
		
	

}
