/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.consumerhttp;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Lukasz Sobczak
 */
@Configuration
@CommonsLog
public class HttpConsumerConfiguration {

    @Bean
    public RestTemplate getRestTemplate() {
        var restTemplate = new RestTemplate();
        log.info("Rest Template Bean Created");
        return restTemplate;
    }

}
