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
import laferme.entity.Ressource;
import laferme.enumeration.TypeEtat;
import laferme.enumeration.TypeRessource;
import laferme.service.RessourceCrudService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author admin
 */
@WebServlet(name = "ClassementServlet", urlPatterns = {"/classement"})
public class ClassementServlet extends HttpServlet {

    @Autowired
    RessourceCrudService ressourceCrudService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = "classement";
        req.setAttribute("option", option);
        
        List<Ressource> carottesDispo = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.CAROTTE, TypeEtat.VIVANT);
        List<Ressource> blesDispo = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.BLE, TypeEtat.VIVANT);
        List<Ressource> fromagesDispo = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.FROMAGE, TypeEtat.VIVANT);

        List<Ressource> carottePlantees = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.CAROTTE, TypeEtat.OCCUPE);
        List<Ressource> blePlantes = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.BLE, TypeEtat.OCCUPE);
        List<Ressource> chevresNonEnceintes = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.CHEVRE, TypeEtat.VIVANT);
        List<Ressource> chevresEnceintes = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.CHEVRE, TypeEtat.OCCUPE);
        
        int nbCarotte = carottesDispo.size()+carottePlantees.size();
        int nbFromage = fromagesDispo.size();
        int nbChevre = chevresNonEnceintes.size()+chevresEnceintes.size();
        int nbBle = blesDispo.size()+blePlantes.size();
        
//        int score = nbCarotte
                
        req.getRequestDispatcher("plateforme").forward(req, resp);
    }

}
