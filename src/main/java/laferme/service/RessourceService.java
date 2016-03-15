/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laferme.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import laferme.entity.Ressource;
import laferme.entity.Utilisateur;
import laferme.enumeration.TypeEtat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class RessourceService {

    @Autowired
    RessourceCrudService ressourceCrudService;

    public void planter(Ressource r) {
        r.setTypeEtat(TypeEtat.OCCUPE);
        r.setDateCycle(Date.from(Instant.MIN)); //DATEASETER
    }

    public void nourrir(Ressource acteur, Ressource consommable) {
        acteur.setDateCreation(Date.from(Instant.MIN));//DATEASETER
        consommable.setTypeEtat(TypeEtat.MORT);
    }

    public void mourir() {

        List<Ressource> ressourcesMorte = ressourceCrudService.findByTypeEtat(TypeEtat.MORT);

        for (Ressource ressource : ressourcesMorte) {
            Utilisateur u = ressource.getUtilisateur();
            ressourceCrudService.delete(ressource);
            u.getRessources().remove(ressource);
        }

    }
}
