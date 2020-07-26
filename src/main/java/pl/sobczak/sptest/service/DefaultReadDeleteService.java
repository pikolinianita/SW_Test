/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.service;

import pl.sobczak.sptest.service.interfac.SwapiRead;
import pl.sobczak.sptest.service.interfac.SwapiDelete;
import java.util.List;
import org.springframework.stereotype.Component;
import pl.sobczak.sptest.controller.ReportDTO;

/**
 *
 * @author piko
 */
@Component
public class DefaultReadDeleteService implements SwapiDelete, SwapiRead {

    @Override
    public ReportDTO getOne(Long id) {
        return new ReportDTO("Fake Report with id " + id);
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
