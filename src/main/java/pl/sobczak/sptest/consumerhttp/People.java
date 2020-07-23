/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.consumerhttp;

import java.util.Set;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author piko
 */
@Getter
@ToString
public class People {
    
    private final String name;

    private final String swapiId;

    private final Set<String> filmIds;
    
    private final String homePlanetId;


    public People(String name, String swapiId, Set<String> filmIds, String planetId) {
        this.name = name;
        this.swapiId = swapiId;
        this.filmIds = filmIds;
        this.homePlanetId = planetId;
    }
    
    
}
