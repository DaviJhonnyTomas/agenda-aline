<%-- 
    Document   : pageGerenciamentoNegocio
    Created on : 29/08/2024, 18:22:38
    Author     : Utilizador
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

    <head>

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Agenda Aline - Gerenciamento de Negócio</title>
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
            .settings-card {
                width: 60%;
                margin-bottom: 100px;
            }
            .profile-card{
                margin-bottom: 100px;
                height: 87%;
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

        <div class="container d-flex mt-3">
            <div class="profile-card d-flex flex-column mr-3">
                <div id="circ-cor-primaria" class="mx-auto"></div>
                <div id="circ-cor-secundaria" class=""></div>
                <button class="btn mx-auto" id="btn-config-estilo">Configurações de estilo</button>
            </div>

            <div class="settings-card d-flex flex-column">
                <div class="header">
                    <h2>Gerenciamento de negocio</h2>

                </div>
                <script>
                    function ativarModoEdicao() {
                        var spanNome = document.getElementById("span-nome");
                        var inputNome = document.getElementById("input-nome");

                        spanNome.hidden = true;
                        inputNome.hidden = false;

                        var spanEmail = document.getElementById("span-email");
                        var inputEmail = document.getElementById("input-email");

                        spanEmail.hidden = true;
                        inputEmail.hidden = false;

                        var spanTelefone = document.getElementById("span-telefone");
                        var inputTelefone = document.getElementById("input-telefone");

                        spanTelefone.hidden = true;
                        inputTelefone.hidden = false;

                        var spanEndereco = document.getElementById("span-endereco");
                        var inputEndereco = document.getElementById("input-endereco");

                        spanEndereco.hidden = true;
                        inputEndereco.hidden = false;

                        var spanNif = document.getElementById("span-nif");
                        var inputNif = document.getElementById("input-nif");

                        spanNif.hidden = true;
                        inputNif.hidden = false;

                        var btnEditar = document.getElementById("btn-editar");
                        var btnSalvar = document.getElementById("btn-salvar");

                        btnEditar.hidden = true;
                        btnSalvar.hidden = false;



                    }






                    function salvar() {

                        
                        // Cria um formulário dinâmico para exclusão
                        var form = document.createElement("form");
                        form.method = "post";
                        form.action = "editar-negocio";
                        form.acceptCharset = "UTF-8";
                        // Adiciona um campo hidden com o id do negocio

                        var inputId = document.getElementById("input-id");
                        var inputNome = document.getElementById("input-nome");
                        var inputEmail = document.getElementById("input-email");
                        var inputTelefone = document.getElementById("input-telefone");
                        var inputEndereco = document.getElementById("input-endereco");
                        var inputNif = document.getElementById("input-nif");
                        form.appendChild(inputId);
                        form.appendChild(inputNome);
                        form.appendChild(inputEmail);
                        form.appendChild(inputTelefone);
                        form.appendChild(inputEndereco);
                        form.appendChild(inputNif);

                        console.log("editando negocio: " + inputId.value);

                        console.log("ID: " + inputId.value);
                        alert("Nome: " + inputNome.value);
                        console.log("Email: " + inputEmail.value);




                        // Adiciona o formulário ao body e o submete
                        document.body.appendChild(form);
                        form.submit();

                    }
                </script>


                <div class="settings-item">
                    <i class="ph ph-hand-coins mr-2"></i>
                    <div class="value">
                        <span>Nome do negócio:</span>
                        <span id="span-nome">${negocio.nome}</span>


                        <input id="input-nome" type="text" name="nome" value="${negocio.nome}" hidden>
                        <input id="input-id" value="${negocio.id}" name="id" hidden>




                    </div>
                </div>
                <div class="settings-item">
                    <i class="ph ph-envelope mr-2"></i>
                    <div class="value">
                        <span>E-mail:</span>
                        <span id="span-email">${negocio.email}</span>
                        <input id="input-email" type="text" name="email" value="${negocio.email}" hidden>
                    </div>
                </div>
                <div class="settings-item">
                    <i class="ph ph-device-mobile mr-2"></i>
                    <div class="value">
                        <span>Telefone:</span>
                        <span id="span-telefone">${negocio.telefone}</span>
                        <input id="input-telefone" type="text" name="telefone" value="${negocio.telefone}" hidden>
                    </div>
                </div>
                <div class="settings-item">
                    <i class="ph-fill ph-map-pin-area mr-2"></i>
                    <div class="value">
                        <span >Morada:</span>
                        <span id="span-endereco">${negocio.endereco}</span>
                        <input id="input-endereco" type="text" name="endereco" value="${negocio.endereco}" hidden>
                    </div>
                </div>
                <div class="settings-item">
                    <i class="ph-fill ph-currency-eur mr-2"></i>
                    <div class="value">
                        <span>NIF:</span>
                        <span id="span-nif">${negocio.nif}</span>
                        <input id="input-nif" type="text" name="nif" value="${negocio.nif}" hidden>
                    </div>
                </div>
                <div class="settings-item">
                    <i class="ph-fill ph-tip-jar mr-2"></i>
                    <div class="value">
                        <span>Plano:</span>
                        <span>${negocio.plano} - €${negocio.valor}</span>

                    </div>
                </div>
                <div class="settings-item">
                    <i class="ph-fill ph-user-circle-gear mr-2"></i>
                    <div class="value">
                        <span>Administrador:</span>
                        <span>${administrador}</span>

                    </div>
                </div>
                <div class="d-flex justify-content-center">
                    <button  class="btn button mr-2 " onclick="ativarModoEdicao()" id="btn-editar">Editar</button>
                    <button onclick="salvar()" id="btn-salvar" class="btn button " hidden>Salvar</button>

                </div>

            </div>

        </div>

        <div id="footer" class="fixed-bottom text-center py-3">
            <span class=" mx-auto">www.alinesimao.pt</span>
        </div>

    </body>

</html>