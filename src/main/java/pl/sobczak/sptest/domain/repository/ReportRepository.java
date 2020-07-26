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
import org.springframework.transaction.annotation.Transactional;
import pl.sobczak.sptest.domain.Report;
import pl.sobczak.sptest.domain.ReportDTO;
import pl.sobczak.sptest.domain.ReportLineDTO;
import pl.sobczak.sptest.domain.ReportLineForGetAll;

/**
 *
 * @author Lukasz Sobczak
 */
public interface ReportRepository extends JpaRepository<Report, Long>{
    
    @Query("Select new pl.sobczak.sptest.domain.ReportLineDTO(r.request.heroPlanet, r.planetId, m.name, m.swapiId, h.name, h.swapiId) from Report r join r.heroes h join h.movies m where r.reportId = :id") 
    public List<ReportLineDTO> getReportLinesFromReport(Long id);
    
    @Query("Select new pl.sobczak.sptest.domain.ReportLineForGetAll(r.reportId, r.request.heroPlanet, r.planetId, m.name, m.swapiId, h.name, h.swapiId) from Report r join r.heroes h join h.movies m" ) 
    public List<ReportLineForGetAll> getAllReportLines();
    
    @Query("Select new pl.sobczak.sptest.domain.ReportDTO (r.reportId, r.request.heroName, r.request.heroPlanet ) from Report r Where r.reportId = :id")
    public ReportDTO getReportDTOHeader(Long id);
    
    @Query("Select new pl.sobczak.sptest.domain.ReportDTO (r.reportId, r.request.heroName, r.request.heroPlanet ) from Report r ")
    public List<ReportDTO> getAllReportDTOHeaders();
    
    @Modifying
     @Transactional
   @Query( value = "delete from HERO_MOVIES hm where hm.HERO_SWAPI_ID in (select h.SWAPI_ID  from hero h where h.SWAPI_ID not in (select HEROES_SWAPI_ID from REPORT_HEROES  )); delete from hero h where h.SWAPI_ID not in (select HERO_SWAPI_ID  from HERO_MOVIES  ); delete from movie m where m.SWAPI_ID not in ( select MOVIES_SWAPI_ID from HERO_MOVIES )",
           nativeQuery = true)
   public void deleteOrphans();
    
    @Query("select count(h) from Hero h")
    public Long countHeroes();
    
    @Query("select count(m) from Movie m")
    public Long countMovies();
    
  }
