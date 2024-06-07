/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendaalineweb.models;

import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class DataModel {

    public LocalDate converterDataStringParaLocalDate(String dataString) {
        String[] dataSplit = dataString.split("/");
        try {
            int dia = Integer.parseInt(dataSplit[0]);//integer.parseInt > para realizar a covercao de String para Int.
            int mes = Integer.parseInt(dataSplit[1]);//integer.parseInt > para realizar a covercao de String para Int.
            int ano = Integer.parseInt(dataSplit[2]);//integer.parseInt > para realizar a covercao de String para Int.
            return LocalDate.of(ano, mes, dia);
        } catch (NumberFormatException e) {
            System.out.println("Nao foi possivel converter a data");
                
        }
        return null;
    }

    public String converterLocalDateParaDataString(LocalDate data) {
        return data.getDayOfMonth() + "/" + data.getMonthValue() + "/" + data.getYear();
    }
}
