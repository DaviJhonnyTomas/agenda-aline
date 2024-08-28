/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package agendaalineweb.controllers;

import agendaalineweb.entities.Usuario;
import agendaalineweb.models.UsuarioModel;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Utilizador
 */
@WebServlet(name = "AcessarLogin", urlPatterns = {"/login"})
public class AcessarLogin extends HttpServlet {

    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/pageLogin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        UsuarioModel usuarioModel = new UsuarioModel();
        try {
            Usuario usuario = usuarioModel.getUsuario(email, senha);
            
            //sessão -> período de tempo
            HttpSession sessao = request.getSession(true);
            sessao.setAttribute("usuarioLogado", usuario);
            
            response.sendRedirect("listagem-agendamentos-dia");
        } catch (SQLException ex) {
            Logger.getLogger(AcessarLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
