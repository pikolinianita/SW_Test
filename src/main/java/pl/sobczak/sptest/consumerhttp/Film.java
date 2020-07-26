/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.consumerhttp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 *
 * @author Lukasz Sobczak
 */

@Accessors(chain = true)
@Data
@NoArgsConstructor
public class Film {
    
    @JsonProperty("title")
    private String name;

    private String swapiId;

    @JsonProperty("url")
    private void getId(String url) {
        var tmp = url.substring(0, url.length() - 1);
        swapiId = tmp.substring(tmp.lastIndexOf('/') + 1);
    }

    //For Testing
    public Film(String name, String swapiId) {
        this.name = name;
        this.swapiId = swapiId;
    }
   
}
