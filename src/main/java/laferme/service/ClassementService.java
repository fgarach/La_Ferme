/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laferme.service;

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

    public List<Utilisateur> classer() {

        List<Utilisateur> utilisateurs = (List<Utilisateur>) utilisateurCrudService.findAll();

//        for (Utilisateur util : utilisateurs) {
//
//            List<Ressource> carottesDispo = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.CAROTTE, TypeEtat.VIVANT);
//            List<Ressource> carottePlantees = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.CAROTTE, TypeEtat.OCCUPE);
//            List<Ressource> blesDispo = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.BLE, TypeEtat.VIVANT);
//            List<Ressource> blePlantes = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.BLE, TypeEtat.OCCUPE);
//            List<Ressource> chevresNonEnceintes = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.CHEVRE, TypeEtat.VIVANT);
//            List<Ressource> chevresEnceintes = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.CHEVRE, TypeEtat.OCCUPE);
//
//            int nbCarotte = carottesDispo.size() + carottePlantees.size();
//            int nbChevre = chevresNonEnceintes.size() + chevresEnceintes.size();
//            int nbBle = blesDispo.size() + blePlantes.size();
//
//            int score = nbCarotte * Config.tauxEchangeCarotte + nbChevre * Config.tauxEchangeChevre + nbBle * Config.tauxEchangeBle;
//
//        }
        return utilisateurs;

    }

}
