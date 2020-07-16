/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.service;

import org.springframework.stereotype.Component;
import pl.sobczak.sptest.controller.ReportDTO;
import pl.sobczak.sptest.exceptions.RestExceptions;

/**
 *
 * @author piko
 */
@Component
public class MyService {

    public void akeita() {
        throw new RestExceptions.AkeitaException("No Cofee Today!");
    }

    public ReportDTO getOne(Long id) {
        return new ReportDTO("Fake Report with id " + String.valueOf(id)); 
    }
    
}
