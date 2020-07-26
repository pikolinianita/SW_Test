/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.domain;

import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author piko
 */
@ToString
@Getter
public class ReportLineDTO {

    Long film_id;
    String film_name;
    Long character_id;
    String character_name;
    Long planet_id;
    String planet_name;

    public ReportLineDTO(String film_name, String character_name) {
        this.film_name = film_name;
        this.character_name = character_name;
    }
    
    public ReportLineDTO(String planetName, Long planetId, String filmName, Long filmID, String characterName, Long characterID) {
        this.planet_name = planetName;
        this.planet_id = planetId;
        this.film_name = filmName;
        this.character_name = characterName;
        this.film_id = filmID;
        this.character_id = characterID;
    }
    
    
}
