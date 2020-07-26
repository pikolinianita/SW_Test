/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.consumerhttp;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Lukasz Sobczak
 */

public class PlanetPage {
    
     private String next;

    List<Planet> resultList = new LinkedList<>();

    // As I write this some "HATEOAS-like" links inside swapi starts with http, but db changed 
    // to https, so I have to add 's'
    @JsonProperty("next")
    public void withHttpsFix(String str) {
        if (str != null) {
            next = str.replace("http://", "https://");
        }
    }

    @JsonProperty("results")
    public void preparePlanetList(List<Map<String, Object>> result) {

        resultList.addAll(
                result.stream()
                        .map(record
                                -> new Planet((String) record.get("name"),
                                getId((String) record.get("url")))
                        )
                        .collect(Collectors.toCollection(LinkedList::new)));
    }
    
    private String getId(String url) {
        var tmp = url.substring(0, url.length() - 1);
        return tmp.substring(tmp.lastIndexOf('/') + 1);
    }
    
 List<Planet> getResultList() {
        return resultList;
    }
 
    String getNext() {
        return next;
    }

    
}
