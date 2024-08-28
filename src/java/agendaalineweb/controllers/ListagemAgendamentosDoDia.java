/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package agendaalineweb.controllers;

import agendaalineweb.entities.Agendamento;
import agendaalineweb.entities.Cliente;
import agendaalineweb.entities.Procedimento;
import agendaalineweb.entities.Usuario;
import agendaalineweb.models.AgendamentoModel;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ListagemAgendamentosDoDia", urlPatterns = {"/listagem-agendamentos-dia"})
public class ListagemAgendamentosDoDia extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

        if (usuario != null) {
            AgendamentoModel agendamentoModel = new AgendamentoModel();
            ProcedimentoModel procedimentoModel = new ProcedimentoModel();
            java.util.Date date = new java.util.Date();
            java.sql.Date datesql = new java.sql.Date(date.getTime());
            ArrayList<Agendamento> agendamentos = agendamentoModel.selectByData(datesql);
            int[] idsProcedimentos = new int[agendamentos.size()];
            int[] idsClientes = new int[agendamentos.size()];
            for (int i = 0; i < agendamentos.size(); i++) {
                idsProcedimentos[i] = agendamentos.get(i).getIdProcedimento();
                idsClientes[i] = agendamentos.get(i).getIdCliente();
            }
            ArrayList<Procedimento> procedimentos;
            ArrayList<Cliente> clientes;
            try {
                procedimentos = procedimentoModel.getProcedimentosByIds(idsProcedimentos);
                ClienteModel modelCliente = new ClienteModel();
                clientes = modelCliente.getClientesByIds(idsClientes);
                request.setAttribute("clientes", clientes);
                request.setAttribute("agendamentos", agendamentos);
                request.setAttribute("procedimentos", procedimentos);
                String caminhoContexto = request.getContextPath();
                request.setAttribute("caminhoContexto", caminhoContexto);
                request.getRequestDispatcher("WEB-INF/agendamentosDoDia.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ListagemAgendamentosDoDia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            response.sendRedirect("login");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
