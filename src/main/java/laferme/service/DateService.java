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

    public Date dateJeu = new Date();

    public boolean dateExpiree(Ressource r,Date d) {
        boolean estExpiree = false;

        if (d.after(dateJeu)) {
            estExpiree = true;
        }
        return true;
    }

}
