/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sobczak.sptest.domain.Report;

/**
 *
 * @author piko
 */
public interface ReportRepository extends JpaRepository<Report, Long>{
    
}
