/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.service;

import pl.sobczak.sptest.service.interfac.SwapiRead;
import pl.sobczak.sptest.service.interfac.SwapiDelete;
import java.util.List;
import static java.util.stream.Collectors.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.sobczak.sptest.domain.ReportDTO;
import pl.sobczak.sptest.domain.ReportLineDTO;
import pl.sobczak.sptest.domain.ReportLineForGetAll;
import pl.sobczak.sptest.domain.repository.ReportRepository;
import pl.sobczak.sptest.exceptions.SwapiRestExceptions;

/**
 *
 * @author piko
 */
@Component
public class DefaultReadDeleteService implements SwapiDelete, SwapiRead {

    private final ReportRepository repository;

    public DefaultReadDeleteService(ReportRepository repo) {
        this.repository = repo;
    }

    @Override
    public ReportDTO getOne(Long id) {
        try {
            var result = repository.getReportDTOHeader(id);
            var lines = repository.getReportLinesFromReport(id);
            result.setResult(lines);
            return result;
        } catch (NullPointerException ex) {
            throw new SwapiRestExceptions.RecordNotFound("No record with id: " + id);
        }

    }

    @Override
    @Transactional
    public boolean deleteOne(Long id) {
        try {
            repository.deleteById(id);
            repository.deleteOrphans();
            return true;
        } catch (EmptyResultDataAccessException ex) {
            throw new SwapiRestExceptions.RecordNotFound("No record with id: " + id);
        }
    }

    @Override
    @Transactional
    public boolean deleteAll() {
        repository.deleteAll();
        repository.deleteOrphans();
        return true;
    }

    @Override
    public List<ReportDTO> getAll() {
        var result = repository.getAllReportDTOHeaders();
        var lines = repository.getAllReportLines();
        var linesMap = lines.stream()
                .collect(groupingBy(ReportLineForGetAll::getReport_Id,
                        mapping(ReportLineDTO::new, toList())));
        result.forEach(reportDTO -> reportDTO.setResult(linesMap.get(reportDTO.getReport_id())));
        return result;
    }

}
