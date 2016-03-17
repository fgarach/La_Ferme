/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laferme.service;

import java.util.Date;
import laferme.entity.Ressource;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class DateService {

    private Integer luneJeu = 0;

    public Integer getLuneJeu() {
        return luneJeu;
    }

    public void setLuneJeu(Integer luneJeu) {
        this.luneJeu = luneJeu;
    }

  
    public boolean dateExpiree(Ressource r,Integer luneR,Integer cycle) {
        boolean estExpiree = false;
        if((luneJeu-luneR)>=(cycle*30)){
            estExpiree=true;
        }

        return estExpiree;
    }

}
