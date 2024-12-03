package agendaalineweb.daos;

import agendaalineweb.conect.Conexao;
import agendaalineweb.entities.Negocio;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NegocioDao {

    public Negocio getNegocioById(int idNegocio) {
        String sql = "SELECT * FROM Negocio WHERE id = ?";
        Conexao conexao = new Conexao();
        Connection con = null;
        Negocio negocio = null;
        PreparedStatement estadoPreparado = null;
        try {
            con = conexao.getConnection();

            estadoPreparado = con.prepareStatement(sql);
            estadoPreparado.setInt(1, idNegocio);
            ResultSet rs = estadoPreparado.executeQuery();

            if (rs.next()) { //se tiver um resultado 
                negocio = new Negocio(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("telefone"), rs.getString("endereco"), rs.getInt("nif"), rs.getInt("plano"), rs.getDouble("valor"), rs.getInt("idUsuarioAdm"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally{
            try {
                estadoPreparado.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            
            
        }
        return negocio;
    }

    public void update(Negocio negocio) {
        String sql = "UPDATE Negocio SET nome = ?, email = ?, telefone = ?, endereco = ?, nif = ? WHERE id = ?";

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = new Conexao().getConnection();

            ps = con.prepareStatement(sql);
            ps.setString(1, negocio.getNome());
            ps.setString(2, negocio.getEmail());
            ps.setString(3, negocio.getTelefone());
            ps.setString(4, negocio.getEndereco());
            ps.setInt(5, negocio.getNif());
            ps.setInt(6, negocio.getId());
            
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally{
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            
            
        }

    }

}
