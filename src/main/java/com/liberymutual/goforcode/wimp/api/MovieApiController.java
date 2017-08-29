package com.liberymutual.goforcode.wimp.api;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liberymutual.goforcode.wimp.api.MovieNotFoundException;
import com.liberymutual.goforcode.wimp.models.Movie;
import com.liberymutual.goforcode.wimp.repositories.MovieRepository;

@RestController
@RequestMapping("/api/movies")
public class MovieApiController {

	private MovieRepository movieRepo;

	public MovieApiController(MovieRepository movieRepo) {
		this.movieRepo = movieRepo;

		movieRepo.save(new Movie("Iron Man", "Marvel"));
		movieRepo.save(new Movie("The Avengers", "Marvel"));
		movieRepo.save(new Movie("Pocahontas", "Disney"));
		movieRepo.save(new Movie("Harry Potter and the Goblet of Fire", "WB"));

	}
	
	@GetMapping("")
	public List<Movie> getAll(){
		return movieRepo.findAll();		
		
	}
	
	@GetMapping("{id}")
	public Movie getOne(@PathVariable long id) throws MovieNotFoundException {
		Movie movie = movieRepo.findOne(id);
		if(movie == null) {
			throw new MovieNotFoundException();
			
		}
		return movie;		
	}
	
	@PostMapping("")
	public Movie create(@RequestBody Movie movie) {
		//RequestBody - the entire body of the post is for a cereal object
		return movieRepo.save(movie);
	}
	
	@PutMapping("{id}")
	public Movie update(@RequestBody Movie movie, @PathVariable long id) {
		movie.setId(id);
		return movieRepo.save(movie);
	}
	
	@DeleteMapping("{id}")
	public Movie delete(@PathVariable long id) {
		try {
			Movie movie = movieRepo.findOne(id);
			movieRepo.delete(id);
			return movie;
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}
	

}
