/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.service.interfac;

import pl.sobczak.sptest.domain.SwRequest;

/**
 *
 * @author Lukasz Sobczak
 */
public interface SwapiWrite {

    boolean createOrUpdate(Long id, SwRequest request);
}
