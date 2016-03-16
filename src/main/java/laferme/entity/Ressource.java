/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laferme.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import laferme.enumeration.TypeEtat;
import laferme.enumeration.TypeRessource;

/**
 *
 * @author admin
 */
@Entity
public class Ressource implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private TypeRessource typeRessource;
   
    private Integer dateLuneCreation;
    
    private Integer dateLuneCycle;
    
    @Enumerated(EnumType.STRING)
    private TypeEtat typeEtat;
    
    @ManyToOne
    @JoinColumn (name="UTIL_ID")
    Utilisateur utilisateur;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeRessource getTypeRessource() {
        return typeRessource;
    }

    public void setTypeRessource(TypeRessource typeRessource) {
        this.typeRessource = typeRessource;
    }

    public Integer getDateLuneCreation() {
        return dateLuneCreation;
    }

    public void setDateLuneCreation(Integer dateLuneCreation) {
        this.dateLuneCreation = dateLuneCreation;
    }

    public Integer getDateLuneCycle() {
        return dateLuneCycle;
    }

    public void setDateLuneCycle(Integer dateLuneCycle) {
        this.dateLuneCycle = dateLuneCycle;
    }

    public TypeEtat getTypeEtat() {
        return typeEtat;
    }

    public void setTypeEtat(TypeEtat typeEtat) {
        this.typeEtat = typeEtat;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Ressource() {
    }

    public Ressource(Long id, TypeRessource typeRessource, Integer dateLuneCreation, Integer dateLuneCycle, TypeEtat typeEtat, Utilisateur utilisateur) {
        this.id = id;
        this.typeRessource = typeRessource;
        this.dateLuneCreation = dateLuneCreation;
        this.dateLuneCycle = dateLuneCycle;
        this.typeEtat = typeEtat;
        this.utilisateur = utilisateur;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ressource)) {
            return false;
        }
        Ressource other = (Ressource) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "laferme.entity.Ressource[ id=" + id + " ]";
    }
    
}
