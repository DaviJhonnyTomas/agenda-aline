/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendaalineweb.entities;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Dell
 */
public class Agendamento {

    private int id;
    private LocalTime hora;
    private LocalDate data;
    private int idCliente;
    private int idUsuario;

    public Agendamento(int id, LocalTime hora, LocalDate data, int idCliente, int idUsuario) {
        this.id = id;
        this.hora = hora;
        this.data = data;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
    }

    public Agendamento(LocalTime hora, LocalDate data, int idCliente, int idUsuario) {
        this.hora = hora;
        this.data = data;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getId() {
        return id;
    }


    public LocalTime getHora() {
        return hora;
    }
    
    public String getDataString(){
        String dataConvertida = data.getDayOfMonth() + "/" + data.getMonthValue()+ "/" + data.getYear();
        return dataConvertida;
    }
    
    public LocalDate getData() {
        return data;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

}
