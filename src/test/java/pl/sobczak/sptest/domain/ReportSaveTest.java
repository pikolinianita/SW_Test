/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.domain;

import java.util.Set;
import lombok.extern.apachecommons.CommonsLog;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sobczak.sptest.domain.repository.ReportRepository;
import static org.assertj.core.api.Assertions.*;
import org.springframework.test.annotation.DirtiesContext;

/**
 *
 * @author Lukasz Sobczak
 */
@CommonsLog
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
class ReportSaveTest {

    @Autowired
    ReportRepository repository;

    Report report;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        log.info("=============== " + testInfo.getDisplayName() + " =============== ");
    }

    @AfterEach
    void TearDown(TestInfo testInfo) {
        log.info("=====Tear Down: " + testInfo.getDisplayName() + " =============== ");
    }

    @Test
    void saveOne() {
        var moviesSet = Set.of(
                new Movie().setName("Hope").setSwapiId(10L),
                new Movie().setName("Strikes").setSwapiId(20L));
        var luke = new Hero().setName("Luke").setSwapiId(10L).setMovies(moviesSet);
        report = new Report(10L);
        report.setRequest(new SwRequest().setHeroName("Luke").setHeroPlanet("Tatooine"))
                .setHeroes(Set.of(luke));

        report.save(repository);

        assertThat(repository.count()).as("reports").isEqualTo(1L);
        assertThat(repository.countHeroes()).as("heroes").isEqualTo(1L);
        assertThat(repository.countMovies()).isEqualTo(2L);

    }

    @Test
    void saveMany() {
        var moviesSet = Set.of(
                new Movie().setName("Hope").setSwapiId(1L),
                new Movie().setName("Strikes").setSwapiId(2L));
        var luke = new Hero().setName("Luke").setSwapiId(1L).setMovies(moviesSet);
        var leia = new Hero().setName("Leia").setSwapiId(2L).setMovies(moviesSet);

        report = new Report(1L);
        report.setRequest(new SwRequest().setHeroName("Luke").setHeroPlanet("Tatooine"))
                .setHeroes(Set.of(luke));

        report.save(repository);

        var report2 = new Report(2L);
        report2.setRequest(new SwRequest().setHeroName("Leia").setHeroPlanet("Alderaan"))
                .setHeroes(Set.of(leia));

        report2.save(repository);

        var report3 = new Report(3L);
        report3.setRequest(new SwRequest().setHeroName("X").setHeroPlanet("Y"))
                .setHeroes(Set.of(leia, luke));

        report3.save(repository);

        assertThat(repository.count()).isEqualTo(3L);
        assertThat(repository.countHeroes()).isEqualTo(2L);
        assertThat(repository.countMovies()).isEqualTo(2L);
    }

    @Test
    void testUpdate() {

        var hope = new Movie().setName("Hope").setSwapiId(1L);
        var strike = new Movie().setName("Strikes").setSwapiId(2L);
        var luke = new Hero().setName("Luke").setSwapiId(1L).setMovies(Set.of(hope));
        var leia = new Hero().setName("Leia").setSwapiId(2L).setMovies(Set.of(strike));

        report = new Report(1L);
        report.setRequest(new SwRequest().setHeroName("Luke").setHeroPlanet("Tatooine"))
                .setHeroes(Set.of(luke));

        report.save(repository);

        report.setRequest(new SwRequest().setHeroName("L").setHeroPlanet("NoNe"))
                .setHeroes(Set.of(leia));

        report.save(repository);

        assertThat(repository.count()).isEqualTo(1L);
        assertThat(repository.countHeroes()).isEqualTo(2L);
        assertThat(repository.countMovies()).isEqualTo(2L);
    }

}
