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
    UtilisateurCrudService utilisateurCrudService;

    @Autowired
    private DateService dateService;

    public void planter(Ressource r) {
        r.setTypeEtat(TypeEtat.OCCUPE);
        r.setDateLuneCycle(dateService.getLuneJeu());
        ressourceCrudService.save(r);
        
    }

    public void nourrir(Ressource acteur, String nourriture,Utilisateur u) {

        
        
        if (acteur.getTypeRessource().equals(TypeRessource.FERMIER)) {
            switch (nourriture) {
                case "ble":
                    List<Ressource> bles = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.BLE, TypeEtat.VIVANT,u.getId());

                    acteur.setDateLuneCreation(dateService.getLuneJeu());
                    ressourceCrudService.save(acteur);
                    for (int i = 0; i < Config.nourrirFermierBle; i++) {
                        bles.get(i).setTypeEtat(TypeEtat.MORT);
                        ressourceCrudService.save(bles.get(i));
                    }

                    break;

                case "carotte":
                    List<Ressource> carottes = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.CAROTTE, TypeEtat.VIVANT,u.getId());

                    acteur.setDateLuneCreation(dateService.getLuneJeu());
                    ressourceCrudService.save(acteur);
                    for (int i = 0; i < Config.nourrirFermierCarotte; i++) {
                        carottes.get(i).setTypeEtat(TypeEtat.MORT);
                        ressourceCrudService.save(carottes.get(i));
                    }

                    break;

                case "fromage":
                    List<Ressource> fromages = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.FROMAGE, TypeEtat.VIVANT,u.getId());

                    acteur.setDateLuneCreation(dateService.getLuneJeu());
                    ressourceCrudService.save(acteur);
                    for (int i = 0; i < Config.nourrirFermierFromage; i++) {
                        fromages.get(i).setTypeEtat(TypeEtat.MORT);
                        ressourceCrudService.save(fromages.get(i));
                    }

                    break;

                case "chevre":
                    List<Ressource> chevres = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.CHEVRE, TypeEtat.VIVANT,u.getId());

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
                    List<Ressource> bles = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.BLE, TypeEtat.VIVANT,u.getId());

                    acteur.setDateLuneCreation(dateService.getLuneJeu());
                    ressourceCrudService.save(acteur);
                    for (int i = 0; i < Config.nourrirChevreBle; i++) {
                        bles.get(i).setTypeEtat(TypeEtat.MORT);
                        ressourceCrudService.save(bles.get(i));
                    }

                    break;
                case "carotte":
                    List<Ressource> carottes = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.CAROTTE, TypeEtat.VIVANT,u.getId());

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

    public void echange(String ressourceDonnees, String ressourceVoulues, Utilisateur u) {

        if (ressourceDonnees.equals("chevre")) {
            List<Ressource> chevres = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.CHEVRE, TypeEtat.VIVANT,u.getId());
            for (int i = 0; i < Config.tauxEchangeChevre; i++) {
                chevres.get(i).setTypeEtat(TypeEtat.MORT);
                ressourceCrudService.save(chevres.get(i));
            }
        }

        if (ressourceDonnees.equals("ble")) {

            List<Ressource> bles = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.BLE, TypeEtat.VIVANT,u.getId());
            for (int i = 0; i < Config.tauxEchangeBle; i++) {
                bles.get(i).setTypeEtat(TypeEtat.MORT);
                ressourceCrudService.save(bles.get(i));
            }
        }

        if (ressourceDonnees.equals("carotte")) {

            List<Ressource> carottes = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.CAROTTE, TypeEtat.VIVANT,u.getId());
            for (int i = 0; i < Config.tauxEchangeCarotte; i++) {
                carottes.get(i).setTypeEtat(TypeEtat.MORT);
                ressourceCrudService.save(carottes.get(i));
            }
        }

        if (ressourceVoulues.equals("chevre")) {

            for (int i = 0; i < Config.tauxEchangeChevre; i++) {
                Ressource r = new Ressource(null, TypeRessource.CHEVRE, dateService.getLuneJeu(), dateService.getLuneJeu(), null, TypeEtat.VIVANT, u);
                ressourceCrudService.save(r);
                u.getRessources().add(r);

            }
        }
        
        if (ressourceVoulues.equals("ble")) {

            for (int i = 0; i < Config.tauxEchangeBle; i++) {
                Ressource r = new Ressource(null, TypeRessource.BLE, dateService.getLuneJeu(), null, null, TypeEtat.VIVANT, u);
                ressourceCrudService.save(r);
                u.getRessources().add(r);

            }
        }
        if (ressourceVoulues.equals("carotte")) {

            for (int i = 0; i < Config.tauxEchangeCarotte; i++) {
                Ressource r = new Ressource(null, TypeRessource.CAROTTE, dateService.getLuneJeu(), null, null, TypeEtat.VIVANT, u);
                ressourceCrudService.save(r);
                u.getRessources().add(r);

            }
        }

    }

}
