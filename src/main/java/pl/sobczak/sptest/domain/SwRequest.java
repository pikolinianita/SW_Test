/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * simple Value class for transfer query from REST service to HttpConsumer
 * module and part of Report
 *
 * @author Lukasz Sobczak
 */
@Accessors(chain = true)
@Getter()
@Setter()
@NoArgsConstructor
@ToString
@Embeddable
public class SwRequest {

    @JsonProperty("query_criteria_character_phrase")
    String heroName;

    @JsonProperty("query_criteria_planet_name")
    String heroPlanet;

}
