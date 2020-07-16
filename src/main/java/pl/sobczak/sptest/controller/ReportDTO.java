/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.controller;

import lombok.Getter;

/**
 *
 * @author piko
 */
@Getter
public class ReportDTO {
    
    String name;

    public ReportDTO() {
        name = "killer";
    }

    public ReportDTO(String name) {
        this.name = name;
    }
    
}
