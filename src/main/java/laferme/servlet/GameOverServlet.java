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

/**
 *
 * @author admin
 */
@WebServlet(name = "GameOverServlet", urlPatterns = {"/game_over"})
public class GameOverServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("_CSS.jsp").include(req, resp);  
        req.getRequestDispatcher("_HEADER.jsp").include(req, resp);  
        req.getRequestDispatcher("GameOver.jsp").include(req, resp);
        req.getRequestDispatcher("_FOOTER.jsp").include(req, resp);
    }

}
