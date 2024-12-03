/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendaalineweb.conect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Dell
 */
public class Conexao {
     public Connection getConnection() throws SQLException{
        Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/agendaaline?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8", "root", "Davi#Tomas9152");
        return conexao;
    }
    
}
