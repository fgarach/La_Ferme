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
    private InitialiserPlateformeService initialiserPlateformeService;
    
    @Autowired
    private RessourceCrudService ressourceCrudService;
    
    @Autowired
    private UtilisateurCrudService utilisateurCrudService;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utilisateur u= utilisateurCrudService.findOne(1L);
        initialiserPlateformeService.initialiser(u);
        
        Integer nbCarotte =ressourceCrudService.countByTypeRessourceAndTypeEtat(TypeRessource.CAROTTE, TypeEtat.VIVANT);
        Integer nbBle =ressourceCrudService.countByTypeRessource(TypeRessource.BLE);
        Integer nbChevre =ressourceCrudService.countByTypeRessource(TypeRessource.CHEVRE);
        
        List<Ressource> carottePlantees =ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.CAROTTE, TypeEtat.OCCUPE);
        
        System.out.println("taile : "+carottePlantees.size());
        req.setAttribute("nbCarotte", nbCarotte);
        req.setAttribute("nbBle", nbBle);
        req.setAttribute("nbChevre", nbChevre);
        req.setAttribute("carottes", carottePlantees);

        
        req.getRequestDispatcher("Plateforme.jsp").include(req, resp);
        
        
        
        
        
        
    }

}