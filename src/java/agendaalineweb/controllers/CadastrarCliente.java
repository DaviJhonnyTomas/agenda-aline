/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package agendaalineweb.controllers;

import agendaalineweb.entities.Cliente;
import agendaalineweb.entities.Usuario;
import agendaalineweb.models.ClienteModel;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
 
/**
 *
 * @author Utilizador
 */
@WebServlet(name = "CadastrarCliente", urlPatterns = {"/cadastrar-cliente"})
public class CadastrarCliente extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            ClienteModel clienteModel = new ClienteModel();
            ArrayList<Cliente> clientes = clienteModel.selectAll();
            String caminhoContexto = request.getContextPath();
            request.setAttribute("caminhoContexto", caminhoContexto);
            request.setAttribute("clientes", clientes);
            request.getRequestDispatcher("WEB-INF/pageCliente.jsp").forward(request, response);
         
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
      
        
        
        HttpSession sessao = request.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
        
        int idNegocio = usuario.getIdNegocio();
          
        Cliente cliente = new Cliente(nome, telefone, email, idNegocio);
        
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.insert(cliente);
        response.sendRedirect("cadastrar-cliente");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
