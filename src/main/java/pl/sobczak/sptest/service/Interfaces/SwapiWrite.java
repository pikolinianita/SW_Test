/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.service.Interfaces;

import pl.sobczak.sptest.service.SwRequest;

/**
 *
 * @author piko
 */
public interface SwapiWrite {

    boolean createOrUpdate(SwRequest request);
}
