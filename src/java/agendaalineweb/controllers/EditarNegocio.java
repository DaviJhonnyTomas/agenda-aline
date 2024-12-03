/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package agendaalineweb.controllers;

import agendaalineweb.entities.Negocio;
import agendaalineweb.entities.Usuario;
import agendaalineweb.models.NegocioModel;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.ext.ParamConverter;

/**
 *
 * @author Utilizador
 */
@WebServlet(name = "EditarNegocio", urlPatterns = {"/editar-negocio"})
public class EditarNegocio extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        String nif = request.getParameter("nif");

        System.out.println("Nome: " + nome);
        
        NegocioModel negocioModel = new NegocioModel();

        String mensagem = null;

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        //se usuário logado
        //se o usuário logado é administrador
        //campos preenchidos
        if (usuario == null) {
            response.sendRedirect("login");
            
        } else if (!id.isEmpty() && !nome.isEmpty() && !endereco.isEmpty() && !email.isEmpty() && !telefone.isEmpty() && !nif.isEmpty()) {

            Negocio negocio = new Negocio(Integer.parseInt(id), nome, email, telefone, endereco, Integer.parseInt(nif));

            negocioModel.update(negocio);

        } else {
            mensagem = "Todos os campos devem ser preenchidos.";

        }
        Negocio negocioDoUsuario = negocioModel.getNegocioById(usuario.getIdNegocio());

        request.setAttribute("administrador", usuario.getNome());
        request.setAttribute("negocio", negocioDoUsuario);

        request.setAttribute("mensagemErro", mensagem);

        request.setAttribute("caminhoContexto", request.getContextPath());

        request.getRequestDispatcher("WEB-INF/pageGerenciamentoNegocio.jsp").forward(request, response);
    }

}
