/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.service;

import lombok.extern.apachecommons.CommonsLog;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import pl.sobczak.sptest.domain.ReportTestFactory;
import pl.sobczak.sptest.domain.repository.ReportRepository;

/**
 *
 * @author piko
 */
@CommonsLog
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class DefaultReadDeleteServiceTest {

    @Autowired
    DefaultReadDeleteService service;

    @Autowired
    ReportRepository rp;

        
    @Test
    @DisplayName("Test read service with one record")
    public void testServiceOne() {

        var report = new ReportTestFactory().lukeOnTatooine();
        rp.save(report);
    
        var result = service.getOne(1L);
       
        assertThat(result).as("luke, tatooine, 1").hasNoNullFieldsOrProperties();
        assertThat(result.getResult()).as("Original Trilogy with Luke").hasSize(3);
    }
    
    @Test
    @DisplayName("Test read service : get one record out of two")
    public void testServiceTwoReports() {
        
        final var reportTestFactory = new ReportTestFactory();
        var report = reportTestFactory.lukeAndWanOnTatooine();
        var report2 = reportTestFactory.LeiaOnAlderaan();
        rp.save(report);
        rp.save(report2);
        
        var result = service.getOne(1L);
        
       assertThat(result).as("luke & Wan, tatooine, 1").hasNoNullFieldsOrProperties();
       assertThat(result.getResult()).as("Original Trilogy with Luke").hasSize(4)
               .allMatch(line-> line.getPlanet_name().equals("Tatooine"))
               .allMatch(line-> line.getCharacter_name().equals("Luke S")
                       ||line.getCharacter_name().equals("Obi-Wan K"));
    }
}
