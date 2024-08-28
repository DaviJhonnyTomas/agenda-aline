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
        
        ProcedimentoModel modelProcedimento = new ProcedimentoModel();
        ClienteModel modelCliente = new ClienteModel();
        String caminhoContexto = request.getContextPath();
        request.setAttribute("caminhoContexto", caminhoContexto);
        request.setAttribute("agendamentos", agendamentos);
        ArrayList<Procedimento> procedimentos = modelProcedimento.selectAll();
        request.setAttribute("procedimentos", procedimentos);
        ArrayList<Cliente> clientes = modelCliente.selectAll();
        request.setAttribute("clientes", clientes);
        request.getRequestDispatcher("WEB-INF/pageAgendamentos.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idCliente = request.getParameter("idCliente");
        String idProcedimento = request.getParameter("idProcedimento");
        String hora = request.getParameter("hora");
        String data = request.getParameter("data"); 
        
        DataModel dataModel = new DataModel();
        LocalDate dataConvertida = LocalDate.parse(data);
        
        LocalDate dataHoje = LocalDate.now();
        String mensagem = null;
        if(dataConvertida.isAfter(dataHoje) || dataConvertida.isEqual(dataHoje)){
            Agendamento agendamento = new Agendamento(Integer.parseInt(idProcedimento), LocalTime.parse(hora), dataConvertida, Integer.parseInt(idCliente));
            AgendamentoModel agendamentoModel = new AgendamentoModel();
            agendamentoModel.insert(agendamento);
            
            
            
        }else{
            mensagem = "A data informada não pode ser anterior a data de hoje";
            request.setAttribute("modalErro", mensagem);
        }
        AgendamentoModel agendamentoModel = new AgendamentoModel();
        ArrayList<Agendamento> agendamentos = agendamentoModel.selectAll();
        request.setAttribute("agendamentos", agendamentos);
        ClienteModel clienteModel = new ClienteModel();
        ArrayList<Cliente> clientes = clienteModel.selectAll();
        request.setAttribute("clientes", clientes);
        ProcedimentoModel procedimentoModel = new ProcedimentoModel();
         ArrayList<Procedimento> procedimentos = procedimentoModel.selectAll();
        request.setAttribute("procedimentos", procedimentos);
        
        String caminhoContexto = request.getContextPath();
        request.setAttribute("caminhoContexto", caminhoContexto);
        
        request.getRequestDispatcher("WEB-INF/pageAgendamentos.jsp").forward(request, response);
        

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
