package com.liberymutual.goforcode.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.liberymutual.goforcode.wimp.models.Actor;
import com.liberymutual.goforcode.wimp.models.Award;
import com.liberymutual.goforcode.wimp.repositories.ActorRepository;
import com.liberymutual.goforcode.wimp.repositories.AwardRepository;
import static org.mockito.Mockito.*;

public class ActorApiControllerTests {
	
	private ActorRepository actorRepo;
	private AwardRepository awardRepo;
	private ActorApiController controller;

	
	@Before
	public void setUp() {
		actorRepo = mock(ActorRepository.class);
		awardRepo = mock(AwardRepository.class);
		controller = new ActorApiController(actorRepo, awardRepo);
	}
	
	//Testing getAll()
	@Test
	public void test_getAll_returns_all_Movies_from_repo() {
		
		//Arrange
		ArrayList<Actor> actors = new ArrayList<Actor>();
		actors.add(new Actor());
		actors.add(new Actor());
		
		when(actorRepo.findAll()).thenReturn(actors);
		
		//Act
		List<Actor> actual = controller.getAll();
		
		//Assert
		assertThat(actual.size()).isEqualTo(2);
		assertThat(actual.get(0)).isSameAs(actors.get(0));
		verify(actorRepo).findAll();
			
	}
	
	//Testing getOne()
	@Test
	public void test_getOne_returns_Actor_from_repo() throws ActorNotFoundException {
		//Arrange
		Actor actor = new Actor();
		when(actorRepo.findOne(4L)).thenReturn(actor);
		
		//Act
		Actor actual = controller.getOne(4L);
		
		//Assert
		assertThat(actual).isSameAs(actor);
		verify(actorRepo).findOne(4L);		
		
	}
	
	//Testing getOne() is null
	@Test
	public void test_get_one_throws_ActorNotFoundException_when_no_actor_returned_from_repo() {
		try {
			controller.getOne(2);
			fail("The Controller did not throw the ActorNotFoundException");
			
		} catch (ActorNotFoundException e){
			
		}
	}
	
	//Testing create()
	@Test
	public void test_create_returns_saved_Actor_from_repo() {
		//Arrange
		Actor actor = new Actor();
		when(actorRepo.save(actor)).thenReturn(actor);
		
		//Act
		Actor actual = controller.create(actor);
		
		//Assert
		assertThat(actual).isSameAs(actor);
		
	}
	
	//Testing update()
	@Test
	public void test_update_returns_saved_Actor_from_repo() {
		//Arrange
		Actor actor = new Actor();
		actor.setId(4L);
		when(actorRepo.save(actor)).thenReturn(actor);
		
		Actor actual = controller.create(actor);
		actual.setId(4L);
		
		//Act
		actual = controller.update(actual, 10L);
				
		//Assert
		assertThat(actual.getId()).isEqualTo(10L);
		
	}
	
	//Testing delete()
	@Test
	public void test_delete_does_not_return_actor_from_repo() {
	
		//Arrange
		Actor actor = new Actor();
		when(actorRepo.findOne(3L)).thenReturn(actor);
		
		//Act
		Actor actual = controller.delete(3L);
		
		//Assert
		assertThat(actor).isSameAs(actual);
		verify(actorRepo).delete(3L);
		verify(actorRepo).findOne(3L);

	}
	
	
	//Testing delete() throws exception when null
	@Test
	public void test_delete_if_null_throws_exception() {
		
		//Arrange
		when(actorRepo.findOne(8L)).thenThrow(new EmptyResultDataAccessException(0));
		
		//Act
		Actor actual = controller.delete(8L);
		
		//Assert
		assertThat(actual).isNull();
		verify(actorRepo).findOne(8L);
		
	}
	
	//Testing associateAwards
	@Test
	public void test_associateAward_returns_associated_awards_for_given_actor() {
		//Arrange
		Actor actor = new Actor();
		when(actorRepo.findOne(4L)).thenReturn(actor);
		
		Award award = new Award();
		when(awardRepo.findOne(4L)).thenReturn(award);
		
		Actor actual = controller.create(actor);
		
		//Act
		actual = controller.associateAwards(4L, award);
		
		//Assert
		assertThat(actor.getAwards()).isEqualTo(actual.getAwards());
		verify(actorRepo, times(2)).save(actual);
		verify(actorRepo).findOne(4L);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
