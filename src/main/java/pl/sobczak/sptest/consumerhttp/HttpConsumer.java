/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.consumerhttp;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import static pl.sobczak.sptest.consumerhttp.SwapiUrls.*;

/**
 *
 * @author piko
 */
@CommonsLog
@Component
public class HttpConsumer implements HttpConsumerI {

    private final RestTemplate restTemplate;

    public HttpConsumer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Planet> findPlanet(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<People> findPeople(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Film> findFilms(Collection<String> listOfIds) {
        return listOfIds.stream()
                .distinct()
                .map(filmId -> restTemplate.getForObject(FILMS.findById(filmId), Film.class))
                .collect(Collectors.toSet());
    }

}
