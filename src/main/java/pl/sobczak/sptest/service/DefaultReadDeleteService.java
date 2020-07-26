/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.service;

import pl.sobczak.sptest.service.interfac.SwapiRead;
import pl.sobczak.sptest.service.interfac.SwapiDelete;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.sobczak.sptest.domain.FakeReportDTO;
import pl.sobczak.sptest.domain.ReportDTO;
import pl.sobczak.sptest.domain.repository.ReportRepository;

/**
 *
 * @author piko
 */
@Component
public class DefaultReadDeleteService implements SwapiDelete, SwapiRead {

    @Autowired
    ReportRepository repo;
    
    
    @Override
    public FakeReportDTO getFakeOne(Long id) {
        return new FakeReportDTO("Fake Report with id " + id);
    }
    @Override
    public ReportDTO getOne(Long id) {
        var result = repo.getReportDTOHeader(id);
        var lines = repo.getReportLinesWithReportID(id);
        result.setResult(lines);
        return result ;
    }

    @Override
    public boolean deleteOne(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ReportDTO> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
