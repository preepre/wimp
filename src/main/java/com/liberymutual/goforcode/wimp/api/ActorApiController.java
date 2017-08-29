package com.liberymutual.goforcode.wimp.api;

import java.util.Date;
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
import com.liberymutual.goforcode.wimp.repositories.ActorRepository;

@RestController
@RequestMapping("/api/actors")
public class ActorApiController {
	
	private ActorRepository actorRepo;
	
	public ActorApiController(ActorRepository actorRepo) {
		this.actorRepo = actorRepo;
		
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
	
	
	@GetMapping("{id}")
	public Actor getOne(@PathVariable long id) throws ActorNotFoundException {
		Actor actor = actorRepo.findOne(id);
		if(actor == null) {
			throw new ActorNotFoundException();
			
		}
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
