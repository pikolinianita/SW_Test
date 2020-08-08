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
import org.springframework.web.reactive.function.client.WebClient;
import static pl.sobczak.sptest.consumerhttp.SwapiUrls.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author piko
 */
@Component
public class WebClientHttpConsumer implements HttpConsumer {

    WebClient webClient;

    public WebClientHttpConsumer() {
        webClient = WebClient.create();

    }

    @Override
    public List<Planet> findPlanet(String name) {

        var resultList = new LinkedList<Planet>();
        var nextUrl = PLANET.queryWithNameUri(name);

        while (nextUrl != null) {
            var page = webClient.get().uri(nextUrl).retrieve().bodyToMono(PlanetPage.class).block();
            nextUrl = page.getNext();
            resultList.addAll(page.getResultList());
        }
        return resultList;
    }

    public List<Planet> findPlanetWithoutWhile(String name) {
        //var resultList = new LinkedList<Planet>();
       var nextUrl = PLANET.queryWithNameUri(name);
       var resultFlux = webClient.get()
               .uri(nextUrl)
               .retrieve()
               .bodyToMono(PlanetPage.class)
               .flatMapIterable((page) -> transformer(page));
        
       return resultFlux.toStream().collect(Collectors.toList());
    }

    private List<Planet> transformer(PlanetPage page) {
        var resultList = new LinkedList<Planet>();
        resultList.addAll(page.resultList);
        if(page.getNext()!=null) {
            resultList.addAll(findPlanetWithoutWhile(page.getNext()));
        }
        return resultList; 
    }
    
    
    @Override
    public List<People> findPeople(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Film> findFilms(Collection<String> listOfIds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
