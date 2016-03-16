/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laferme.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import laferme.entity.Ressource;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        if (type.equals("fermier")) {

            Long idFermier = Long.parseLong(req.getParameter("idFermier"));
            Ressource fermier = ressourceCrudService.findOne(idFermier);
            ressourceService.nourrir(fermier, null);

        } else if (type.equals("chevre")) {
            Long idChevre = Long.parseLong(req.getParameter("idChevre"));
            Ressource chevre = ressourceCrudService.findOne(idChevre);
            ressourceService.nourrir(chevre, null);
        }
        resp.sendRedirect("plateforme");
    }

}
