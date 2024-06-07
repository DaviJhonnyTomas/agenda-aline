<%-- 
    Document   : pageAgendamentos
    Created on : 17/05/2024, 17:54:51
    Author     : Utilizador
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        </style>
    </head>

    <body class="d-flex flex-column">
        <div id="container-menu">
            <nav class="navbar navbar-expand-lg navbar-light  mx-auto">
                <a class="navbar-brand " href="${caminhoContexto}/listagem-agendamentos-dia">
                    <img src="imgs/img-aline-simao.jpg " alt="logotipo do site Aline Simão" id="img-logo">
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
                      <div class="input-group mb-3">
                      <select name ="idProcedimento" class="form-control input-text custom-select" id="inputGroupSelect01">
                        <option value="0" selected>Escolher Procedimento</option>
                        <c:forEach var="procedimento" items="${procedimentos}">
                        <option value="${procedimento.id}">${procedimento.nome}</option>
                        </c:forEach>
                      </select>
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
                                            <form action="">

                                                <input id="input-filtrar" class="form-control" type="text" placeholder="Digite o nome do cliente">
                                                <div class="modal-footer d-flex justify-content-center">

                                                    <button id="btn-filtrar" type="button" class="btn">Filtrar pelo agendamento</button>
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
                                                 ${clientesAg[status.index].nome}
                                            </td>
                                            <td class="td-table">
                                                 ${procedimentosAg[status.index].nome}
                                            </td>
                                            <td class="td-table">
                                                 ${agendamento.data}
                                            </td>
                                            <td class="td-table">
                                                 ${agendamento.hora}
                                            </td>
                                            <td id="tb-funcoes">
                                                <i class="ph ph-pencil"></i>
                                                <i class="ph ph-trash"></i>
                                            </td>
                                        </tr>
                                        </c:forEach>
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