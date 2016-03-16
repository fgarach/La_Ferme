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
import laferme.service.DateService;
import laferme.service.RessourceCrudService;
import laferme.spring.AutowireServlet;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author admin
 */
@WebServlet(name = "AccouplerChevresServlet", urlPatterns = {"/accoupler_chevres"})
public class AccouplerChevresServlet extends AutowireServlet {

    @Autowired
    private RessourceCrudService ressourceCrudService;
    
    @Autowired
    private DateService dateService;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        List<Ressource> chevres = ressourceCrudService.findByTypeRessourceAndTypeEtat(TypeRessource.CHEVRE, TypeEtat.VIVANT);
        
        for(int i=0;i<2;i++){
            chevres.get(i).setDateLuneCycleEnceinte(dateService.getLuneJeu());
            chevres.get(i).setTypeEtat(TypeEtat.OCCUPE);
            ressourceCrudService.save(chevres.get(i));
        }
        
        resp.sendRedirect("plateforme");
        
    }

   
}
