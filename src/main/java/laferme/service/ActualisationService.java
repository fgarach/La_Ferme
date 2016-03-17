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

    @Scheduled(fixedDelay = 1000)
    public void actualiserLune() {
        dateService.setLuneJeu(dateService.getLuneJeu() + 1);
    }

    public void actualiser() {
        misAJourCarotteDisponibleEtPlantee();
        misAJourBleDisponibleEtPlantee();
        misAJourFromageDisponible();
        misAJourNaissanceChevre();
        misAJourCycleVieFermier();
        misAJourCycleVieChevre();
        supprimerRessourceMorte();
    }

    private void misAJourCarotteDisponibleEtPlantee() {

        List<Ressource> carottesPlantees = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.CAROTTE, TypeEtat.OCCUPE);
        System.out.println("taille : " + carottesPlantees.size());

        for (Ressource carottePlantee : carottesPlantees) {
            if (dateService.dateExpiree(carottePlantee, carottePlantee.getDateLuneCycle(), Config.cyclePlantageCarotte)) {
                ressourceService.mourir(carottePlantee);
                Utilisateur u = carottePlantee.getUtilisateur();

                Random rand = new Random();
                int nombreCarotteRecoltees = rand.nextInt(Config.nbRecolteCarotteMax - Config.nbRecolteCarotteMin + 1) + Config.nbRecolteCarotteMin;

                for (int i = 0; i < nombreCarotteRecoltees; i++) {
                    Ressource carotte = new Ressource(null, TypeRessource.CAROTTE, dateService.getLuneJeu(), null,null, TypeEtat.VIVANT, u);
                    ressourceCrudService.save(carotte);
                    u.getRessources().add(carotte);
                }
            }
        }
    }

    private void misAJourBleDisponibleEtPlantee() {

        List<Ressource> blesPlantes = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.BLE, TypeEtat.OCCUPE);

        for (Ressource blePlante : blesPlantes) {
            if (dateService.dateExpiree(blePlante, blePlante.getDateLuneCycle(), Config.cyclePlantageBle)) {
                ressourceService.mourir(blePlante);
                Utilisateur u = blePlante.getUtilisateur();

                Random rand = new Random();
                int nombreBleRecoltes = rand.nextInt(Config.nbRecolteBleMax - Config.nbRecolteBleMin + 1) + Config.nbRecolteBleMin;

                for (int i = 0; i < nombreBleRecoltes; i++) {
                    Ressource ble = new Ressource(null, TypeRessource.BLE, dateService.getLuneJeu(), null,null, TypeEtat.VIVANT, u);
                    ressourceCrudService.save(ble);
                    u.getRessources().add(ble);
                }
            }
        }
    }

    private void misAJourFromageDisponible() {
        List<Ressource> chevres = ressourceCrudService.findByTypeRessource(TypeRessource.CHEVRE);

        for (Ressource chevre : chevres) {
            if (dateService.dateExpiree(chevre, chevre.getDateLuneCycle(), Config.cycleCreationFromage)) {
                Utilisateur u = chevre.getUtilisateur();
                Random rand = new Random();
                int nombreFromageRecoltes = rand.nextInt(Config.nbRecolteFromageMax - Config.nbRecolteFromageMin + 1) + Config.nbRecolteFromageMin;

                for (int i = 0; i < nombreFromageRecoltes; i++) {
                    Ressource fromage = new Ressource(null, TypeRessource.FROMAGE, dateService.getLuneJeu(), null,null,TypeEtat.VIVANT, u);
                    ressourceCrudService.save(fromage);
                    u.getRessources().add(fromage);
                }
                chevre.setDateLuneCycle(dateService.getLuneJeu());
                ressourceCrudService.save(chevre);
            }
        }
    }

    private void misAJourCycleVieFermier() {
        List<Ressource> fermiers = ressourceCrudService.findByTypeRessource(TypeRessource.FERMIER);

        for (Ressource fermier : fermiers) {
            if (dateService.dateExpiree(fermier, fermier.getDateLuneCreation(), Config.cycleMortFermier)) {
                fermier.setTypeEtat(TypeEtat.MORT);
                ressourceCrudService.save(fermier);
            }
        }
    }

    private void misAJourCycleVieChevre() {
        List<Ressource> chevres = ressourceCrudService.findByTypeRessource(TypeRessource.CHEVRE);

        for (Ressource chevre : chevres) {
            if (dateService.dateExpiree(chevre, chevre.getDateLuneCreation(), Config.cycleMortChevre)){
                chevre.setTypeEtat(TypeEtat.MORT);
                ressourceCrudService.save(chevre);
            }
        }
    }

    private void misAJourNaissanceChevre() {
        List<Ressource> chevreEnceintes = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.CHEVRE, TypeEtat.OCCUPE);

        Boolean naissance = false;

        for (Ressource chevreEnceinte : chevreEnceintes) {
            if (dateService.dateExpiree(chevreEnceinte, chevreEnceinte.getDateLuneCycleEnceinte(), Config.cycleNaissanceChevre)) {

                if (!naissance) {
                    naissance = true;
                    Utilisateur u = chevreEnceinte.getUtilisateur();
                    Ressource chevre = new Ressource(null, TypeRessource.CHEVRE, dateService.getLuneJeu(), dateService.getLuneJeu(),null, TypeEtat.VIVANT, u);
                    ressourceCrudService.save(chevre);
                    u.getRessources().add(chevre);
                } else {
                    naissance = false;
                }
                chevreEnceinte.setTypeEtat(TypeEtat.VIVANT);
                chevreEnceinte.setDateLuneCycleEnceinte(null);
                ressourceCrudService.save(chevreEnceinte);
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
