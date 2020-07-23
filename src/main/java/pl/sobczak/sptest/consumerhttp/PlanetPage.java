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
import lombok.Setter;

/**
 *
 * @author piko
 */

public class PlanetPage {
    
     private String next;

    private String count;

    List<Planet> resultList = new LinkedList<>();

    
    @JsonProperty("next")
    public void withHttpsFix(String str) {
        if (str != null) {
            next = str.replace("http://", "https://");
        }
    }

    @JsonProperty("results")
    public void setTheRes(List<Map<String, Object>> result) {

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
