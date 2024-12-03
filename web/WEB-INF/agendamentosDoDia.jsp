<%-- 
    Document   : agendamentosDoDia
    Created on : 17/05/2024, 17:49:44
    Author     : Utilizador
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="agendaalineweb.models.ClienteModel"%>
<%@ page import="agendaalineweb.entities.Cliente"%>
<%@ page import="agendaalineweb.models.ProcedimentoModel"%>
<%@ page import="agendaalineweb.models.Agendamento_ProcedimentoModel"%>
<%@ page import="java.util.ArrayList"%>
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
                height: 100%;
            }

            #container-conteudo {
                height: 100%;
            }

            #btn-agenda{
                margin-bottom: 20px;
                border: #B7044A 3px solid;
                border-radius: 5px;
                background-color: #b704499a;
                color: whitesmoke;
                padding: 8px;
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
                            <a class="nav-link text-link text-dark" href="${caminhoContexto}/cadastrar-procedimento">Serviços</a> 
                        </li>
                        <li class="nav-item li-nav">
                            <a class="nav-link text-link text-dark" href="${caminhoContexto}/cadastrar-agendamento">Agendamento</a>
                        </li>
                        <li class="nav-item li-nav">
                            <a class="nav-link text-link text-dark" href="${caminhoContexto}/gerenciamento-negocio">Meu negócio</a>
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
                    <div class="d-flex justify-content-between">
                        <h2 id="titulo">Agendamentos do dia</h2>

                        <div class="align-content-end mb-4" >
                            <a href="${caminhoContexto}/listagem-agendamento-semanal" id="btn-agenda" >Agenda Semanal</a>

                        </div>
                    </div>
                    <ul>
                        <c:forEach varStatus="status" var="agendamento" items="${agendamentos}">
                            <li>
                                <div class="item-list-agendamento d-flex justify-content-between">
                                    <span class="subitem-agendamento">
                                        <% 
                                                        Agendamento agendamento = (Agendamento) pageContext.getAttribute("agendamento");
                                                        ClienteModel clienteModel = new ClienteModel();
                                                        Cliente cliente = clienteModel.selectById(agendamento.getIdCliente());
                                                        pageContext.setAttribute("cliente", cliente);
                                        %>

                                        ${cliente.nome}
                                    </span>
                                    <span class="subitem-agendamento">${agendamento.data}</span>
                                    <span class="subitem-agendamento">${agendamento.hora}</span>
                                    <span class="subitem-agendamento">
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


                                    </span>
                                    <%
                                        double somaValoresProcedimentos = 0;
                                        for(Procedimento procedimento : procedimentos){
                                           somaValoresProcedimentos = somaValoresProcedimentos + procedimento.getValor();
                                        }
                                        pageContext.setAttribute("somaValoresProcedimentos", somaValoresProcedimentos);
                                    %>     
                                    <span class="subitem-agendamento">€ ${somaValoresProcedimentos}</span>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <div id="footer" class="fixed-bottom text-center py-3">
            <span class=" mx-auto">www.alinesimao.pt</span>
        </div>

    </body>

</html>