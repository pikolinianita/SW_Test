/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.domain;

import java.util.Set;
import pl.sobczak.sptest.consumerhttp.Film;
import pl.sobczak.sptest.consumerhttp.People;
import pl.sobczak.sptest.consumerhttp.Planet;

/**
 *
 * @author Lukasz Sobczak
 */
public class SwapiVirtualDB {
    
   public Film hope(){
        return new Film("A New Hope","1");
    }
    
   public Film strike(){
        return new Film("Imperium Strikes Back", "2");
    }
    
   public Film roJedi(){
        return new Film("Return of Jedi", "3");
}
    
    public People luke(){
        return new People("Luke Skywalker","1", Set.of("1","2","3"), "1"); 
    }
    
    public People leia(){
        return new People("Leia Organa","2", Set.of("1","2","3"), "2");
    }
    
    
    public People owen(){
        return new People("Owen Lars","3", Set.of("1"),"1");
    }
    
    public People wampa(){
        return new People("Wampa","4", Set.of("2"), "3");
    }
    
    public People ewok(){
        return new People("Generic Ewok","5",Set.of("3"),"3");
    }
    
    public Planet tatooine(){
        return new Planet("Tatooine", "1");
    }
    
    public Planet alderaan(){
        return new Planet("Alderaan", "2");
    }
   
    public Planet other(){
        return new Planet("Other", "3");
    }
}
