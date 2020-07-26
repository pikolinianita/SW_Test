/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.domain;

import java.util.Set;

/**
 *
 * @author Lukasz Sobczak
 */
public class ReportTestFactory {

    public Report lukeOnTatooine() {

        var hope = new Movie().setSwapiId(100L).setName("A Hope");
        var strike = new Movie().setSwapiId(200L).setName("Strike Back");
        var roJ = new Movie().setSwapiId(300L).setName("ReturnOfJedi");
        var luke = new Hero().setSwapiId(10L).setName("Luke S").setMovies(Set.of(hope, strike, roJ));

        var result = new Report()
                .setReportId(1L)
                .setPlanetId(99L)
                .setRequest(new SwRequest().setHeroName("Luke").setHeroPlanet("Tatooine"))
                .setHeroes(Set.of(luke));
        return result;

    }

    public Report lukeAndWanOnTatooine() {

        var hope = new Movie().setSwapiId(100L).setName("A Hope");
        var strike = new Movie().setSwapiId(200L).setName("Strike Back");
        var roJ = new Movie().setSwapiId(300L).setName("ReturnOfJedi");
        var luke = new Hero().setSwapiId(10L).setName("Luke S").setMovies(Set.of(hope,strike,roJ));
        var Obi =  new Hero().setSwapiId(20L).setName("Obi-Wan K").setMovies(Set.of(hope));
        
        var result = new Report()
                .setReportId(1L)
                .setPlanetId(99L)
                .setRequest(new SwRequest().setHeroName("K").setHeroPlanet("Tatooine"))
                .setHeroes(Set.of(luke,Obi));
        return result;

    }
    
    public Report LeiaOnAlderaan(){
        var hope = new Movie().setSwapiId(100L).setName("A Hope");
        var strike = new Movie().setSwapiId(200L).setName("Strike Back");
        var roJ = new Movie().setSwapiId(300L).setName("ReturnOfJedi");
        var leia = new Hero().setSwapiId(30L).setName("Leia O").setMovies(Set.of(hope,strike,roJ));
        
        var result = new Report()
                .setReportId(2L)
                .setPlanetId(199L)
                .setRequest(new SwRequest().setHeroName("Leia").setHeroPlanet("Alderaan"))
                .setHeroes(Set.of(leia));
        return result;
        
    }
    
}