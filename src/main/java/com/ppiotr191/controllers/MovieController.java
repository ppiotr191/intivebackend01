package com.ppiotr191.controllers;


import com.ppiotr191.entity.Actor;
import com.ppiotr191.entity.Movie;
import com.ppiotr191.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
public class MovieController{

    @Autowired
    private CrudRepository<Movie,Long> movieRepository;

    @Autowired
    private CrudRepository<Actor,Long> actorRepository;

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public Iterable<Movie> index()
    {
        return movieRepository.findAll();
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.GET)
    public Movie get(@PathVariable("id") long id) throws NotFoundException {
        Movie movie = movieRepository.findOne(id);
        if (movie == null){
            throw new NotFoundException("Movie");
        }
        return movieRepository.findOne(id);
    }
    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    public Movie create(@RequestBody Movie movie) {


        return movieRepository.save(movie);

    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.PUT)
    public Movie update(@PathVariable("id") long id, @RequestBody Movie movie) throws NotFoundException {
        Movie update = movieRepository.findOne(id);
        if (update == null) {
            throw new NotFoundException("Movie");
        }
        update.setName(movie.getName());
        update.setType(movie.getType());

        return movieRepository.save(update);

    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") long id) throws NotFoundException {
        Movie movie = movieRepository.findOne(id);

        if (movie == null){
            throw new NotFoundException("Movie");
        }
        movieRepository.delete(id);
    }


    @RequestMapping(value = "/movies/{id}/add_actor/{id_actor}", method = RequestMethod.POST)
    public Actor addActor(@PathVariable("id") long id, @PathVariable("id_actor") long idActor) throws NotFoundException {

        Actor actor = actorRepository.findOne(idActor);
        Movie movie = movieRepository.findOne(id);

        if (actor == null){
            throw new NotFoundException("Actor");
        }
        if (movie == null) {
            throw new NotFoundException("Movie");
        }

        Set<Actor> actors = movie.getActors();
        actors.add(actor);
        movie.setActors(actors);
        movieRepository.save(movie);

        return actor;


    }
}
