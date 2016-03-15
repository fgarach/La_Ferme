/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laferme.service;

import java.util.Random;

/**
 *
 * @author admin
 */
public class ActualisationService {
    
    public void misAJourCarotteDisponible(){
        Random rand = new Random();
        int nombre = rand.nextInt(3 - 2 + 1) + 2;
    }
    
    public void misAJourBleDisponible(){
        
    }
    
    public void misAJourChevreDisponible(){
        
    }
    
    public void misAJourFromageDisponible(){
        
    }
}
