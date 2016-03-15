/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laferme.service;

import laferme.entity.Utilisateur;
import laferme.exception.LoginNonExistant;
import laferme.exception.MotDePasseIncorrect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class ConnexionService {
    
        @Autowired
    private UtilisateurCrudService utilisateurCrudService;
    
    public void connexion(String email, String mdp) throws LoginNonExistant, MotDePasseIncorrect{
        Utilisateur u = utilisateurCrudService.findByEmail(email);

        if (u==null){
            throw new LoginNonExistant();
        }
        else if (!u.getMdp().equals(mdp)){
            throw new MotDePasseIncorrect();
        }
 
    }
    
}
