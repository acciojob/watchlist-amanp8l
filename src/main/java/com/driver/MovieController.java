package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("movies")
public class MovieController {
    @Autowired

    MovieService movieService;
    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        String response= movieService.addMovie(movie);
        if(response.equals("movie added successfully!")){
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(response,HttpStatus.ALREADY_REPORTED);
    }
    @PostMapping("/add-director")
    public ResponseEntity  addDirector(@RequestBody Director director){
        String response= movieService.addDirector(director);
        if(response.equals("Director Added successFully!")){
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(response,HttpStatus.ALREADY_REPORTED);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movie") String movie, @RequestParam("director") String director){
        String response= movieService.addMovieDirectorPair(movie,director);
        if(response.equals("added successfully!")){
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String directirName){
        return new ResponseEntity<>(movieService.getMovieByName(directirName),HttpStatus.FOUND);
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String name){
        return new ResponseEntity<>(movieService.getDirectorByName(name),HttpStatus.FOUND);
    }

    @GetMapping("s/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String directorName){
        return new ResponseEntity<>(movieService.getMoviesByDirectorName(directorName),HttpStatus.FOUND);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity findAllMovies(){
        return new ResponseEntity<>(movieService.findAllMovies(),HttpStatus.FOUND);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam String director){
        return new ResponseEntity<>(movieService.deleteDirectorByName(director),HttpStatus.OK);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        return new ResponseEntity<>(movieService.deleteDirectorByName(),HttpStatus.OK);
    }

}
