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
    private int idProcedimento;
    private LocalTime hora;
    private LocalDate data;
    private int idCliente;

    public Agendamento(int id, int idProcedimento, LocalTime hora, LocalDate data, int idCliente) {
        this.id = id;
        this.idProcedimento = idProcedimento;
        this.hora = hora;
        this.data = data;
        this.idCliente = idCliente;
    }

    public Agendamento(int idProcedimento, LocalTime hora, LocalDate data, int idCliente) {
        this.idProcedimento = idProcedimento;
        this.hora = hora;
        this.data = data;
        this.idCliente = idCliente;
    }

    public int getId() {
        return id;
    }

    public int getIdProcedimento() {
        return idProcedimento;
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

    public void setIdProcedimento(int idProcedimento) {
        this.idProcedimento = idProcedimento;
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
