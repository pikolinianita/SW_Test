/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.controller;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sobczak.sptest.service.SwapiService;

/**
 *
 * @author piko
 */
@CommonsLog
@RestController
@RequestMapping(path = "/report")
public class SwapiController {

    @Autowired
    SwapiService service;

    @GetMapping("/akeita")
    public Object getAkeita() {
        log.info("Akeita invoked");
        return service.akeita();
    }

    @GetMapping("/{id}")
    public ReportDTO getOne(@PathVariable Long id) {
        log.info("Get invokedwith id: " + id);
        return service.getOne(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        log.info("Delete invoked with id: " + id);
        return null;
    }

}
