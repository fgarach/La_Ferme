/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laferme.service;

import laferme.entity.Utilisateur;
import laferme.exception.UtilisateurDejaInscrit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class InscriptionService {
    
    @Autowired
    private UtilisateurCrudService utilisateurCrudService;

    
    public void inscriptionUtilisateur(Utilisateur u) throws UtilisateurDejaInscrit{

        if(utilisateurCrudService.findByEmail(u.getEmail())!= null){
            throw new UtilisateurDejaInscrit();
        }  
        
    }
    
}
