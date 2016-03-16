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
import laferme.service.RessourceService;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String type = req.getParameter("type");
        if (type.equals("carotte")) {
            List<Ressource> carottesDisponibles = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.CAROTTE, TypeEtat.VIVANT);
            ressourceService.planter(carottesDisponibles.get(0));
            
        }
        else if(type.equals("ble")){
            List<Ressource> blesDisponibles = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.BLE, TypeEtat.VIVANT);
            ressourceService.planter(blesDisponibles.get(0));
        }
        resp.sendRedirect("plateforme");
    }

}
