<%-- 
    Document   : agendamentosDoDia
    Created on : 17/05/2024, 17:49:44
    Author     : Utilizador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="agendaalineweb.models.ClienteModel"%>
<%@ page import="agendaalineweb.entities.Cliente"%>
<%@ page import="agendaalineweb.models.ProcedimentoModel"%>
<%@ page import="agendaalineweb.models.Agendamento_ProcedimentoModel"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="agendaalineweb.entities.Procedimento"%>
<%@ page import="agendaalineweb.entities.Agendamento"%>
<%@ page import="agendaalineweb.models.DataModel"%>
<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Agenda-Aline</title>

        <script src="https://unpkg.com/@phosphor-icons/web"></script>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
              integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
                integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
                integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>

        <link rel="stylesheet" href="css/style.css">

        <style>
            #container-main {
                /* "../" serve para sair de uma pasta atual*/
                background: url(./imgs/img-fundo.jpg);
                background-attachment: fixed;
                background-size: cover;

            }
            .table {
                margin-bottom: 170px !important;
            }

            #container-conteudo {
                height: 100%;
            }

            .calendar-container {
                display: flex;
                flex-wrap: wrap;
            }

            .horarios-column {
                width: 6%;
                min-height: 500px;
                padding: 10px;
            }



            .table thead th {
                text-align: center;
            }

            .td-table {
                text-align: center;
            }

            .container-thitem-horario {
                width: 4.5%;
            }

            .btn-group,
            #btnExpandirAgendamento {
                width: 100%;
                max-height: 10%;

            }

            #nome-cliente {
                font-size: 80%;
                cursor: pointer;

            }
            .table{
                border: 1px solid #B7044A;
            }
            table{
                border: none;
            }

            .td-table:hover{
                background-color: #debdc66e;
            }

            .container-thitem {
                background-color: #b704499a;
                border: #B7044A 3px solid;
                border-radius: 5px;
                color: whitesmoke;
                padding: 0.5%; /* Aumente o padding para melhor visualização */
                text-align: center;
                width: 7%;
            }

            .table {
                margin-top: 20px;
                width: 100%; /* Garante que a tabela utilize toda a largura disponível */
            }
            #th-horas{
                width: 10%;
            }
            .td-horas {
                width: 10%; /* Coluna das horas */
                background-color: rgba(192, 192, 192, 0.509);
                border: none;
            }

            .td-table {
                width: 12.5%; /* Ajuste para as outras colunas (7 colunas restantes) */
                background-color: rgba(245, 245, 245, 0.4);
                border: 1px solid #B7044A !important;


            }

            .table td {
                border-top: 1px solid #B7044A !important;


            }
            tr{
                line-height: 10px;
            }

            #calendario{
                background-color: rgba(245, 245, 245, 0.4);
                border: 1px solid #B7044A !important;
            }
            #container-calendario{
                margin-top: 20px ;
            }
            .th-item-semanal {

                color: whitesmoke;

                border-radius: 5px;

                text-align: center;

            }

        </style>
    </head>

    <body class="d-flex flex-column">
        <div id="container-menu">
            <nav class="navbar navbar-expand-lg navbar-light  mx-auto">

                <a class="navbar-brand " href="${caminhoContexto}/listagem-agendamentos-dia">
                    <img src="./imgs/img-aline-simao.jpg " alt="logotipo do site Aline Simão" id="img-logo">
                </a>
                <!-- <button style="color: black;" class="navbar-toggler" type="button" data-toggle="collapse"
                     data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Alterna navegação">
                     <span class="navbar-toggler-icon"></span>
                 </button>
                 colocar a classe collapse e o navbar-collapse na div abaixo-->
                <div class="  " id="navbarNav">
                    <ul class="navbar-nav d-flex justify-content-around">
                        <li class="nav-item li-nav">
                            <a class="nav-link text-link text-dark" href="${caminhoContexto}/cadastrar-cliente">Cliente</a>
                        </li>
                        <li class="nav-item li-nav">
                            <a class="nav-link text-link text-dark" href="${caminhoContexto}/cadastrar-procedimento">Procedimento</a> 
                        </li>
                        <li class="nav-item li-nav">
                            <a class="nav-link text-link text-dark" href="${caminhoContexto}/cadastrar-agendamento">Agendamento</a>
                        </li>
                        <li class="nav-item li-nav">
                            <a class="nav-link text-link text-dark" href="page-meuNegocio.html">Meu negócio</a>
                        </li>
                        <div class="dropdown">
                            <button class="btn  dropdown-toggle" type="button" id="dropdownMenuButton"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="ph ph-user-check"></i>
                            </button>

                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                <a class="dropdown-item" href="#">Configurações</a>
                                <a class="dropdown-item" href="#">Sair</a>
                            </div>
                        </div>
                    </ul>


                </div>
            </nav>

        </div>
        <div id="container-main">
            <div id="container-principal">
                <div class="mx-auto" id="container-conteudo">

                    <div id="container-calendario" class="d-flex justify-content-end">
                        <input type="date" id="calendario" value="${dataAtual}">
                    </div>



                    <div class="row justify-content-center" id="conteudo-tabela">
                        <div class="col-12">
                            <div id="table-head" class="d-flex justify-content-around mb-3">
                                <div class="container-thitem-horario text-center">
                                    <span id="th-horas"></span>
                                </div>
                                <c:forEach var="i" begin="0" end="6">
                                    <div class="container-thitem text-center">
                                        <span class="th-item-semanal">${dias[i]}</span><br>
                                        <span class="th-item-semanal">${dataInicial.plusDays(i).getDayOfMonth()}</span> <!-- Mostra o número do dia -->
                                    </div>
                                </c:forEach>

                            </div>

                            <table class="table ">
                                <tbody>
                                    <%/*
                                    ArrayList<Agendamento> agendamentos = request.getAttribute("agendamentos");
                                        
                                    String[] horas = {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00"};
                                    String dias = {"DOM","SEG", "TER", "QUA", "QUI", "SEX", "SAB"};
                                    
                                    for(Agendamento agendamento : agendamentos) {
                                        if(agendamento.getHora() == "08:00"){
                                            
                                        }else if(agendamento.getHora() ==  "08:30"){
                                        
                                        
                                        }else if(agendamento.getHora() ==  "09:00"){
                                        
                                        }
                                    }
                                    
                                    for (String hora : horas){
                                        out.println("<tr>");
                                        out.println("<td>" + hora + "</td>");
                                        
                                        for()
                                    }*/
                                        
                                    // Lista de horas (1ª coluna)
        String[] horas = {"07:00","08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00","18:00","19:00","20:00","21:00","22:00"};
        // Lista de dias (1ª linha)
        String[] dias = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
        
        // Recupera os agendamentos do request
        ArrayList<Agendamento> agendamentos = (ArrayList<Agendamento>) request.getAttribute("agendamentos");
        ClienteModel clienteModel = new ClienteModel();
        // Itera sobre cada hora
        List<String> horasComMinutos = new ArrayList<>();

        // Itera sobre cada hora
        for (int hora = 7; hora <= 23; hora++) {
            // Itera sobre cada minuto
            for (int minuto = 0; minuto < 60; minuto++) {
                // Formata a hora e minuto para ter dois dígitos (ex: 08:00, 08:01, etc.)
                String horaFormatada = String.format("%02d:%02d", hora, minuto);
                horasComMinutos.add(horaFormatada);
            }
        }
        for (String hora : horasComMinutos) {
    // Verifica se o horário atual é uma hora "fixa" (por exemplo, 07:00, 08:00, 09:00, etc.) ou de um agendamento específico com minutos
    boolean exibirHorarioFixo = hora.endsWith(":00");

    // Verifica se há agendamentos para o horário com minutos
    boolean agendamentoComMinutos = false;
    List<Agendamento> agendamentosMinutos = new ArrayList<>();

    for (Agendamento agendamento : agendamentos) {
        String horaAgendamento = agendamento.getHora().toString();  // Horário do agendamento, incluindo minutos
        String horaAgendamentoFixa = horaAgendamento.substring(0, 5); // Pega apenas HH:mm do agendamento

        if (horaAgendamentoFixa.equals(hora)) {
            agendamentosMinutos.add(agendamento);
            agendamentoComMinutos = true;
        }
    }

    // Exibir linha da tabela apenas se for horário fixo ou se houver um agendamento com minutos
    if (exibirHorarioFixo || agendamentoComMinutos) {
        out.println("<tr>");
        // Coluna dos horários (seja fixo ou com minutos)
        out.println("<td class='td-horas'>" + hora + "</td>");

        // Itera sobre cada dia da semana
        for (String dia : dias) {
            boolean agendamentoEncontrado = false;

            // Verifica se há agendamento para o dia e hora atuais
            for (Agendamento agendamento : agendamentosMinutos) {
                String diaSemanaAgendamento = agendamento.getData().getDayOfWeek().toString(); // Dia da semana do agendamento

                if (diaSemanaAgendamento.equals(dia)) {
                    // Exibe o agendamento com o horário completo
                    Cliente cliente = clienteModel.selectById(agendamento.getIdCliente());
                    out.println("<td class='td-table'>");
                    out.println("<div class='btn-group-agendamento' role='group'>");
                    out.println("<div id='nome-cliente' data-toggle='dropdown'>" + cliente.getNome() + "</div>");
                    out.println("<div class='dropdown-menu' aria-labelledby='btnExpandirAgendamento'>");

                    // Lista procedimentos e detalhes do agendamento
                    double somaProcedimentos = 0;
                    for (Procedimento procedimento : agendamento.getProcedimentos()) {
                        out.println("<span class='dropdown-item'>" + procedimento.getNome() + "</span>");
                        somaProcedimentos = somaProcedimentos + procedimento.getValor();
                       
                                                           
                    }
                    LocalDate data = agendamento.getData();
                    String dataConvertida = data.getDayOfMonth() + "/" + data.getMonthValue() +"/"+ data.getYear();
                    out.println("<span class='dropdown-item' >" + dataConvertida + "</span>");
                    out.println("<span class='dropdown-item'>" + agendamento.getHora() + "</span>");
                    out.println("<span class='dropdown-item'>" + somaProcedimentos + "€ </span>");
                    out.println("</div>");
                    out.println("</div>");
                    out.println("</td>");

                    agendamentoEncontrado = true;
                    break;
                }
            }

            // Se não encontrar agendamento para o dia/hora, imprime célula vazia
            if (!agendamentoEncontrado) {
                out.println("<td class='td-table'></td>");
            }
        }
        out.println("</tr>");
    }
}

                                    %>
                                    <!--
                                    <tr>
                                        <td class="td-horas">07:00</td>
                                        <td class="td-table"></td>
                                        <td class="td-table"></td>
                                        <td class="td-table"></td>
                                        <td class="td-table"></td>
                                        <td class="td-table"></td>
                                        <td class="td-table"></td>
                                        <td class="td-table"></td>
                                    </tr>
                                    <tr>
                                        <td class="td-horas">08:00</td>
                                        <td class="td-table"></td>
                                        <td class="td-table">

                                            <div>

                                                <div>
                                                    <div class="btn-group-agendamento" role="group">
                                                        <div id="nome-cliente" data-toggle="dropdown">Jhonata Simão</div>
                                    <!--<div class="dropdown-toggle" id="dropdown-agendamento" data-toggle="dropdown"></div>-->
                                    <!--
             <div class="dropdown-menu" aria-labelledby="btnExpandirAgendamento">
                                         <a class="dropdown-item" href="#">unhas de gel</a>
                                         <a class="dropdown-item" href="#">unhas de gelinho</a>
                                         <a class="dropdown-item" href="#">01/10/2024</a>
                                         <a class="dropdown-item" href="#">08:00</a>

                                     </div>
                                 </div>
                             </div>
                         </div>

                     </td>
                     <td class="td-table"></td>
                     <td class="td-table"></td>
                     <td class="td-table"></td>
                     <td class="td-table"></td>
                     <td class="td-table"></td>
                 </tr>
                                    -->
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="footer" class="fixed-bottom text-center py-3">
            <span class=" mx-auto">www.alinesimao.pt</span>
        </div>

    </body>

</html>