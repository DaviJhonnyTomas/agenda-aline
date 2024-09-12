/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendaalineweb.models;

import agendaalineweb.daos.Agendamento_ProcedimentoDao;
import agendaalineweb.entities.Procedimento;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Utilizador
 */
public class Agendamento_ProcedimentoModel {
    public ArrayList<Procedimento> getProcedimentosByIdAgendamento(int idAgendamento) throws SQLException{
        Agendamento_ProcedimentoDao agendamento_ProcedimentoDao = new Agendamento_ProcedimentoDao();
        ArrayList<Procedimento> procedimentosDoAgendamento= agendamento_ProcedimentoDao.getProcedimentosByIdAgendamento(idAgendamento);
        return procedimentosDoAgendamento;
        
        
    }
}
