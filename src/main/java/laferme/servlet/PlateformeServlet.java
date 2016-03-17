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
import laferme.service.ActualisationService;
import laferme.service.DateService;
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

    @Autowired
    private DateService dateService;

    @Autowired
    private ActualisationService actualisationService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idUtilisateur = Long.parseLong(req.getSession().getAttribute("login").toString());
        List<Ressource> fermiers = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.FERMIER, TypeEtat.VIVANT,idUtilisateur);
        //if (fermiers.isEmpty()) {
        if (false) {
            //interface, vous avez perdu
        } else {

            List<Ressource> carottesDispo = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.CAROTTE, TypeEtat.VIVANT,idUtilisateur);
            List<Ressource> blesDispo = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.BLE, TypeEtat.VIVANT,idUtilisateur);
            List<Ressource> fromagesDispo = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.FROMAGE, TypeEtat.VIVANT,idUtilisateur);

            List<Ressource> carottePlantees = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.CAROTTE, TypeEtat.OCCUPE,idUtilisateur);
            List<Ressource> blePlantes = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.BLE, TypeEtat.OCCUPE,idUtilisateur);
            List<Ressource> chevresNonEnceintes = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.CHEVRE, TypeEtat.VIVANT,idUtilisateur);
            List<Ressource> chevresEnceintes = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.CHEVRE, TypeEtat.OCCUPE,idUtilisateur);

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

            req.setAttribute("lune", dateService.getLuneJeu());
            if (!fermiers.isEmpty()) {

                req.setAttribute("vieFermier", (fermiers.get(0).getDateLuneCreation() + 1 * 30) - dateService.getLuneJeu());

            }

            //req.getRequestDispatcher("_CSS.jsp").include(req, resp);
            //req.getRequestDispatcher("_TITRE.jsp").include(req, resp);
            //req.getRequestDispatcher("_HEADER.jsp").include(req, resp);
            //req.getRequestDispatcher("_OPTIONJEU.jsp").include(req, resp);
            //req.getRequestDispatcher("_FOOTER.jsp").include(req, resp);
            req.setAttribute("tauxEchangeChevre", Config.tauxEchangeChevre);
            req.setAttribute("tauxEchangeCarotte", Config.tauxEchangeCarotte);
            req.setAttribute("tauxEchangeBle", Config.tauxEchangeBle);

            String option = req.getParameter("option");
            req.getRequestDispatcher("PlateformeDesign.jsp").include(req, resp);
            resp.sendRedirect("actualisation");
        }

    }
}
