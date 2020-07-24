/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.domain;

import java.util.LinkedList;
import lombok.extern.apachecommons.CommonsLog;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sobczak.sptest.consumerhttp.DefaultHttpConsumer;
import pl.sobczak.sptest.exceptions.RestExceptions;

/**
 *
 * @author piko
 */
@CommonsLog
@ExtendWith(MockitoExtension.class)
public class ReportTest {
    
    Report report;
    
    @Mock
    DefaultHttpConsumer httpConsumer;
    
    @BeforeEach
    public void setUp(TestInfo testInfo){
        log.info("=============== " + testInfo.getDisplayName() + " =============== ");
        report = new Report(1L);
    }

    @Test
    public void testNoHits() {
        var request = new SwRequest();
        when(httpConsumer.findPeople(any())).thenReturn(new LinkedList<>());
        when(httpConsumer.findPlanet(any())).thenReturn(new LinkedList<>());
        
        Throwable thrown = catchThrowable( ()-> report.performQuery(request, httpConsumer));
        
        assertThat(thrown).as("No Planet No Hero")
                .isInstanceOf(RestExceptions.HttpResourceNotFound.class)
                .hasMessageContainingAll("no Planet with that name", "no Hero with such name");
        
    }
    
   
    
}
