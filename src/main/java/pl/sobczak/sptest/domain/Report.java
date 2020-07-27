/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.domain;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import static java.util.stream.Collectors.*;
import static javax.persistence.CascadeType.*;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.apachecommons.CommonsLog;
import pl.sobczak.sptest.consumerhttp.Film;
import pl.sobczak.sptest.consumerhttp.HttpConsumer;
import pl.sobczak.sptest.consumerhttp.People;
import pl.sobczak.sptest.consumerhttp.Planet;
import pl.sobczak.sptest.domain.repository.ReportRepository;
import pl.sobczak.sptest.exceptions.SwapiRestExceptions;

/**
 * aggregate root
 * @author Lukasz Sobczak
 */
@CommonsLog
@Accessors(chain = true)
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
@ToString
@NoArgsConstructor
@Entity
public class Report {

    @Id
    Long reportId;
    
    Long planetId;

    @Embedded
    SwRequest request;

    @ManyToMany(cascade = {MERGE, PERSIST})
    Set<Hero> heroes;

    public Report(Long id) {
        reportId = id;
    }

    public Report performQuery(SwRequest req, HttpConsumer httpConsumer) {
        request = req;
        var planets = httpConsumer.findPlanet(request.getHeroPlanet());
        var people = httpConsumer.findPeople(request.getHeroName());
        validateAndPrune(planets, people);  //function changes arguments
        var films = httpConsumer.findFilms(getSetOfFilmId(people));
        //there is exactly one planet or exception was thrown earlier
        planetId = Long.parseLong(planets.get(0).getSwapiId());
        heroes = composeHeroes(people, films);
        return this;
    }

    //arguments are modified
    //Validation rules:
    //Planet name has to be exact hit (not case sensitive)
    //Hero name must contain search phrase and must be born on query planet
    void validateAndPrune(List<Planet> planets, List<People> people) {
        String errorMsg = "";
        // in case planet name is substring of other planet name
        planets.removeIf(p -> !p.getName().equalsIgnoreCase(request.getHeroPlanet()));
        if (planets.size() != 1) {
            errorMsg += "There is no Planet with that name!\n";
        }
        if (people.isEmpty()) {
            errorMsg += "There is no Hero with such name!\n";
        }
        if (!errorMsg.isEmpty()) {
            throw new SwapiRestExceptions.HttpResourceNotFound(errorMsg);
        }

        var queryPlanet = planets.get(0);
        people.removeIf(ppl -> !ppl.getHomePlanetId().equalsIgnoreCase(queryPlanet.getSwapiId()));

        if (people.isEmpty()) {
            throw new SwapiRestExceptions.HttpQueryNoHits("There is no hero with name containing " + request.getHeroName()
                    + " and born on " + request.getHeroPlanet());
        }
    }

    private Set<String> getSetOfFilmId(List<People> people) {
        return people.stream()
                .flatMap(p -> p.getFilmIds().stream())
                .collect(toSet());
    }

    private Set<Hero> composeHeroes(List<People> people, Set<Film> films) {
        var movieMap = films.stream()
                .map(Movie::new)
                .collect(toMap(movie -> String.valueOf(movie.getSwapiId()),
                        Function.identity()));

        return people.stream()
                .map(ppl -> {
                    var hero = new Hero(ppl);
                    ppl.getFilmIds().forEach(id -> hero.addMovie(movieMap.get(id)));
                    return hero;
                })
                .collect(toSet());
    }

    public Report save(ReportRepository repo) {
      return repo.save(this);        
    }
    
   
}
