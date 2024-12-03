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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

        if (usuario != null) {
            ClienteModel clienteModel = new ClienteModel();
            ArrayList<Cliente> clientes = clienteModel.selectAll(usuario.getIdNegocio());
            String caminhoContexto = request.getContextPath();
            request.setAttribute("caminhoContexto", caminhoContexto);
            request.setAttribute("clientes", clientes);
            request.getRequestDispatcher("WEB-INF/pageCliente.jsp").forward(request, response);
        }
        else{
            response.sendRedirect("login");
    }
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
    request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        ClienteModel clienteModel = new ClienteModel();
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        String mensagem = null;
        if (usuario == null) {
            mensagem = "Você deve fazer login para realizar esta operação.";

        }
        else if (!nome.isEmpty() && !telefone.isEmpty() && !email.isEmpty()) {
            //HttpSession sessao = request.getSession();
            

            //int idNegocio = usuario.getIdNegocio();
            Cliente cliente = new Cliente(nome, telefone, email, usuario.getIdNegocio());

            //ClienteModel clienteModel = new ClienteModel();
            clienteModel.insert(cliente);
            

        } else {
            mensagem = "Todos os campos devem ser preenchidos.";
           

        }
        request.setAttribute("mensagemErro", mensagem);

            request.setAttribute("clientes", clienteModel.selectAll(usuario.getIdNegocio()));

            String caminhoContexto = request.getContextPath();
            request.setAttribute("caminhoContexto", caminhoContexto);

            request.getRequestDispatcher("WEB-INF/pageCliente.jsp").forward(request, response);

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
