/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.consumerhttp;

import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author Lukasz Sobczak
 */
@Getter
@ToString
public class Planet {
        
    private final String name;

    private final String swapiId;

    public Planet(String name, String swapiId) {
        this.name = name;
        this.swapiId = swapiId;
    }

}
