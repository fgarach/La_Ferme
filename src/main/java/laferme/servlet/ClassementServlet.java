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
import laferme.service.ClassementService;
import laferme.service.RessourceCrudService;
import laferme.spring.AutowireServlet;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author admin
 */
@WebServlet(name = "ClassementServlet", urlPatterns = {"/classement"})
public class ClassementServlet  extends AutowireServlet{

    @Autowired
    RessourceCrudService ressourceCrudService;
    
    @Autowired
    ClassementService classementService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = "classement";
        req.setAttribute("option", option);

        List <Utilisateur> classements = classementService.classerTop10();
        req.setAttribute("classements", classements);
                
        req.getRequestDispatcher("plateforme").forward(req, resp);
    }

}
