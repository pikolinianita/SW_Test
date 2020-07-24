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
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import pl.sobczak.sptest.consumerhttp.Film;
import pl.sobczak.sptest.consumerhttp.HttpConsumer;
import pl.sobczak.sptest.consumerhttp.People;
import pl.sobczak.sptest.consumerhttp.Planet;
import pl.sobczak.sptest.domain.repository.ReportRepository;
import pl.sobczak.sptest.exceptions.RestExceptions;

/**
 *
 * @author piko
 */
@Accessors(chain = true)
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
@Entity
public class Report {

    @Id
    Long reportId;

    @Embedded
    SwRequest request;

    @ManyToMany
    Set<Hero> heroes;

    public Report(Long id) {
        reportId = id;
    }

    public Report performQuery(SwRequest req, HttpConsumer httpConsumer) {
        request = req;
        var planets = httpConsumer.findPlanet(request.getHeroPlanet());
        var people = httpConsumer.findPeople(request.getHeroName());
        validateAndPrune(planets, people);  //changes arguments
        var films = httpConsumer.findFilms(getSetOfFilmId(people));
        heroes = composeHeroes(people, films);
        return this;
    }

    //arguments are modified
    void validateAndPrune(List<Planet> planets, List<People> people) {
        String errorMsg = "";
        planets.removeIf(p -> !p.getName().equalsIgnoreCase(request.getHeroPlanet()));
        if (planets.size() != 1) {
            errorMsg += "There is no Planet with that name!/n";
        }
        if (people.isEmpty()) {
            errorMsg += "There is no Hero with such name!/n";
        }
        if (!errorMsg.isEmpty()) {
            throw new RestExceptions.HttpResourceNotFound(errorMsg);
        }

        var queryPlanet = planets.get(0);
        people.removeIf(ppl -> !ppl.getHomePlanetId().equalsIgnoreCase(queryPlanet.getSwapiId()));

        if (people.isEmpty()) {
            throw new RestExceptions.HttpQueryNoHits("There is no hero with name containing " + request.getHeroName()
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //return this;
    }
}
