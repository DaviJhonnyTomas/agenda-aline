<%-- 
    Document   : pageLogin
    Created on : 27/08/2024, 18:29:01
    Author     : Utilizador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Aline Simão</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="style.css">
    <style>
        body {
            background-color: white;
            font-family: 'Arial', sans-serif;
            display: flex;
            min-height: 100vh;
        }

        .container1 {
            width: 100%;
            height: 100vh;
            display: flex;
            align-items: center;
            border-radius: 5px;

        }

        .row {
            height: 100%;
            width: 100%;
            display: flex;

        }

        .logo {
            text-align: center;

        }

        .logo h1 {
            font-size: 3em;
            font-weight: bold;
            color: #7a007d;
            /* Cor de destaque da Aline */
        }

        .logo p {
            font-size: 1.2em;
            color: #555;
        }

        .login-form {
            text-align: center;
            width: 70%;

        }

        .login-form h2 {
            color: #7a007d;
            margin-bottom: 20px;
        }

        .form-control {
            border-radius: 3px;
            box-shadow: none;
            border-color: #ccc;
        }

        .btn-primary {
            background-color: #7a007d;
            /* Cor de destaque da Aline */
            border-color: #7a007d;
            color: white;
            padding: 10px 20px;
            border-radius: 3px;
            transition: background-color 0.3s;
        }

        .btn-primary:hover {
            background-color: #550058;
        }

        .logo img {
            width: 100%;
            /* Faz a imagem ocupar 100% da largura da div */
            height: auto;
            /* Ajusta a altura automaticamente para manter as proporções */
            display: block;
            /* Remove espaços em branco extras abaixo da imagem */
        }

        #coluna-logo {
            min-height: 100%;
            display: flex;
            align-items: center;
            background-color: #DEBDC6;

        }

        #coluna-form {
            width: 100%;
            background-image: url(imgs/fundo-tela-login.jpg);
            
            background-size: cover;
            background-position: center;
            display: flex;
            align-items: center;
            justify-content: center;

        }
        #fundo-coluna-form{
            display: flex;
            align-items: center;
            justify-content: center;
            background-color: rgba(255, 255, 255, 0.635);
            width: 100%;
            height: 100%;
        }
        .col-md-7{
            padding: 0;
        }
    </style>
</head>

<body>
    <div class="container1">
        <div class="row">
            <div id="coluna-logo" class="col-md-5 ">
                <div class="logo">
                    <img src="imgs/img-aline-simao.jpg" alt="logo aline simao">
                </div>
            </div>
            <div id="coluna-form" class="col-md-7">
                <div id="fundo-coluna-form">


                    <div class="login-form">
                        <h2>Login</h2>
                        <form action="login" method="POST">
                            <div class="form-group">

                                <input name="email" type="email" class="form-control" id="email" placeholder="Digite seu e-mail"
                                    required>
                            </div>
                            <div class="form-group">
                                <input name="senha" type="password" class="form-control" id="password" placeholder="Digite sua senha"
                                    required>
                            </div>
                            <button type="submit" class="btn btn-primary">Entrar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>
