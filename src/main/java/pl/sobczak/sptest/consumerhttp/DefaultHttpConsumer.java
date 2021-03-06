/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.consumerhttp;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import static pl.sobczak.sptest.consumerhttp.SwapiUrls.*;

/**
 *
 * @author Lukasz Sobczak
 */

@Component
public class DefaultHttpConsumer implements HttpConsumer {

    private final RestTemplate restTemplate;

    public DefaultHttpConsumer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Planet> findPlanet(String name) {
        var resultList = new LinkedList<Planet>();
        var nextUrl = PLANET.queryWithNameUri(name);

        while (nextUrl != null) {            
            var page = restTemplate.getForObject(nextUrl, PlanetPage.class);
            nextUrl = page.getNext();
            resultList.addAll(page.getResultList());           
        }
        return resultList;
    }

    @Override
    public List<People> findPeople(String name) {
        var resultList = new LinkedList<People>();
        var nextUrl = PEOPLE.queryWithNameUri(name);

        while (nextUrl != null) {            
            var page = restTemplate.getForObject(nextUrl, PeoplePage.class);
            nextUrl = page.getNext();
            resultList.addAll(page.getResultList());           
        }        
        return resultList;
    }

    @Override
    public Set<Film> findFilms(Collection<String> listOfIds) {
        return listOfIds.stream()
                .distinct()
                .map(filmId -> restTemplate.getForObject(FILMS.findById(filmId), Film.class))
                .collect(Collectors.toSet());
    }

}
