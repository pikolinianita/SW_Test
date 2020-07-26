/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.domain;

import java.util.HashSet;
import java.util.Set;
import static javax.persistence.CascadeType.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import pl.sobczak.sptest.consumerhttp.People;


/**
 *
 * @author piko
 */
@Accessors(chain = true)
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
@NoArgsConstructor
@ToString
@Entity
class Hero {
    
    @Id
    Long swapiId;
          
    @ManyToMany(cascade = {MERGE, PERSIST})
    Set<Movie> movies;
    
    String name;

    Hero(People ppl) {
        swapiId = Long.parseLong(ppl.getSwapiId());
        name = ppl.getName();
        movies = new HashSet<>();
    }

    Hero addMovie(Movie movie) {
        movies.add(movie);
        return this;
    }
    
    }
