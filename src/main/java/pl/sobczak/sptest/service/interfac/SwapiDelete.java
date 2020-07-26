/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.service.interfac;

/**
 *
 * @author piko
 */
public interface SwapiDelete {

    boolean deleteOne(Long id);

    boolean deleteAll();

}
