/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.service.interfac;

import java.util.List;
import pl.sobczak.sptest.controller.ReportDTO;

/**
 *
 * @author piko
 */
public interface SwapiRead {

    ReportDTO getOne(Long id);

    List<ReportDTO> getAll();

}
