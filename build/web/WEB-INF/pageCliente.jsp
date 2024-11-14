<%-- 
    Document   : pageCliente
    Created on : 17/05/2024, 17:53:34
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
            .col-md-11 {
                max-width: 90%;

            }

            #tb-funcoes {
                width: 8%;
            }

            .container-thitem {
                width: 28.6%;
                text-align: center;
            }

            #alert-container {
                position: fixed;
                top: 20px; /* Altere o valor conforme necessário para ajustar a distância do topo */
                left: 50%;
                transform: translateX(-50%);
                z-index: 1050; /* Valor de z-index alto para sobrepor outros elementos */
                width: auto;
                max-width: 90%; /* Limite a largura para evitar problemas em telas pequenas */

                border-radius: 10px;
            }

            /* Animação para a borda piscante */
            @keyframes border-blink {
                0% {
                    border: 3px solid black;
                }
                50% {
                    border: 3px solid transparent;
                }
                100% {
                    border: 3px solid black;
                }
            }

            .alert {
                margin: 0 auto; /* Centraliza o conteúdo do alerta */
                text-align: center;
                border-radius: 10px;
                background-color: whitesmoke;
                animation: border-blink 1s infinite; /* Animação de borda piscante */
            }

            #confirmacao-excluir-container {
                position: fixed;
                top: 20px; /* Altere o valor conforme necessário para ajustar a distância do topo */
                left: 50%;
                transform: translateX(-50%);
                z-index: 1050; /* Valor de z-index alto para sobrepor outros elementos */
                width: auto;
                max-width: 90%; /* Limite a largura para evitar problemas em telas pequenas */

                border-radius: 10px;
            }


        </style>
    </head>

    <body class="d-flex flex-column">
        <div id="alert-container"></div>
        <audio id="alert-sound" src="audios/alert-sound.mp3" preload="auto"></audio>

        <%
            
            if(request.getAttribute("mensagemErro")!=null){
        %>
        <script>
            // Cria o alerta dinamicamente
            var alertHtml = `
<div class="alert alert-danger alert-dismissible fade show" role="alert">
            ${mensagemErro}
<button type="button" class="close" data-dismiss="alert" aria-label="Close">
<span aria-hidden="true">&times;</span>
</button>
</div>
`;

            // Insere o alerta no container
            document.getElementById("alert-container").innerHTML = alertHtml;
            var alertSound = document.getElementById("alert-sound");
            try {
                alertSound.play();
            } catch (error) {
                console.warn("Som de alerta bloqueado pelo navegador: ", error);
            }
        </script>
        <%
        }
                                    
        %>

        <!--caixa de confirmação botão excluir -->
        <div id="confirmacao-excluir-container"></div>

        <div id="confirmacao-excluir-container" hidden>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <div>Você realmente deseja excluir?</div>
                <button type="button" id="btnConfirmar" class="btn btn-danger">Sim</button>
                <button type="button" onclick="fecharConfirmacaoExclusao()" class="btn btn-secondary">Não</button>
            </div>
        </div>

        <script>
            function dispararAlerta(idCliente) {
                // Torna o container de confirmação visível
                var confirmacaoContainer = document.getElementById("confirmacao-excluir-container");
                confirmacaoContainer.hidden = false;
                confirmacaoContainer.style.display = "block";

                // Seleciona o botão de confirmação
                var btnConfirmar = document.getElementById("btnConfirmar");

                // Remove qualquer event listener existente para evitar duplicações
                btnConfirmar.removeEventListener("click", confirmarExclusao);

                // Adiciona um event listener ao botão "Sim" passando o idCliente como parâmetro
                btnConfirmar.addEventListener("click", function () {
                    confirmarExclusao(idCliente);
                });

                // Toca o som do alerta se disponível
                var alertSound = document.getElementById("alert-sound");
                try {
                    alertSound.play();
                } catch (error) {
                    console.warn("Som de alerta bloqueado pelo navegador: ", error);
                }
            }



            function confirmarExclusao(confirmado) {
                if (confirmado && confirmado.dataset) {
                    let idCliente = confirmado.dataset.id;

                    console.log(idCliente);
                    // Cria um formulário dinâmico para exclusão
                    var form = document.createElement("form");
                    form.method = "post";
                    form.action = "excluir-cliente";

                    // Adiciona um campo hidden com o id do cliente
                    var inputId = document.createElement("input");
                    inputId.type = "hidden";
                    inputId.name = "id";
                    inputId.value = idCliente;

                    console.log(inputId.value);

                    form.appendChild(inputId);

                    // Adiciona o formulário ao body e o submete
                    document.body.appendChild(form);
                    form.submit();
                } else {
                    // Remove o alerta de confirmação se o usuário clicar em "Não"
                    document.getElementById("confirmacao-excluir-container").innerHTML = "";
                }
            }
            function fecharConfirmacaoExclusao() {
                document.getElementById("confirmacao-excluir-container").hidden = true;
            }

        </script>



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
                    <h2 id="titulo">Cadastro de clientes</h2>
                    <form action="cadastrar-cliente" method="post">
                        <div class="form-group">

                            <input name="nome" type="text" class="form-control input-text" id="input-nome" placeholder="Nome">

                        </div>
                        <div class="form-group">

                            <input name="telefone" type="text" class="form-control input-text" id="input-telefone" placeholder="Telefone">
                        </div>
                        <div class="form-group ">
                            <input name="email" type="email" class="form-control input-text" id="input-email" placeholder="Email">

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
                                        Nome
                                    </span>
                                </div>
                                <div class="container-thitem">
                                    <span class="th-item">
                                        Telefone

                                    </span>
                                </div>
                                <div class="container-thitem">
                                    <span class="th-item">
                                        Email
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
                                 aria-labelledby="TituloModalCentralizado" >
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="TituloModalCentralizado">Filtrar</h5>
                                            <button id="btn-close-modal" type="button" data-dismiss="modal"
                                                    aria-label="Fechar">
                                                <span id="x-close" aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form action="${caminhoContexto}/filtrar-cliente" method="GET">

                                                <input name="cliente" id="input-filtrar" class="form-control" type="text"
                                                       placeholder="Digite o nome do cliente">
                                                <div class="modal-footer d-flex justify-content-center">

                                                    <button id="btn-filtrar" type="submit" class="btn">Filtrar pelo
                                                        nome</button>
                                                </div>

                                            </form>
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


                            <table class="table">

                                <tbody>
                                    <c:forEach var="cliente" items="${clientes}">
                                        <tr>
                                            <td class="td-table">
                                                ${cliente.nome}
                                            </td>
                                            <td class="td-table">
                                                ${cliente.telefone}
                                            </td>
                                            <td class="td-table">
                                                ${cliente.email}
                                            </td>
                                            <td id="tb-funcoes">
                                                <a href="${caminhoContexto}/editar-cliente?id=${cliente.id}&nome=${cliente.nome}&telefone=${cliente.telefone}&email=${cliente.email}&idNegocio=${cliente.idNegocio}"><i class="ph ph-pencil"></i></a> <!-- ao clicar neste botão, o id chegará no método doGet (EditarCliente) -->

                                                <button id="btnExcluir" onclick="dispararAlerta(${cliente.id})"><i class="ph ph-trash"></i></button>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>




                            <div class="modal fade" id="modalEditarCliente" tabindex="-1" role="dialog"
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
                                            <form action="editar-cliente" method="post">
                                                <div class="form-group">
                                                    <input name="id" type="text" class="form-control input-text" id="input-nome" placeholder="Nome" value="${cliente.id}" hidden="true" >
                                                </div>
                                                <div class="form-group">

                                                    <input name="nome" type="text" class="form-control input-text" id="input-nome" placeholder="Nome" value="${cliente.nome}">

                                                </div>
                                                <div class="form-group">

                                                    <input name="telefone" type="text" class="form-control input-text" id="input-telefone" placeholder="Telefone" value="${cliente.telefone}">
                                                </div>
                                                <div class="form-group ">
                                                    <input name="email" type="email" class="form-control input-text" id="input-email" placeholder="Email" value="${cliente.email}">

                                                </div>
                                                <input type="submit" class="btn-cadastro" value="editar">



                                            </form>
                                        </div>

                                    </div>
                                </div>
                            </div>

                            <%
                                String modalParam = (String) request.getAttribute("modal");
                                if ("modalEditarCliente".equals(modalParam)) {
                            %>
                            <script>

                                $(document).ready(function () {
                                    $('#modalEditarCliente').modal('show');
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