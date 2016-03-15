/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laferme.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class ScheduledService {
    
    @Scheduled(cron = "*/10 * * * * MON-FRI") //sec min heur jour mois jourdumois  toute les 10 sec du lundi au vendredi
    public void cron(){
        
    }
    
    
    @Scheduled(fixedDelay = 2000) //2sec entre la fin du précédent et début du suivant
    public void fixedDelay(){
        
    }
    
    //fonction qui s'execute toute les 2 seconde pour gerer tous les cycles:
    
}
