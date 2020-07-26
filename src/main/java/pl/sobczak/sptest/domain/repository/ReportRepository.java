/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.domain.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.sobczak.sptest.domain.Report;
import pl.sobczak.sptest.domain.ReportDTO;
import pl.sobczak.sptest.domain.ReportLineDTO;

/**
 *
 * @author piko
 */
public interface ReportRepository extends JpaRepository<Report, Long>{
    
    
    @Query("Select new pl.sobczak.sptest.domain.ReportLineDTO(m.name,h.name) from Hero h join h.movies m ") 
    public List<ReportLineDTO> getReportLines(Long id);
    
    @Query("Select new pl.sobczak.sptest.domain.ReportLineDTO(r.request.heroPlanet, m.name, m.swapiId, h.name, h.swapiId) from Report r join r.heroes h join h.movies m where r.reportId = :id") 
    public List<ReportLineDTO> getReportLinesWithReportID(Long id);
    
    @Query("Select new pl.sobczak.sptest.domain.ReportDTO (r.reportId, r.request.heroName, r.request.heroPlanet ) from Report r Where r.reportId = :id")
    public ReportDTO getReportDTOHeader(Long id);
    
    @Query("select count(h) from Hero h")
    public Long countHeroes();
    
    @Query("select count(m) from Movie m")
    public Long countMovies();
    
  }
