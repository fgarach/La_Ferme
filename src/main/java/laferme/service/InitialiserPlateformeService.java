/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laferme.service;

import java.util.Date;
import java.util.List;
import laferme.configuration.Config;
import laferme.entity.Ressource;
import laferme.entity.Utilisateur;
import laferme.enumeration.TypeEtat;
import laferme.enumeration.TypeRessource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class InitialiserPlateformeService {

    @Autowired
    private RessourceCrudService ressourceCrudService;

    public void initialiser(Utilisateur u) {

        List<Ressource> l = (List<Ressource>) ressourceCrudService.findAll();
       
        if (l.isEmpty()) {
                 System.out.println("initialiser");
            for (int i = 0; i < Config.initialisationNbCarotte; i++) {
                Ressource r = new Ressource(null, TypeRessource.CAROTTE, new Date(), null, TypeEtat.VIVANT, u);
                ressourceCrudService.save(r);
                u.getRessources().add(r);
            }

            for (int i = 0; i < Config.initialisationNbBle; i++) {
                Ressource r = new Ressource(null, TypeRessource.BLE, new Date(), null, TypeEtat.VIVANT, u);
                ressourceCrudService.save(r);
                u.getRessources().add(r);
            }

            for (int i = 0; i < Config.initialisationNbChevre; i++) {
                Ressource r = new Ressource(null, TypeRessource.CHEVRE, new Date(), null, TypeEtat.VIVANT, u);
                ressourceCrudService.save(r);
                u.getRessources().add(r);
            }
        }
    }
}
