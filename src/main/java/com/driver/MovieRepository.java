package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {
    HashMap<String,Movie> Movie_Db=new HashMap<>();
    HashMap<String,Director> Director_Db=new HashMap<>();
    HashMap<String, List<String>> Director_movies_Db=new HashMap<>();
    public String addMovie(Movie movie){
        String movie_name= movie.getName();
        if(!Movie_Db.containsKey(movie_name)) {
            Movie_Db.put(movie_name, movie);
            return "movie added successfully!";
        }
        return "movie already exist!";
    }
    public String addDirector(Director director){
        String director_name=director.getName();
        if(!Director_Db.containsKey(director_name)){
            Director_Db.put(director_name,director);
            return "Director Added successFully!";
        }
        return "Director already exist!";
    }

    public String addMovieDirectorPair(String movieName, String directorName){
        if(!Director_Db.containsKey(directorName))
            return "Director not exist!";
        if(!Movie_Db.containsKey(movieName))
            return "Movie not exist!";

        if(Director_movies_Db.containsKey(directorName)){
            Director_movies_Db.get(directorName).add(movieName);
            return "added successfully!";
        }
        List<String> list=new ArrayList<>();
        list.add(movieName);
        Director_movies_Db.put(directorName,list);
        return "added successfully!";
    }

    public Movie getMovieByName(String movieName){
        return Movie_Db.get(movieName);
    }

    public Director getDirectorByName(String directorName){
        return Director_Db.get(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName){
        return Director_movies_Db.get(directorName);
    }

    public List<String> findAllMovies(){
        List<String> list=new ArrayList<>();
        for(String x:Movie_Db.keySet()){
            list.add(x);
        }
        return list;
    }

    public String deleteDirectorByName(String directorName){
        if (Director_Db.containsKey(directorName)){
            Director_Db.remove(directorName);
        } else {
            return "director not exist!";
        }
        List<String> list=new ArrayList<>();
        list=Director_movies_Db.get(directorName);
        for(String movieName:list){
            Movie_Db.remove(movieName);
        }
        Director_Db.remove(directorName);
        return "deleted!";
    }

    public String deleteAllDirectors(){
        for (String director:Director_movies_Db.keySet()){
            deleteDirectorByName(director);
        }
        Director_Db.clear();
        return "deleted!";

    }
}
