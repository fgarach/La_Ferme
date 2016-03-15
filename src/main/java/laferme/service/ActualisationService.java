/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laferme.service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import laferme.configuration.Config;
import laferme.entity.Ressource;
import laferme.entity.Utilisateur;
import laferme.enumeration.TypeEtat;
import laferme.enumeration.TypeRessource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class ActualisationService {

    @Autowired
    private RessourceCrudService ressourceCrudService;

    @Autowired
    private RessourceService ressourceService;

    @Autowired
    private DateService dateService;

   // @Scheduled(fixedDelay = 2000)
    public void actualiser() {

        misAJourCarotteDisponibleEtPlantee();
//        misAJourBleDisponibleEtPlantee();
//        misAJourFromageDisponible();
//        misAJourCycleVieFermier();
//        misAJourCycleVieChevre();
    }

    private void misAJourCarotteDisponibleEtPlantee() {

        List<Ressource> carottesPlantees = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.CAROTTE, TypeEtat.OCCUPE);

        for (Ressource carottePlantee : carottesPlantees) {
            if (dateService.dateExpiree(carottePlantee, carottePlantee.getDateCycle())) {
                ressourceService.mourir(carottePlantee);
                Utilisateur u = carottePlantee.getUtilisateur();

                Random rand = new Random();
                int nombreCarotteRecoltees = rand.nextInt(Config.nbRecolteCarotteMax - Config.nbRecolteCarotteMin + 1) + Config.nbRecolteCarotteMin;

                for (int i = 0; i < nombreCarotteRecoltees; i++) {
                    Ressource carotte = new Ressource(null, TypeRessource.CAROTTE, new Date(), null, TypeEtat.VIVANT, u);
                    ressourceCrudService.save(carotte);
                    u.getRessources().add(carotte);
                }
            }
        }
    }

    private void misAJourBleDisponibleEtPlantee() {

        List<Ressource> blesPlantes = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.BLE, TypeEtat.OCCUPE);

        for (Ressource blePlante : blesPlantes) {
            if (dateService.dateExpiree(blePlante, blePlante.getDateCycle())) {
                ressourceService.mourir(blePlante);
                Utilisateur u = blePlante.getUtilisateur();

                Random rand = new Random();
                int nombreBleRecoltes = rand.nextInt(Config.nbRecolteBleMax - Config.nbRecolteBleMin + 1) + Config.nbRecolteBleMin;

                for (int i = 0; i < nombreBleRecoltes; i++) {
                    Ressource ble = new Ressource(null, TypeRessource.BLE, new Date(), null, TypeEtat.VIVANT, u);
                    ressourceCrudService.save(ble);
                    u.getRessources().add(ble);
                }
            }
        }
    }

    private void misAJourFromageDisponible() {
        List<Ressource> chevres = ressourceCrudService.findByTypeRessource(TypeRessource.CHEVRE);

        for (Ressource chevre : chevres) {
            if (dateService.dateExpiree(chevre, chevre.getDateCycle())) {
                Utilisateur u = chevre.getUtilisateur();
                Ressource fromage = new Ressource(null, TypeRessource.FROMAGE, new Date(), null, TypeEtat.VIVANT, u);
            }
        }
    }

    private void misAJourCycleVieFermier() {
        List<Ressource> fermiers = ressourceCrudService.findByTypeRessource(TypeRessource.FERMIER);

        for (Ressource fermier : fermiers) {
            if (dateService.dateExpiree(fermier, fermier.getDateCreation())) {
                fermier.setTypeEtat(TypeEtat.MORT);
            }
        }
    }

    private void misAJourCycleVieChevre() {
        List<Ressource> chevres = ressourceCrudService.findByTypeRessource(TypeRessource.CHEVRE);

        for (Ressource chevre : chevres) {
            if (dateService.dateExpiree(chevre, chevre.getDateCreation())) {
                chevre.setTypeEtat(TypeEtat.MORT);
            }
        }
    }

    private void supprimerRessourceMorte() {
        List<Ressource> ressourcesMorte = ressourceCrudService.findByTypeEtat(TypeEtat.MORT);

        for (Ressource ressource : ressourcesMorte) {
            Utilisateur u = ressource.getUtilisateur();
            ressourceCrudService.delete(ressource);
            u.getRessources().remove(ressource);
        }
    }
}
