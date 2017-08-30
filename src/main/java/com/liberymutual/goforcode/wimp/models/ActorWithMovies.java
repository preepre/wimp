package com.liberymutual.goforcode.wimp.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActorWithMovies extends Actor {
	
	
	//Set this as a JSON Property
	@JsonProperty
	public List<Movie> noReallyMovies() {
		return getMovies();
	}

}
