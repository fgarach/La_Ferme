/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laferme.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import laferme.configuration.Config;
import laferme.entity.Ressource;
import laferme.entity.Utilisateur;
import laferme.enumeration.TypeEtat;
import laferme.enumeration.TypeRessource;
import laferme.exception.NourritureInsuffisanteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class RessourceService {

    @Autowired
    RessourceCrudService ressourceCrudService;

    @Autowired
    private DateService dateService;
    
    

    public void planter(Ressource r) {
        r.setTypeEtat(TypeEtat.OCCUPE);
        r.setDateLuneCycle(dateService.getLuneJeu());
        ressourceCrudService.save(r);
        System.err.println("sa marcheeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
    }

    public void nourrir(Ressource acteur, String nourriture){

        if (acteur.getTypeRessource().equals(TypeRessource.FERMIER)) {
            switch (nourriture) {
                case "ble":
                    List<Ressource> bles = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.BLE, TypeEtat.VIVANT);

                    acteur.setDateLuneCreation(dateService.getLuneJeu());
                    ressourceCrudService.save(acteur);
                    for (int i = 0; i < Config.nourrirFermierBle; i++) {
                        bles.get(i).setTypeEtat(TypeEtat.MORT);
                        ressourceCrudService.save(bles.get(i));
                    }

                    break;

                case "carotte":
                    List<Ressource> carottes = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.CAROTTE, TypeEtat.VIVANT);

                    acteur.setDateLuneCreation(dateService.getLuneJeu());
                    ressourceCrudService.save(acteur);
                    for (int i = 0; i < Config.nourrirFermierCarotte; i++) {
                        carottes.get(i).setTypeEtat(TypeEtat.MORT);
                        ressourceCrudService.save(carottes.get(i));
                    }

                    break;

                case "fromage":
                    List<Ressource> fromages = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.FROMAGE, TypeEtat.VIVANT);

                    acteur.setDateLuneCreation(dateService.getLuneJeu());
                    ressourceCrudService.save(acteur);
                    for (int i = 0; i < Config.nourrirFermierFromage; i++) {
                        fromages.get(i).setTypeEtat(TypeEtat.MORT);
                        ressourceCrudService.save(fromages.get(i));
                    }

                    break;

                case "chevre":
                    List<Ressource> chevres = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.CHEVRE, TypeEtat.VIVANT);

                    acteur.setDateLuneCreation(dateService.getLuneJeu());
                    ressourceCrudService.save(acteur);
                    for (int i = 0;
                            i < Config.nourrirFermierChevre;
                            i++) {
                        chevres.get(i).setTypeEtat(TypeEtat.MORT);
                        ressourceCrudService.save(chevres.get(i));
                    }

                    break;
            }

        } else if (acteur.getTypeRessource()
                .equals(TypeRessource.CHEVRE)) {

            switch (nourriture) {
                case "ble":
                    List<Ressource> bles = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.BLE, TypeEtat.VIVANT);

                    acteur.setDateLuneCreation(dateService.getLuneJeu());
                    ressourceCrudService.save(acteur);
                    for (int i = 0; i < Config.nourrirChevreBle; i++) {
                        bles.get(i).setTypeEtat(TypeEtat.MORT);
                        ressourceCrudService.save(bles.get(i));
                    }

                    break;
                case "carotte":
                    List<Ressource> carottes = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.CAROTTE, TypeEtat.VIVANT);

                    acteur.setDateLuneCreation(dateService.getLuneJeu());
                    ressourceCrudService.save(acteur);
                    for (int i = 0; i < Config.nourrirChevreCarotte; i++) {
                        carottes.get(i).setTypeEtat(TypeEtat.MORT);
                        ressourceCrudService.save(carottes.get(i));
                    }

                    break;

            }

        }
    }

    public void mourir(Ressource r) {

        r.setTypeEtat(TypeEtat.MORT);
        ressourceCrudService.save(r);
    }
}
