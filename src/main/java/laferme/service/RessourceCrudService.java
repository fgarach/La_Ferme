/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laferme.service;

import java.io.Serializable;
import java.util.List;
import laferme.entity.Ressource;
import laferme.enumeration.TypeEtat;
import laferme.enumeration.TypeRessource;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author admin
 */
public interface RessourceCrudService extends CrudRepository<Ressource, Long>{
    public List<Ressource> findByTypeRessourceAndTypeEtatAndUtilisateurId (TypeRessource tr, TypeEtat te,Long id);
    public List<Ressource> findByTypeEtatAndUtilisateurId (TypeEtat te,Long id);
    public List<Ressource> findByTypeRessourceAndUtilisateurId (TypeRessource tr,Long id);
    public List<Ressource> findByUtilisateurId (Long id);
    public Integer countByTypeRessourceAndUtilisateurId (TypeRessource tr,Long id);
    public Integer countByTypeRessourceAndTypeEtatAndUtilisateurId (TypeRessource tr, TypeEtat te,Long id);
  
    
}
