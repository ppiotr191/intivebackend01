package com.ppiotr191.controllers;

import com.ppiotr191.entity.Actor;
import com.ppiotr191.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;


import java.util.Set;

@RestController
public class ActorController{

    @Autowired
    private CrudRepository<Actor,Long> actorRepository;

    @RequestMapping(value = "/actor", method = RequestMethod.GET)
    public Iterable<Actor> index()
    {
        return actorRepository.findAll();
    }

    @RequestMapping(value = "/actor/{id}", method = RequestMethod.GET)
    public Actor get(@PathVariable("id") long id)
    {
        return actorRepository.findOne(id);
    }
    @RequestMapping(value = "/actor", method = RequestMethod.POST)
    public Actor create(@RequestBody Actor actor) {
        return actorRepository.save(actor);
    }

    @RequestMapping(value = "/actor/{id}", method = RequestMethod.PUT)
    public Actor update(@PathVariable("id") long id, @RequestBody Actor actor) {
        Actor update = actorRepository.findOne(id);
        update.setFirstName(actor.getFirstName());
        update.setLastName(actor.getLastName());

        return actorRepository.save(update);
    }

    @RequestMapping(value = "/actor{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") long id) {
        actorRepository.delete(id);
    }

}