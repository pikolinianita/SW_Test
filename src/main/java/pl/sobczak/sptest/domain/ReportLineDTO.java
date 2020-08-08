/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.domain;

import lombok.Getter;
import lombok.ToString;

/**
 * names are not in Java convention, because they are converted to JSON
 *
 * @author Lukasz Sobczak
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

    public ReportLineDTO(String planetName, Long planetId, String filmName, Long filmID, String characterName, Long characterID) {
        this.planet_name = planetName;
        this.planet_id = planetId;
        this.film_name = filmName;
        this.character_name = characterName;
        this.film_id = filmID;
        this.character_id = characterID;
    }

    public ReportLineDTO(ReportLineForGetAll line) {
        this.planet_name = line.planet_name;
        this.planet_id = line.planet_id;
        this.film_name = line.film_name;
        this.character_name = line.character_name;
        this.film_id = line.film_id;
        this.character_id = line.character_id;
    }
}
