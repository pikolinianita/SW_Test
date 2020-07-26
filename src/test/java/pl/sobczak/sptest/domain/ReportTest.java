/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.domain;

import static java.util.Collections.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.extern.apachecommons.CommonsLog;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sobczak.sptest.consumerhttp.DefaultHttpConsumer;
import pl.sobczak.sptest.exceptions.RestExceptions;

/**
 *
 * @author Lukasz Sobczak
 */
@CommonsLog
@ExtendWith(MockitoExtension.class)
class ReportTest {

    Report report;

    SwapiVirtualDB sw;

    @Mock
    DefaultHttpConsumer httpConsumer;

    ReportTest() {
        this.sw = new SwapiVirtualDB();
    }

    @BeforeEach
    void setUp(TestInfo testInfo) {
        log.info("=============== " + testInfo.getDisplayName() + " =============== ");
        report = new Report(1L);
    }

    @Test
    @DisplayName("Happy path: Luke on Tatooine")
    void testHappyPath() {
        SoftAssertions softly = new SoftAssertions();

        var request = new SwRequest().setHeroName("Luke").setHeroPlanet("Tatooine");
        var planetList = new LinkedList<>(List.of(sw.tatooine()));
        var peopleList = new LinkedList<>(List.of(sw.luke()));
        var filmSet = Set.of(sw.hope(), sw.strike(), sw.roJedi());

        when(httpConsumer.findPeople(any())).thenReturn(peopleList);
        when(httpConsumer.findPlanet(any())).thenReturn(planetList);
        when(httpConsumer.findFilms(Set.of("1", "2", "3"))).thenReturn(filmSet);

        report.performQuery(request, httpConsumer);
        
        softly.assertThat(report).as("sWRequest Copied")
                .hasFieldOrPropertyWithValue("planetId", 1L)
                .extracting("request")
                .hasFieldOrPropertyWithValue("heroName", "Luke");

        softly.assertThat(report.getHeroes()).as("Only Luke")
                .hasSize(1)
                .matches(set -> set.iterator().next().getName().equals("Luke Skywalker"));

        softly.assertThat(report.getHeroes().iterator().next().getMovies()).as("three movies")
                .hasSize(3)
                .anyMatch(mov -> mov.getName().equals("A New Hope"))
                .anyMatch(mov -> mov.getName().equals("Imperium Strikes Back"))
                .anyMatch(mov -> mov.getName().equals("Return of Jedi"));

        softly.assertAll();
    }

    @Test
    void testNoHits() {
        var request = new SwRequest();
        when(httpConsumer.findPeople(any())).thenReturn(EMPTY_LIST);
        when(httpConsumer.findPlanet(any())).thenReturn(EMPTY_LIST);

        Throwable thrown = catchThrowable(() -> report.performQuery(request, httpConsumer));

        assertThat(thrown).as("No Planet No Hero")
                .isInstanceOf(RestExceptions.HttpResourceNotFound.class)
                .hasMessageContainingAll("no Planet with that name", "no Hero with such name");

    }

    @Test
    void testNoPlanetIsHero() {
        var request = new SwRequest();
        var heroList = List.of(sw.luke());
        when(httpConsumer.findPeople(any())).thenReturn(heroList);
        when(httpConsumer.findPlanet(any())).thenReturn(EMPTY_LIST);

        Throwable thrown = catchThrowable(() -> report.performQuery(request, httpConsumer));

        assertThat(thrown).as("No Planet Is Hero")
                .isInstanceOf(RestExceptions.HttpResourceNotFound.class)
                .hasMessageContaining("no Planet with that name")
                .hasMessageNotContaining("no Hero with such name");

    }

    @Test
    void testNoHeroIsPlanet() {
        var request = new SwRequest().setHeroPlanet("Tatooine");
        var planetList = new LinkedList<>(List.of(sw.tatooine()));

        when(httpConsumer.findPeople(any())).thenReturn(EMPTY_LIST);
        when(httpConsumer.findPlanet(any())).thenReturn(planetList);

        Throwable thrown = catchThrowable(() -> report.performQuery(request, httpConsumer));

        assertThat(thrown).as("Is Planet No Hero")
                .isInstanceOf(RestExceptions.HttpResourceNotFound.class)
                .hasMessageContaining("no Hero with such name")
                .hasMessageNotContaining("no Planet with that name");

    }

    @Test
    void testHeroPlanetMismatch() {
        var request = new SwRequest().setHeroPlanet("Tatooine");
        var planetList = new LinkedList<>(List.of(sw.tatooine()));
        var peopleList = new LinkedList<>(List.of(sw.leia()));
        when(httpConsumer.findPeople(any())).thenReturn(peopleList);
        when(httpConsumer.findPlanet(any())).thenReturn(planetList);

        Throwable thrown = catchThrowable(() -> report.performQuery(request, httpConsumer));

        assertThat(thrown).as("Planet Hero Mismatch")
                .isInstanceOf(RestExceptions.HttpQueryNoHits.class)
                .hasMessageContaining("There is no hero with name containing");

    }

}
