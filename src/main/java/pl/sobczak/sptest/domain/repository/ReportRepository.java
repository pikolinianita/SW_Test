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
public interface ReportRepository extends JpaRepository<Report, Long> {

    //each object contain all data for output "result line", just put list into ReportDTO 
    @Query("Select new pl.sobczak.sptest.domain.ReportLineDTO(r.request.heroPlanet, r.planetId, m.name, m.swapiId, h.name, h.swapiId) from Report r join r.heroes h join h.movies m where r.reportId = :id")
    public List<ReportLineDTO> getReportLinesFromReport(Long id);

    //each object has all data for output "result line" AND reportID, so list can be grouped by reportID and assigned to correct report
    @Query("Select new pl.sobczak.sptest.domain.ReportLineForGetAll(r.reportId, r.request.heroPlanet, r.planetId, m.name, m.swapiId, h.name, h.swapiId) from Report r join r.heroes h join h.movies m")
    public List<ReportLineForGetAll> getAllReportLines();

    //base for single report, ReportLines has to be added
    @Query("Select new pl.sobczak.sptest.domain.ReportDTO (r.reportId, r.request.heroName, r.request.heroPlanet ) from Report r Where r.reportId = :id")
    public ReportDTO getReportDTOHeader(Long id);

    @Query("Select new pl.sobczak.sptest.domain.ReportDTO (r.reportId, r.request.heroName, r.request.heroPlanet ) from Report r ")
    public List<ReportDTO> getAllReportDTOHeaders();

    //Remove "orphaned" Heroes and Mocies (orphaned = not referenced by Reports ( or Heroes, respectively)
    //Contains three native queries, first removes mentiones of "orphaned" Heroes from Hero_Movies join table,
    // second and thirs removes "orphaned" Heroes and Movies, respectively
    //I consider this ugly hack, but I have no idea how to do it right
    @Modifying
    @Transactional
    @Query(value = "delete from HERO_MOVIES hm where hm.HERO_SWAPI_ID in (select h.SWAPI_ID  from Hero h where h.SWAPI_ID not in (select HEROES_SWAPI_ID from REPORT_HEROES  ));"
            + " delete from hero h where h.SWAPI_ID not in (select HERO_SWAPI_ID  from HERO_MOVIES  );"
            + "delete from Movie m where m.SWAPI_ID not in ( select MOVIES_SWAPI_ID from HERO_MOVIES )",
            nativeQuery = true)
    public void deleteOrphans();

    //for Testing
    @Query("select count(h) from Hero h")
    public Long countHeroes();

    //for Testing
    @Query("select count(m) from Movie m")
    public Long countMovies();

}
