package com.liberymutual.goforcode.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.liberymutual.goforcode.wimp.models.Actor;
import com.liberymutual.goforcode.wimp.models.Movie;
import com.liberymutual.goforcode.wimp.repositories.ActorRepository;
import com.liberymutual.goforcode.wimp.repositories.MovieRepository;

public class MovieApiControllerTests {
	
	private MovieRepository movieRepo;
	private ActorRepository actorRepo;
	private MovieApiController controller;

	@Before
	public void setUp() {
		movieRepo = mock(MovieRepository.class);
		actorRepo = mock(ActorRepository.class);
		controller = new MovieApiController(movieRepo, actorRepo);
	}
	
	//Testing getAll()
	@Test
	public void test_getAll_returns_all_Movies_from_repo() {
		
		//Arrange
		ArrayList<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie());
		movies.add(new Movie());
		
		when(movieRepo.findAll()).thenReturn(movies);
		
		//Act
		List<Movie> actual = controller.getAll();
		
		//Assert
		assertThat(actual.size()).isEqualTo(2);
		assertThat(actual.get(0)).isSameAs(movies.get(0));
		verify(movieRepo).findAll();
	}
	
	//Testing getOne()
	@Test
	public void test_getOne_returns_Movie_from_repo() throws MovieNotFoundException {
		//Arrange
		Movie movie = new Movie();
		when(movieRepo.findOne(4L)).thenReturn(movie);
		
		//Act
		Movie actual = controller.getOne(4L);
		
		//Assert
		assertThat(actual).isSameAs(movie);
		verify(movieRepo).findOne(4L);
		
	}
	
	//Testing getOne() is null
	@Test
	public void test_getOne_throws_MovieNotFoundException_when_no_movie_returned_from_repo() {
		try {
			controller.getOne(2);
			fail("The Controller did not throw the MovieNotFoundException");
		} catch (MovieNotFoundException e) {
			
		}
	}
	
	//Testing create()
	@Test
	public void test_create_returns_saved_Movie_from_repo() {
		//Arrange
		Movie movie = new Movie();
		//movie.setId(4L);	
		when(movieRepo.save(movie)).thenReturn(movie);
		
		//Act
		Movie actual = controller.create(movie);
		
		//Assert
		assertThat(actual).isSameAs(movie);
		
	}
	
	//Testing update()
	@Test
	public void test_update_returns_saved_Movie_from_repo() {
		
		//Arrange
		Movie movie = new Movie();
		movie.setId(4L);	
		when(movieRepo.save(movie)).thenReturn(movie);
		
		Movie actual = controller.create(movie);
		actual.setId(4L);
		
		//Act
		actual = controller.update(actual, 10L);
		
		//Assert
		assertThat(actual.getId()).isEqualTo(10L);
		
	}
	
	//Testing delete()
	@Test
	public void test_delete_does_not_return_movie_from_repo() {
	
		//Arrange
		Movie movie = new Movie();
		when(movieRepo.findOne(3L)).thenReturn(movie);
		
		//Act
		Movie actual = controller.delete(3L);
		
		//Assert
		assertThat(movie).isSameAs(actual);
		verify(movieRepo).delete(3L);
		verify(movieRepo).findOne(3L);

	}
	
	//Testing delete() throws exception when null
	@Test
	public void test_delete_if_null_throws_exception() {
		
		//Arrange
		when(movieRepo.findOne(8L)).thenThrow(new EmptyResultDataAccessException(0));
		
		//Act
		Movie actual = controller.delete(8L);
		
		//Assert
		assertThat(actual).isNull();
		verify(movieRepo).findOne(8L);
		
	}
	
	

	
	//Testing associateAnActor()
	@Test
	public void test_associateAnActor_returns_associated_actor_for_given_movie() {
		//Arrange
		Movie movie = new Movie();
		when(movieRepo.findOne(4L)).thenReturn(movie);
		
		Actor actor = new Actor();
		when(actorRepo.findOne(4L)).thenReturn(actor);

		Movie actual = controller.create(movie);
		
		//Act	
		actual = controller.associateAnActor(4L, actor);		
		
		//Assert
		assertThat(movie.getActors()).isEqualTo(actual.getActors());
		verify(movieRepo, times(2)).save(actual);
		verify(movieRepo).findOne(4L);
		
		
	}
	

	
	

}
