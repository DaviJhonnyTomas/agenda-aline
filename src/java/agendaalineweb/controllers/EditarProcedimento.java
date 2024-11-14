/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package agendaalineweb.controllers;

import agendaalineweb.entities.Cliente;
import agendaalineweb.entities.Procedimento;
import agendaalineweb.entities.Usuario;
import agendaalineweb.models.ClienteModel;
import agendaalineweb.models.ProcedimentoModel;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

@WebServlet(name = "EditarProcedimento", urlPatterns = {"/editar-procedimento"})
public class EditarProcedimento extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) //Buscar uma pagina
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String valor = request.getParameter("valor");
        String duracao = request.getParameter("duracao");
        String idUsuario = request.getParameter("idUsuario");

        Procedimento procedimento = new Procedimento(Integer.parseInt(id), nome, duracao, Double.parseDouble(valor), Integer.parseInt(idUsuario));
        request.setAttribute("procedimento", procedimento);
        request.setAttribute("modal", "modalEditarProcedimento");

        ProcedimentoModel procedimentoModel = new ProcedimentoModel();
        ArrayList<Procedimento> procedimentos = procedimentoModel.selectAll();
        request.setAttribute("procedimentos", procedimentos);

        String caminhoContexto = request.getContextPath();
        request.setAttribute("caminhoContexto", caminhoContexto);

        request.getRequestDispatcher("WEB-INF/pageProcedimentos.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)// Pegar algo da pagina e levar para o banco de dados
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String valor = request.getParameter("valor");
        String duracao = request.getParameter("duracao");
        String mensagem = null;
        ProcedimentoModel procedimentoModel = new ProcedimentoModel();
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        if (usuario == null) {
            mensagem = "Você deve fazer login para realizar esta operação.";

        } else {

            
            if (!nome.isEmpty() && !duracao.isEmpty() && !valor.isEmpty()) {

                Procedimento procedimento = new Procedimento(Integer.parseInt(id), nome, duracao, Double.parseDouble(valor), usuario.getId());

                procedimentoModel.updateById(procedimento);

            } else {
                mensagem = "Todos os campos devem ser preenchidos.";

            }
        }
        request.setAttribute("mensagemErro", mensagem);
        ArrayList<Procedimento> procedimentos = procedimentoModel.selectAll();
        request.setAttribute("procedimentos", procedimentos);

        request.setAttribute("caminhoContexto", request.getContextPath());

        request.getRequestDispatcher("WEB-INF/pageProcedimentos.jsp").forward(request, response);

    }
}
