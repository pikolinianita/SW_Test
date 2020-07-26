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
import org.springframework.stereotype.Component;
import pl.sobczak.sptest.domain.ReportDTO;
import pl.sobczak.sptest.domain.ReportLineDTO;
import pl.sobczak.sptest.domain.ReportLineForGetAll;
import pl.sobczak.sptest.domain.repository.ReportRepository;

/**
 *
 * @author piko
 */
@Component
public class DefaultReadDeleteService implements SwapiDelete, SwapiRead {

    private final ReportRepository repo;

    public DefaultReadDeleteService(ReportRepository repo) {
        this.repo = repo;
    }

    @Override
    public ReportDTO getOne(Long id) {
        var result = repo.getReportDTOHeader(id);
        var lines = repo.getReportLinesFromReport(id);
        result.setResult(lines);
        return result;
    }

    @Override
    public boolean deleteOne(Long id) {
        repo.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAll() {
        repo.deleteAll();
        return true;
    }

    @Override
    public List<ReportDTO> getAll() {
        var result = repo.getAllReportDTOHeaders();
        var lines = repo.getAllReportLines();
        var linesMap = lines.stream()
                .collect(groupingBy(ReportLineForGetAll::getReport_Id,
                        mapping(ReportLineDTO::new, toList())));
        result.forEach(reportDTO -> reportDTO.setResult(linesMap.get(reportDTO.getReport_id())));
        return result;
    }

}
