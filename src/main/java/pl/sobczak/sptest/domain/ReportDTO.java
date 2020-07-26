package pl.sobczak.sptest.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author piko
 */
@Accessors(chain=true)
@Setter
@Getter
@ToString
public class ReportDTO {
    
Long report_id;
 
String query_criteria_character_phrase;

String query_criteria_planet_name;

List<ReportLineDTO> result;

    public ReportDTO(Long report_id, String heroName, String planet) {
        this.report_id = report_id;
        this.query_criteria_character_phrase = heroName;
        this.query_criteria_planet_name = planet;
    }

}
