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
@WebServlet(name = "NourrirServlet", urlPatterns = {"/nourrir"})
public class NourrirServlet extends AutowireServlet {

    @Autowired
    private RessourceCrudService ressourceCrudService;

    @Autowired
    private RessourceService ressourceService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        String typeRessource = req.getParameter("nourriture");
        if (type.equals("fermier")) {

            Long idFermier = Long.parseLong(req.getParameter("idFermier"));
            Ressource fermier = ressourceCrudService.findOne(idFermier);

            ressourceService.nourrir(fermier, typeRessource);

        } else if (type.equals("chevre")) {
            List<Ressource> chevresNonEnceintes = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.CHEVRE, TypeEtat.VIVANT);
            for (Ressource chevre : chevresNonEnceintes ) {
                ressourceService.nourrir(chevre, typeRessource);
            }
        }
        resp.sendRedirect("plateforme");
    }

}
