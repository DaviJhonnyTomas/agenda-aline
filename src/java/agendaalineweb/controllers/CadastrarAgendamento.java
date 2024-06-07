/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package agendaalineweb.controllers;

import agendaalineweb.entities.Agendamento;
import agendaalineweb.entities.Cliente;
import agendaalineweb.entities.Procedimento;
import agendaalineweb.models.AgendamentoModel;
import agendaalineweb.models.ClienteModel;
import agendaalineweb.models.DataModel;
import agendaalineweb.models.ProcedimentoModel;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Utilizador
 */
@WebServlet(name = "CadastrarAgendamento", urlPatterns = {"/cadastrar-agendamento"})
public class CadastrarAgendamento extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AgendamentoModel agendamentoModel = new AgendamentoModel();
        ArrayList<Agendamento> agendamentos = agendamentoModel.selectAll();
        int[] idsProcedimentos = new int[agendamentos.size()];
        int[] idsClientes = new int[agendamentos.size()];
        for (int i = 0; i < agendamentos.size(); i++) {
            idsProcedimentos[i] = agendamentos.get(i).getIdProcedimento();
            idsClientes[i] = agendamentos.get(i).getIdCliente();
        }
        ArrayList<Procedimento> procedimentosAg;
        ArrayList<Cliente> clientesAg;
        try {
            ProcedimentoModel modelProcedimento = new ProcedimentoModel();
            procedimentosAg = modelProcedimento.getProcedimentosByIds(idsProcedimentos);
            ClienteModel modelCliente = new ClienteModel();
            clientesAg = modelCliente.getClientesByIds(idsClientes);
            request.setAttribute("clientesAg", clientesAg);
            request.setAttribute("procedimentosAg", procedimentosAg);
            String caminhoContexto = request.getContextPath();
            request.setAttribute("caminhoContexto", caminhoContexto);
            request.setAttribute("agendamentos", agendamentos);
            ArrayList<Procedimento> procedimentos = modelProcedimento.selectAll();
            request.setAttribute("procedimentos", procedimentos);
            ArrayList<Cliente> clientes = modelCliente.selectAll();
            request.setAttribute("clientes", clientes);
            request.getRequestDispatcher("WEB-INF/pageAgendamentos.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarAgendamento.class.getName()).log(Level.SEVERE, null, ex);
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
        String idCliente = request.getParameter("idCliente");
        String idProcedimento = request.getParameter("idProcedimento");
        String hora = request.getParameter("hora");
        String data = request.getParameter("data"); 
        System.out.println(data);
        DataModel dataModel = new DataModel();
        LocalDate dataConvertida = LocalDate.parse(data);
        Agendamento agendamento = new Agendamento(Integer.parseInt(idProcedimento), LocalTime.parse(hora), dataConvertida, Integer.parseInt(idCliente));
        AgendamentoModel agendamentoModel = new AgendamentoModel();
        agendamentoModel.insert(agendamento);
        String caminhoContexto = request.getContextPath();
        response.sendRedirect(caminhoContexto + "/cadastrar-agendamento");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
