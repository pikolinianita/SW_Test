/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.consumerhttp;

import java.util.List;
import lombok.extern.apachecommons.CommonsLog;
import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author piko
 */
@CommonsLog
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HttpConsumerConfiguration.class, HttpConsumer.class})
public class HttpConsumerTest {

    @Autowired
    HttpConsumer consumer;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        log.info("=============== " + testInfo.getDisplayName() + " =============== ");
    }

    @Test
    @DisplayName("Find New Hope")
    public void findFilmTest() {

        var film = consumer.findFilms(List.of("1"));

        assertThat(film).as("A New Hope").hasSize(1)
                .allMatch(f -> f.getName().equalsIgnoreCase("A New Hope"), "Title match")
                .allMatch(f -> f.getSwapiId().equals("1"), "Id match");
    }

    @Test
    @DisplayName("Find Original Trilogy")
    public void findOriginalTrilogyTest() {

        var titles = List.of("A New Hope", "Return of the Jedi", "The Empire Strikes Back");
        Condition<Film> inOriginalTrilogy = new Condition<>(f -> titles.contains(f.getName()), "in Original Trilogy");
        Condition<Film> withCorrectSwapiIds = new Condition<>(f -> List.of("1", "2", "3").contains(f.getSwapiId()), "has right Ids");

        var films = consumer.findFilms(List.of("1", "2", "3"));

        assertThat(films).as("Trilogy")
                .hasSize(3)
                .are(inOriginalTrilogy)
                .are(withCorrectSwapiIds);
    }

    @Test
    @DisplayName("Find Tatooine")
    public void findOnePlanet() {

        var planets = consumer.findPlanet("Tatooine");

        assertThat(planets).as("Tattoine only").hasSize(1)
                .allMatch(p -> p.getName().equals("Tatooine"))
                .allMatch(p -> p.getSwapiId().equals("1"));
    }

    @Test
    @DisplayName("Find many planets")
    public void findManyPlanets() {

        var planets = consumer.findPlanet("a");

        assertThat(planets).as("at least 39 planets")
                .hasSizeGreaterThan(38)
                .allMatch(p -> p.getName().toLowerCase().contains("a"));
    }

    @Test
    @DisplayName("Find Leia")
    public void findOnePerson(){
        
        var people = consumer.findPeople("Leia Organa");
              
        assertThat(people).as("Leia only")
                .allMatch(p -> p.getName().equals("Leia Organa"))
                .allMatch(p -> p.getSwapiId().equals("5"))
                .allMatch(p -> p.getHomePlanetId().equals("2"))
                .allMatch(p-> p.getFilmIds().containsAll(List.of("1", "2", "3", "6")));
    }
    
    @Test
    @DisplayName("Find Many People")
    public void findManyPersons(){
        
        var people = consumer.findPeople("o");
        
        assertThat(people).as("many people with o in name")
                .hasSizeGreaterThan(39)
                .allMatch(p -> p.getName().toLowerCase().contains("o"))
                .allMatch(p -> p.getFilmIds().isEmpty()==false);
    }
    
}
