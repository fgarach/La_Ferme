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
import laferme.service.RessourceCrudService;
import laferme.service.RessourceService;
import laferme.service.UtilisateurCrudService;
import laferme.spring.AutowireServlet;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author admin
 */
@WebServlet(name = "PlanterServlet", urlPatterns = {"/planter"})
public class PlanterServlet extends AutowireServlet {

    @Autowired
    private RessourceService ressourceService;

    @Autowired
    private RessourceCrudService ressourceCrudService;
    
    @Autowired
    private UtilisateurCrudService utilisateurService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email =req.getSession().getAttribute("email").toString();
        Utilisateur u = utilisateurService.findByEmail(email);
        
        String type = req.getParameter("type");
        if (type.equals("carotte")) {
            List<Ressource> carottesDisponibles = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.CAROTTE, TypeEtat.VIVANT,u.getId());
            ressourceService.planter(carottesDisponibles.get(0));
            
        }
        else if(type.equals("ble")){
            List<Ressource> blesDisponibles = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.BLE, TypeEtat.VIVANT,u.getId());
            ressourceService.planter(blesDisponibles.get(0));
        }
        resp.sendRedirect("plateforme");
    }

}
