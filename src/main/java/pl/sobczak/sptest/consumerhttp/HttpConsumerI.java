/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.consumerhttp;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 *
 * @author piko
 */
public interface HttpConsumerI {
    
    public List<Planet> findPlanet(String name);
    
    public List<People> findPeople(String name);
    
    public Set<Film> findFilms (Collection<String> listOfIds);
    
}
