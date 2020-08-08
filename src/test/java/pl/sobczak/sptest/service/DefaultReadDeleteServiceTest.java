/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.service;

import lombok.extern.apachecommons.CommonsLog;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import pl.sobczak.sptest.domain.HelperReportTestFactory;
import pl.sobczak.sptest.domain.repository.ReportRepository;
import pl.sobczak.sptest.exceptions.SwapiRestExceptions;

/**
 *
 * @author Lukasz Sobczak
 */
@CommonsLog
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
class DefaultReadDeleteServiceTest {

    @Autowired
    DefaultReadDeleteService service;

    @Autowired
    ReportRepository repository;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        log.info("=============== " + testInfo.getDisplayName() + " =============== ");
    }

    @Test
    @DisplayName("Test read service with one record")
    void testServiceOne() {
        var report = new HelperReportTestFactory().lukeOnTatooine();
        repository.save(report);

        var result = service.getOne(1L);

        assertThat(result).as("luke, tatooine, 1").hasNoNullFieldsOrProperties();
        assertThat(result.getResult()).as("Original Trilogy with Luke").hasSize(3);
    }

    @Test
    @DisplayName("Test read service : get one record out of two")
    void testServiceTwoReports() {
        final var reportTestFactory = new HelperReportTestFactory();
        var report = reportTestFactory.lukeAndWanOnTatooine();
        var report2 = reportTestFactory.LeiaOnAlderaan();
        repository.save(report);
        repository.save(report2);

        var result = service.getOne(1L);

        assertThat(result).as("luke & Wan, tatooine, 1").hasNoNullFieldsOrProperties();
        assertThat(result.getResult()).as("Original Trilogy with Luke").hasSize(4)
                .allMatch(line -> line.getPlanet_name().equals("Tatooine"))
                .allMatch(line -> line.getCharacter_name().equals("Luke S")
                || line.getCharacter_name().equals("Obi-Wan K"));
    }

    @Test
    @DisplayName("Test read service : get all of 2 records")
    void testServiceTwoReportsReortAll() {
        final var reportTestFactory = new HelperReportTestFactory();
        var report = reportTestFactory.lukeAndWanOnTatooine();
        var report2 = reportTestFactory.LeiaOnAlderaan();
        repository.save(report);
        repository.save(report2);

        var result = service.getAll();

        assertThat(result).as("Luke, Wan, and Leia")
                .hasSize(2);

        assertThat(result.get(0).getResult()).as("Luke & Wan")
                .hasSize(4)
                .allMatch(line -> line.getPlanet_name().equals("Tatooine"))
                .allMatch(line -> line.getCharacter_name().equals("Luke S")
                || line.getCharacter_name().equals("Obi-Wan K"));

        assertThat(result.get(1).getResult()).as("Leia")
                .hasSize(3)
                .allMatch(line -> line.getPlanet_name().equals("Alderaan"))
                .allMatch(line -> line.getCharacter_name().equals("Leia O"));
    }

    @Test
    @DisplayName("delete Orphans")
    void testDeleteOrphans() {
        final var reportTestFactory = new HelperReportTestFactory();
        var report = reportTestFactory.lukeAndWanOnTatooine();
        var report2 = reportTestFactory.LeiaOnAlderaan();
        repository.save(report);
        repository.save(report2);
        repository.deleteById(1L);

        assertThat(repository.countHeroes()).isEqualTo(3L);

        repository.deleteOrphans();

        assertThat(repository.countHeroes()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Get - record no exist")
    void testNoRecordGet() {

        var thrown = catchThrowable(() -> service.getOne(134L));

        assertThat(thrown).as("No Record Excception")
                .isInstanceOf(SwapiRestExceptions.RecordNotFound.class)
                .hasMessageContaining("No record with id: ");
    }

    @Test
    @DisplayName("Delete - record no exist")
    void testNoRecordDelete() {

        var thrown = catchThrowable(() -> service.deleteOne(134L));

        assertThat(thrown).as("No Record Excception")
                .isInstanceOf(SwapiRestExceptions.RecordNotFound.class)
                .hasMessageContaining("No record with id: ");
    }
}
