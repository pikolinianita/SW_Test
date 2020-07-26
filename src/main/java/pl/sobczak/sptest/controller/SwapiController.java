/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.controller;

import pl.sobczak.sptest.domain.FakeReportDTO;
import java.util.List;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.sobczak.sptest.domain.ReportDTO;
import pl.sobczak.sptest.exceptions.RestExceptions;
import pl.sobczak.sptest.domain.SwRequest;
import pl.sobczak.sptest.service.interfac.SwapiDelete;
import pl.sobczak.sptest.service.interfac.SwapiRead;
import pl.sobczak.sptest.service.interfac.SwapiWrite;
import pl.sobczak.sptest.service.DefaultReadDeleteService;

/**
 *
 * @author piko
 */
@CommonsLog
@RestController
@RequestMapping(path = "/report")
public class SwapiController {

    @Autowired
    SwapiRead readService;

    @Autowired
    SwapiWrite writeService;

    @Autowired
    SwapiDelete deleteService;

    @GetMapping("/akeita")
    public Object getAkeita() {
        log.info("Akeita invoked");
        throw new RestExceptions.AkeitaException("No Cofee Today!");
    }
 
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReportDTO getOne(@PathVariable Long id) {
        log.info("Get invokedwith id: " + id);
        return readService.getOne(id);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<ReportDTO> getAll() {
        log.info("getAll invoked");
        return readService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        log.info("Delete invoked with id: " + id);
        deleteService.deleteOne(id);
        return null;
    }

    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        log.info("DeleteAll invoked");
       deleteService.deleteAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createOrUpdate(@PathVariable Long id, @RequestBody SwRequest input) {
        log.info("put invoked");
        writeService.createOrUpdate(id, input);
    }

}
