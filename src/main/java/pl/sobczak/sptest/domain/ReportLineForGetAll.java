/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.domain;

import lombok.Getter;

/**
 *
 * @author piko
 */
@Getter
public class ReportLineForGetAll {
      
    Long report_Id;
    
    Long film_id;
    String film_name;
    
    Long character_id;    
    String character_name;
    
    Long planet_id;
    String planet_name;

   
    
    public ReportLineForGetAll(Long reportId, String planetName, Long planetId, String filmName, Long filmID, String characterName, Long characterID) {
        this.report_Id = reportId;
        this.planet_name = planetName;
        this.planet_id = planetId;
        this.film_name = filmName;
        this.character_name = characterName;
        this.film_id = filmID;
        this.character_id = characterID;
    }
    
}
