/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendaalineweb.models;

import agendaalineweb.daos.NegocioDao;
import agendaalineweb.entities.Negocio;
import java.sql.SQLException;

/**
 *
 * @author Utilizador
 */
public class NegocioModel {

    public Negocio getNegocioById(int idNegocio) {
        NegocioDao negocioDao = new NegocioDao();
        Negocio negocio= negocioDao.getNegocioById(idNegocio);
        return negocio;
    }

    public void update(Negocio negocio) {
        NegocioDao negocioDao = new NegocioDao();
        negocioDao.update(negocio);
    }
    
}
