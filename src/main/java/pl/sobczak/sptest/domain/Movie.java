/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import pl.sobczak.sptest.consumerhttp.Film;

/**
 *
 * @author piko
 */
@Accessors(chain = true)
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
@Entity
class Movie {
    
    @Id
    Long swapiId;
    
    String name;
    
    Movie (Film film){
        swapiId = Long.parseLong(film.getSwapiId());
        name = film.getName();
    }
}
