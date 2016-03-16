/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laferme.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import laferme.configuration.Config;
import laferme.entity.Ressource;
import laferme.entity.Utilisateur;
import laferme.enumeration.TypeEtat;
import laferme.enumeration.TypeRessource;
import laferme.service.InitialiserPlateformeService;
import laferme.service.RessourceCrudService;
import laferme.service.UtilisateurCrudService;
import laferme.spring.AutowireServlet;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author admin
 */
@WebServlet(name = "PlateformeServlet", urlPatterns = {"/plateforme"})
public class PlateformeServlet extends AutowireServlet {


    @Autowired
    private RessourceCrudService ressourceCrudService;

    @Autowired
    private UtilisateurCrudService utilisateurCrudService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<Ressource> fermiers = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.FERMIER, TypeEtat.VIVANT);
        //if (fermiers.isEmpty()) {
        if (false) {
              //interface, vous avez perdu
        } 
        else {


            List<Ressource> carottesDispo = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.CAROTTE, TypeEtat.VIVANT);
            List<Ressource> blesDispo = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.BLE, TypeEtat.VIVANT);
            List<Ressource> fromagesDispo = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.FROMAGE, TypeEtat.VIVANT);
            
            
            List<Ressource> carottePlantees = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.CAROTTE, TypeEtat.OCCUPE);
            List<Ressource> blePlantes = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.BLE, TypeEtat.OCCUPE);
            List<Ressource> chevresNonEnceintes = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.CHEVRE, TypeEtat.VIVANT);
            List<Ressource> chevresEnceintes = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.CHEVRE, TypeEtat.OCCUPE);


            
            Boolean aNourrir = false;
            
            req.setAttribute("carottesDispo", carottesDispo);
            req.setAttribute("blesDispo", blesDispo);
            req.setAttribute("fromagesDispo", fromagesDispo);
            req.setAttribute("carottesPlantees", carottePlantees);
            req.setAttribute("blesPlantes", blePlantes);
            req.setAttribute("fermiers", fermiers);
            req.setAttribute("chevresNonEnceintes", chevresNonEnceintes);
            req.setAttribute("chevresEnceintes", chevresEnceintes);
            req.setAttribute("nourrirChevreBle", Config.nourrirChevreBle);
            req.setAttribute("nourrirChevreCarotte", Config.nourrirChevreCarotte);
            req.setAttribute("nourrirFermierChevre", Config.nourrirFermierChevre);
            req.setAttribute("nourrirFermierCarotte", Config.nourrirFermierCarotte);
            req.setAttribute("nourrirFermierFromage", Config.nourrirFermierFromage);
            req.setAttribute("nourrirFermierBle", Config.nourrirFermierBle);
            
            req.getRequestDispatcher("Plateforme.jsp").include(req, resp);

        }

    }

}
