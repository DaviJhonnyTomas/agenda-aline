/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendaalineweb.models;


import agendaalineweb.daos.ProcedimentoDao;
import agendaalineweb.entities.Procedimento;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class ProcedimentoModel {

    

    public ProcedimentoModel() {// Metodo construtor.
       
    }

    public void insert(Procedimento procedimento) {//recebe da interface um cliente(AgendaAline.java).
        ProcedimentoDao daoProcedimento = new ProcedimentoDao();
        daoProcedimento.insert(procedimento);//chamada do metodo insert da DAO.
    }

    public void updateById(Procedimento procedimentoEditado) {//recebemos da interface(AgendaAline.java).
        ProcedimentoDao daoProcedimento = new ProcedimentoDao();
        daoProcedimento.updateById(procedimentoEditado);//chamada do metodo updateById da DAO.
        }

    public ArrayList<Procedimento> selectAll() {//Tipo do retorno do metodo = ArrayList.
        ProcedimentoDao daoProcedimento = new ProcedimentoDao();
        ArrayList<Procedimento> procedimentos = daoProcedimento.selectAll();//chamada do metodo selectAll da DAO.

        return procedimentos;
    }

    public boolean verificarProcedimentoById(int idConvertido) {
        ProcedimentoDao daoProcedimento = new ProcedimentoDao();
        boolean procedimentoExiste = daoProcedimento.verificarProcedimentoById(idConvertido); //chamada do metodo selectAll da DAO.

        return procedimentoExiste;
    }

   

    public ArrayList<Procedimento> selectByNome(String pesquisar) {
        ProcedimentoDao procedimentoDao = new ProcedimentoDao();
        ArrayList<Procedimento> procedimentos = procedimentoDao.selectByNome(pesquisar);// Atualizar nossos procedimentos
        
        return procedimentos;

    }

    public Procedimento selectById(int id) {
        ProcedimentoDao procedimentoDao = new ProcedimentoDao();
        Procedimento procedimento = procedimentoDao.selectById(id);
        return procedimento;
    }

    public ArrayList<Procedimento> getProcedimentosByIds(int[] idsProcedimentos) throws SQLException {
        ProcedimentoDao daoProcedimento = new ProcedimentoDao();
        ArrayList<Procedimento> procedimentos = daoProcedimento.getProcedimentosByIds(idsProcedimentos);
        return procedimentos;
        
    }

    
}