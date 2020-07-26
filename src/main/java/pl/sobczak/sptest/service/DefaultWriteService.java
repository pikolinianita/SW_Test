/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.sobczak.sptest.consumerhttp.HttpConsumer;
import pl.sobczak.sptest.domain.Report;
import pl.sobczak.sptest.domain.SwRequest;
import pl.sobczak.sptest.domain.repository.ReportRepository;
import pl.sobczak.sptest.service.interfac.SwapiWrite;

/**
 *
 * @author piko
 */
@Component
public class DefaultWriteService implements SwapiWrite {

    @Autowired
    ReportRepository repo;
    
    @Autowired
    HttpConsumer httpConsumer;
    
    @Override
    public boolean createOrUpdate(Long id, SwRequest request) {
        
        new Report(id)
                .performQuery(request, httpConsumer)
                .save(repo);
        
        return true;
    }

}
