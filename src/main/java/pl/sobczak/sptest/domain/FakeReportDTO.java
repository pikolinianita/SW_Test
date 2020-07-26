/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.domain;

import lombok.Getter;

/**
 *
 * @author piko
 */
@Getter
public class FakeReportDTO {

    String name;

    public FakeReportDTO() {
        name = "killer";
    }

    public FakeReportDTO(String name) {
        this.name = name;
    }

}
