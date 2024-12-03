/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package agendaalineweb.controllers;

import agendaalineweb.models.NegocioModel;
import agendaalineweb.entities.Negocio;
import agendaalineweb.entities.Usuario;
import agendaalineweb.models.UsuarioModel;
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
@WebServlet(name = "AcessarGerenciamentoNegocio", urlPatterns = {"/gerenciamento-negocio"})
public class AcessarGerenciamentoNegocio extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        NegocioModel negocioModel = new NegocioModel();
        String mensagem = null;
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        if(usuario != null){
            
            Negocio negocio = negocioModel.getNegocioById(usuario.getIdNegocio());
            
            if(usuario.getId() == negocio.getIdUsuarioAdm()){ //verificação do administrador
                UsuarioModel usuarioModel = new UsuarioModel();
                String administrador = usuario.getNome();
                request.setAttribute("administrador", administrador);
                request.setAttribute("negocio", negocio);
                String caminhoContexto = request.getContextPath();
                request.setAttribute("caminhoContexto", caminhoContexto);
                request.getRequestDispatcher("WEB-INF/pageGerenciamentoNegocio.jsp").forward(request, response);
            }else{
                mensagem = "Você não tem acesso a essa página. Somente Adm.";
                request.setAttribute("mensagemErro", mensagem);
                request.getRequestDispatcher("WEB-INF/pageLogin.jsp").forward(request, response);

            }
        }else{
            mensagem = "Você precisa estar logado como Adm.";
            request.setAttribute("mensagemErro", mensagem);
        request.getRequestDispatcher("WEB-INF/pageLogin.jsp").forward(request, response);
            
        }
        

        
        
        
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    

}
