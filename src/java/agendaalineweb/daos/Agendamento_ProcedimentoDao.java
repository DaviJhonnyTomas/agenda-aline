/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendaalineweb.daos;

import agendaalineweb.conect.Conexao;
import agendaalineweb.entities.Procedimento;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Agendamento_ProcedimentoDao {

    public ArrayList<Procedimento> getProcedimentosByIdAgendamento(int idAgendamento) throws SQLException {
        ArrayList<Procedimento> procedimentos = new ArrayList<>();
        String sql = "select * from procedimento p inner join agendamento_procedimento ap on p.id = ap.idProcedimento where ap.idAgendamento = ?";
        Connection conn = new Conexao().getConnection();
        PreparedStatement estadoPreparado = conn.prepareStatement(sql);
        estadoPreparado.setInt(1, idAgendamento);
        ResultSet rs = estadoPreparado.executeQuery();
        
        Procedimento procedimento = null;
        while (rs.next() == true) {            
            procedimento = new Procedimento(rs.getInt("id"), rs.getString("nome"), rs.getString("duracao"), rs.getDouble("valor"), rs.getInt("idUsuario") );
            procedimentos.add(procedimento);
        }
        
        conn.close();
        estadoPreparado.close();
        return procedimentos;
        
    }
    
}
