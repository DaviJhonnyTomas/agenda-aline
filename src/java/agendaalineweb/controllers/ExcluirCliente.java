/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package agendaalineweb.controllers;

import agendaalineweb.models.ClienteModel;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Utilizador
 */
@WebServlet(name = "ExcluirCliente", urlPatterns = {"/excluir-cliente"})
public class ExcluirCliente extends HttpServlet {
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        ClienteModel modelCliente = new ClienteModel();
        modelCliente.deleteById(Integer.parseInt(id));
        String caminhoContexto = request.getContextPath();
        response.sendRedirect(caminhoContexto+"/cadastrar-cliente");
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
}
