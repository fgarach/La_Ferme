/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laferme.service;

import java.util.ArrayList;
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
public class ClassementService {

    @Autowired
    RessourceCrudService ressourceCrudService;

    @Autowired
    UtilisateurCrudService utilisateurCrudService;

    public List<Utilisateur> classerTop10() {
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
        utilisateurs = (List<Utilisateur>) utilisateurCrudService.findAll();
        for (Utilisateur u : utilisateurs) {
            this.calculScore(u);
        }
        List<Utilisateur> utilisateurClassés = new ArrayList<Utilisateur>();
        utilisateurClassés = utilisateurCrudService.findAllByOrderByScoreDesc();
        List<Utilisateur> utilisateurTop10 = new ArrayList<Utilisateur>();
        if (utilisateurClassés.size() < 10) {
            return utilisateurClassés;
        } else {
            for (int i = 0; i < 10; i++) {
                utilisateurTop10 = (List<Utilisateur>) utilisateurClassés.get(i);
            }
            return utilisateurTop10;
        }
    }

    public void calculScore(Utilisateur u) {

        List<Ressource> carottesDispo = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.CAROTTE, TypeEtat.VIVANT, u.getId());
        List<Ressource> carottePlantees = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.CAROTTE, TypeEtat.OCCUPE, u.getId());
        List<Ressource> blesDispo = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.BLE, TypeEtat.VIVANT, u.getId());
        List<Ressource> blePlantes = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.BLE, TypeEtat.OCCUPE, u.getId());
        List<Ressource> chevresNonEnceintes = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.CHEVRE, TypeEtat.VIVANT, u.getId());
        List<Ressource> chevresEnceintes = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.CHEVRE, TypeEtat.OCCUPE, u.getId());
        int nbCarotte = carottesDispo.size() + carottePlantees.size();
        int nbChevre = chevresNonEnceintes.size() + chevresEnceintes.size();
        int nbBle = blesDispo.size() + blePlantes.size();
        int score = nbCarotte * Config.tauxEchangeCarotte + nbChevre * Config.tauxEchangeChevre + nbBle * Config.tauxEchangeBle;
        u.setScore(score);
        utilisateurCrudService.save(u);
    }

}
