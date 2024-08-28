/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendaalineweb.models;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import agendaalineweb.conect.Conexao;
import java.sql.SQLException;
import agendaalineweb.entities.Usuario;

public class UsuarioDao {
    
    public Usuario getUsuario(String email, String senha) throws SQLException{ 
        String sql = "Select * from Usuario WHERE email = ? AND senha = ?";
        Connection conn = new Conexao().getConnection();
        PreparedStatement estadoPreparado = conn.prepareStatement(sql);
        estadoPreparado.setString(1, email);
        estadoPreparado.setString(2, senha);
        ResultSet rs = estadoPreparado.executeQuery();
        Usuario usuario = null;
        if(rs.next()){
            usuario = new Usuario(rs.getInt("id"), rs.getString("nome"), senha, email, rs.getString("telefone"), rs.getString("endereco"), rs.getInt("idNegocio"));
        }
        return usuario;
    }
}
