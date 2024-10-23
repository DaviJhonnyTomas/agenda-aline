<%-- 
    Document   : pageAgendamentos
    Created on : 17/05/2024, 17:54:51
    Author     : Utilizador
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="agendaalineweb.models.ClienteModel"%>
<%@ page import="agendaalineweb.entities.Cliente"%>
<%@ page import="agendaalineweb.models.ProcedimentoModel"%>
<%@ page import="agendaalineweb.entities.Procedimento"%>
<%@ page import="agendaalineweb.entities.Agendamento"%>
<%@ page import="agendaalineweb.models.DataModel"%>
<%@ page import="agendaalineweb.models.Agendamento_ProcedimentoModel"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="pt-br">

    <head>

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Agenda Aline - Clientes</title>
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
            .col-md-11{
                max-width: 89%;
                margin-right: 1%;
            }
            #dropdownButtonProcedimentos{
                width: 100%;
                text-align: start;
                background-color: #DEBDC6;
                border-radius: 10px;
                border: 3px solid #B7044A;
                color: #555;
            }
            #dropdownButtonProcedimentos::after{
                margin-left: 765px;
                color: black;
            }
            #dropdownMenuProcedimentos{
                width: 100%;
            }
            .item-procedimento{
                margin-left: 10px;
            }
            #seletor-procedimento{
                width: 100%;
                border-radius: 10px;
                border: #B7044A solid 3px;
                background-color: #DEBDC6;
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

                                <div class="form-check">


                                </div>



                            </div>
                        </div>
                    </ul>


                </div>
            </nav>

        </div>

        <div id="container-main">
            <div id="container-principal">
                <div class="mx-auto" id="container-conteudo">
                    <h2 id="titulo">Agendamentos</h2>
                    <form action="${caminhoContexto}/cadastrar-agendamento" method="post">
                        <div class="input-group mb-3">

                            <select name="idCliente" class="form-control input-text custom-select" id="inputGroupSelect01">
                                <option value="0" selected>Escolher cliente</option>
                                <c:forEach var="cliente" items="${clientes}">
                                    <option value="${cliente.id}">${cliente.nome}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="input-group mb-3 form-group" >
                            <button class="btn dropdown-toggle" type="button" id="dropdownButtonProcedimentos"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Selecione os procedimentos
                            </button>


                            <div class="dropdown-menu" id="dropdownMenuProcedimentos" aria-labelledby="dropdownMenuButton">
                                <c:forEach var="procedimento" items="${procedimentos}" varStatus="i">
                                    <div class="item-procedimento">
                                        <input type="checkbox" value="${procedimento.id}" name="idProcedimento${i.index}" id="defaultCheck1">
                                        ${procedimento.nome}
                                    </div>                                   

                                </c:forEach>


                            </div>


                        </div>

                        <div class="form-group ">
                            <input name="data" type="date" class="form-control input-text" id="input-data" placeholder="Data">

                        </div>
                        <div class="form-group ">
                            <input name="hora" type="time" class="form-control input-text" id="input-hora" placeholder="Hora">

                        </div>
                        <button type="submit" class="btn-cadastro">Cadastrar</button>
                    </form>


                </div>
                <div class="">


                    <div class="row justify-content-end" id="conteudo-tabela">
                        <div class="col-md-11">


                            <div id="table-head" class="d-flex justify-content-around">
                                <div class="container-thitem">
                                    <span class="th-item">
                                        Cliente
                                    </span>
                                </div>
                                <div class="container-thitem">
                                    <span class="th-item">
                                        Procedimento

                                    </span>
                                </div>
                                <div class="container-thitem">
                                    <span class="th-item">
                                        Data
                                    </span>
                                </div>
                                <div class="container-thitem">
                                    <span class="th-item">
                                        Hora
                                    </span>
                                </div>
                                <div class="icon" id="icon-filter">

                                    <button id="btn-filtro" data-toggle="modal" data-target="#ExemploModalCentralizado">
                                        <i class="ph ph-sliders-horizontal"></i>
                                    </button>

                                </div>
                            </div>
                            <!-- Modal -->
                            <div class="modal fade" id="ExemploModalCentralizado" tabindex="-1" role="dialog"
                                 aria-labelledby="TituloModalCentralizado" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="TituloModalCentralizado">Filtrar</h5>
                                            <button id="btn-close-modal"  type="button"  data-dismiss="modal" aria-label="Fechar">
                                                <span id="x-close" aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form action="${caminhoContexto}/filtrar-agendamento" method="GET">

                                                <input id="input-filtrar" class="form-control mb-3" type="text" placeholder="Digite o nome do cliente" name="cliente">
                                                <input type="date" class="form-control mb-3" placeholder="Selecione a data desejada" name="data">

                                                <div class="modal-footer d-flex justify-content-center">

                                                    <button id="btn-filtrar" type="submit" class="btn">Filtrar pelo agendamento</button>
                                                </div>

                                            </form>
                                        </div>

                                    </div>
                                </div>
                            </div>


                            <table class="table">

                                <tbody>
                                    <c:forEach varStatus="status" var="agendamento" items="${agendamentos}">

                                        <tr>
                                            <td class="td-table">
                                                <% 
                                                    Agendamento agendamento = (Agendamento) pageContext.getAttribute("agendamento");
                                                    ClienteModel clienteModel = new ClienteModel();
                                                    Cliente cliente = clienteModel.selectById(agendamento.getIdCliente());
                                                    pageContext.setAttribute("cliente", cliente);
                                                %>

                                                ${cliente.nome}
                                            </td>
                                            <td class="td-table">
                                                <% 
                                                    
                                                   Agendamento_ProcedimentoModel agProcedimentoModel = new Agendamento_ProcedimentoModel();
                                                   ArrayList<Procedimento> procedimentos = agProcedimentoModel.getProcedimentosByIdAgendamento(agendamento.getId());
                                                   pageContext.setAttribute("procedimentos", procedimentos);
                                                %>
                                                <ul>

                                                    <c:forEach var="procedimento" items="${procedimentos}">
                                                        <li>${procedimento.nome}</li>


                                                    </c:forEach>

                                                </ul>
                                            </td>
                                            <td class="td-table">
                                                <%
                                                   DataModel dataModel = new DataModel();
                                                   String dataConvertida = dataModel.converterLocalDateParaDataString(agendamento.getData());
                                                   pageContext.setAttribute("dataConvertida", dataConvertida);
                                                %>
                                                ${dataConvertida}
                                            </td>
                                            <td class="td-table">
                                                ${agendamento.hora}
                                            </td>
                                            <td id="tb-funcoes">
                                                <a href="${caminhoContexto}/editar-agendamento?id=${agendamento.id}&idCliente=${agendamento.idCliente}&data=${agendamento.data}&hora=${agendamento.hora}"><i class="ph ph-pencil"></i></a> <!-- ao clicar neste botão, o id chegará no método doGet (EditarCliente) -->
                                                <form action="excluir-agendamento" method="post">
                                                    <input name="id" value="${agendamento.id}" hidden="true">
                                                    <button id="btnExcluir" type="submit"><i class="ph ph-trash"></i></button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <%
                                    String modalParam = (String) request.getAttribute("modal");
                                    if (modalParam != null) {
                                
                                        if ("modalEditarAgendamento".equals(modalParam)) {
                            %>
                            <script>

                                $(document).ready(function () {
                                    $('#modalEditarAgendamento').modal('show');
                                });
                            </script>
                            <%
                                    }
                               }
                            %>
                            <div class="modal fade" id="modalEditarAgendamento" tabindex="-1" role="dialog"
                                 aria-labelledby="TituloModalCentralizado" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="TituloModalCentralizado">Editar</h5>
                                            <button id="btn-close-modal" type="button" data-dismiss="modal"
                                                    aria-label="Fechar">
                                                <span id="x-close" aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form action="editar-agendamento" method="post">
                                                <div class="form-group">
                                                    <input name="id" type="text" class="form-control input-text" id="input-nome" placeholder="Nome" value="${agendamento.id}" hidden="true" >
                                                </div>
                                                <div class="input-group mb-3">
                                                    <select name ="idCliente" class="form-control input-text custom-select" id="inputGroupSelect01">
                                                        <option value="${clienteSelecionado.id}" selected>${clienteSelecionado.nome}</option>
                                                        <c:forEach var="cliente" items="${clientes}">
                                                            <c:if test="${cliente.id != clienteSelecionado.id}">
                                                                <option value="${cliente.id}">${cliente.nome}</option>
                                                            </c:if>

                                                        </c:forEach>
                                                    </select>
                                                </div>



                                                <div class="input-group mb-3 form-group" >
                                                    <button class="btn dropdown-toggle" type="button" id="dropdownButtonProcedimentos"
                                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                        Selecione os procedimentos
                                                    </button>


                                                    <div class="dropdown-menu" id="dropdownMenuProcedimentos" aria-labelledby="dropdownMenuButton">
                                                        <c:forEach var="procedimentoSelecionado" items="${procedimentosSelecionados}" varStatus="i">
                                                            <div class="item-procedimento">
                                                                <input type="checkbox" value="${procedimentoSelecionado.id}" name="idProcedimentoStart${i.index}" id="defaultCheck1" checked="true">
                                                                ${procedimentoSelecionado.nome}
                                                            </div>                                   

                                                        </c:forEach>
                                                        <c:forEach var="procedimento" items="${procedimentos2}" varStatus="i">
                                                            <div class="item-procedimento">
                                                                <input type="checkbox" value="${procedimento.id}" name="idProcedimentoEnd${i.index}" id="defaultCheck1" >
                                                                ${procedimento.nome}
                                                            </div>   
                                                        </c:forEach>


                                                    </div>


                                                </div>
                                                <div class="form-group ">
                                                    <input name="data" type="date" class="form-control input-text" id="input-data" placeholder="Data" value="${agendamento.data}">
                                                    <div class="form-group ">
                                                        <input name="hora" type="time" class="form-control input-text" id="input-hora" placeholder="Hora" value="${agendamento.hora}">

                                                    </div>
                                                    <input type="submit" class="btn-cadastro" value="editar">



                                                    </form>
                                                </div>

                                        </div>
                                    </div>
                                </div>


                            </div>

                            <div class="modal" id="modalErro" tabindex="-1" role="dialog">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title">Erro</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <p>${modalErro}</p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                                        </div>
                                    </div>
                                </div>
                                <%
                            String modalParamErro = (String) request.getAttribute("modalErro");
                            if (modalParamErro != null) {
                                %>
                                <script>

                                    $(document).ready(function () {
                                        $('#modalErro').modal('show');
                                    });
                                </script>
                                <%
                                    }
                                %>
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