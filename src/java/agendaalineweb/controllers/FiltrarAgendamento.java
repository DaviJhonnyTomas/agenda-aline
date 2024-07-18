/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package agendaalineweb.controllers;

import java.sql.Date;
import agendaalineweb.models.ClienteModel;
import agendaalineweb.entities.Cliente;
import agendaalineweb.entities.Agendamento;
import agendaalineweb.models.AgendamentoModel;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Utilizador
 */
@WebServlet(name = "FiltrarAgendamento", urlPatterns = {"/filtrar-agendamento"})
public class FiltrarAgendamento extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nomeCliente = request.getParameter("cliente");
        String data = request.getParameter("data");
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dataFormatada = null;
        java.sql.Date dataFormatadaSql = null;
        try {
            if(!data.isEmpty()){
                dataFormatada = formatador.parse(data);
                dataFormatadaSql = new java.sql.Date(dataFormatada.getTime());
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
           }
        

        //1° caso: filtrar somente pelo nome da cliente
        //2° caso: filtrar somente pela data
        //3° caso: filtrar pelos dois
        //1°:
        ClienteModel clienteModel = new ClienteModel();
        ArrayList<Agendamento> agendamentos = null;
        AgendamentoModel agendamentoModel = new AgendamentoModel();
        if (!data.isEmpty() && !nomeCliente.isEmpty()) { 
            //empty => vazio
            //filtrar somente pelos dois (nome da cliente e data)
            try {
            agendamentos = agendamentoModel.selectByDataAndNome(dataFormatadaSql, nomeCliente);
            } catch (SQLException ex) {
                Logger.getLogger(FiltrarAgendamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (!data.isEmpty()) {
            //filtrar somente pela data
            agendamentos = agendamentoModel.selectByData(dataFormatadaSql);
        } else if (!nomeCliente.isEmpty()) {
            //filtrar somente pelo nome da cliente
            System.out.println("======================================================= caiu aqui");
            ArrayList<Cliente> clientesEncontrados = clienteModel.selectByNome(nomeCliente);
            int[] idsClientes = new int[clientesEncontrados.size()];
            for (int i = 0; i < clientesEncontrados.size(); i++) {
                idsClientes[i] = clientesEncontrados.get(i).getId();
            }
            
            
            try {
                agendamentos = agendamentoModel.selectAgendamentosByIdsClientes(idsClientes);
                
               
            } catch (SQLException ex) {
                Logger.getLogger(FiltrarAgendamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        request.setAttribute("agendamentos", agendamentos);
        String caminhoContexto = request.getContextPath();
        request.setAttribute("caminhoContexto", caminhoContexto);
        try {
    // Código que pode lançar WaspException ou NullPointerException
            request.getRequestDispatcher("WEB-INF/pageAgendamentos.jsp").forward(request, response);

} catch (Exception e) {
    Throwable cause = e.getCause();
    if (cause instanceof NullPointerException) {
        // Tratamento específico para NullPointerException dentro de WaspException
        System.out.println("Causa raiz é NullPointerException: " + cause.getMessage());
        // Pode adicionar mais tratamento aqui, como logging ou recuperação de falhas
    } else {
        // Tratamento específico para WaspException sem NullPointerException como causa raiz
        System.out.println("WaspException capturada: " + e.getMessage());
        // Pode adicionar mais tratamento aqui, como logging ou recuperação de falhas
    }
} catch (NullPointerException e) {
    // Tratamento específico para NullPointerException não encapsulada
    System.out.println("NullPointerException capturada: " + e.getMessage());
    // Pode adicionar mais tratamento aqui, como logging ou recuperação de falhas
} catch (Exception e) {
    // Tratamento para outras exceções
    System.out.println("Outra exceção capturada: " + e.getMessage());
    // Pode adicionar mais tratamento aqui, como logging ou recuperação de falhas
}
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
