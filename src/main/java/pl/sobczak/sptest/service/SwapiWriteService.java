/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.service;

import pl.sobczak.sptest.domain.SwRequest;
import pl.sobczak.sptest.service.Interfaces.SwapiWrite;

/**
 *
 * @author piko
 */
public class SwapiWriteService implements SwapiWrite {

    @Override
    public boolean createOrUpdate(SwRequest request) {
        
        return true;
    }

}
