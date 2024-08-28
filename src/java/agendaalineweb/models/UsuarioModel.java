/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendaalineweb.models;

import agendaalineweb.entities.Usuario;
import java.sql.SQLException;

public class UsuarioModel {
    
    public Usuario getUsuario(String email, String senha) throws SQLException{
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario usuario = usuarioDao.getUsuario(email, senha);
        return usuario;
    }
}
