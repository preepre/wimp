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

import com.liberymutual.goforcode.wimp.models.Actor;
import com.liberymutual.goforcode.wimp.models.ActorWithMovies;
import com.liberymutual.goforcode.wimp.models.Award;
import com.liberymutual.goforcode.wimp.models.Movie;
import com.liberymutual.goforcode.wimp.repositories.ActorRepository;
import com.liberymutual.goforcode.wimp.repositories.AwardRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/actors")
@Api(description="Use this to get and create actors and add awards to actors")
public class ActorApiController {
	
	private ActorRepository actorRepo;
	private AwardRepository awardRepo;
	
	public ActorApiController(ActorRepository actorRepo, AwardRepository awardRepo) {
		this.actorRepo = actorRepo;
		this.awardRepo = awardRepo;
		
		
		actorRepo.save(new Actor("Brad", "Pitt"));
		actorRepo.save(new Actor("Jennifer", "Anniston"));
		actorRepo.save(new Actor("Angelina" ,"Jolie"));
		actorRepo.save(new Actor("Sofia", "Vergara"));
		actorRepo.save(new Actor("Penelope", "Cruz"));
	}
	
	
	@GetMapping("")
	public List<Actor> getAll(){
		return actorRepo.findAll();		
		
	}
	
	@ApiOperation(value="Find actor by ID", 
			notes="Try this because you want to see all the actors for a certain ID")
	@GetMapping("{id}")
	public Actor getOne(@PathVariable long id) throws ActorNotFoundException {
		Actor actor = actorRepo.findOne(id);
		if(actor == null) {
			throw new ActorNotFoundException();
			
		}/*
		ActorWithMovies newActor = new ActorWithMovies();
		newActor.setId(actor.getId());
		newActor.setActiveSinceYear(actor.getActiveSinceYear());
		newActor.setBirthDate(actor.getBirthDate());
		newActor.setMovies(actor.getMovies());
		newActor.setFirstName(actor.getFirstName());
		newActor.setLastName(actor.getLastName());
		return newActor;		*/
	
		return actor;
	}
	
	@PostMapping("")
	public Actor create(@RequestBody Actor actor) {
		//RequestBody - the entire body of the post is for a cereal object
		return actorRepo.save(actor);
	}
	
	
	@PutMapping("{id}")
	public Actor update(@RequestBody Actor actor, @PathVariable long id) {
		actor.setId(id);
		return actorRepo.save(actor);
	}
	
	@PostMapping("{actorId}/awards")
	public Actor associateAwards(@PathVariable long actorId, @RequestBody Award award) {
			
		Actor actor = actorRepo.findOne(actorId);
		award = awardRepo.findOne(award.getId());
		
		actor.addAward(award);
		actorRepo.save(actor);
		
		return actor;
		
	}
	
	@DeleteMapping("{id}")
	public Actor delete(@PathVariable long id) {
		try {
			Actor actor = actorRepo.findOne(id);
			actorRepo.delete(id);
			return actor;
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}
	


}
