<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UFT-8">
    <meta name="viewport" content="width=device-with, initial-scale=1.0">
    <title>Primeira aula de PW2</title>
</head>
<body>
    <h1>Seja bem vindo a nossa página</h1>
    <form id="meuFormulario" action="/index" method="post">
        <label for="username">Nome de Usuário:</label>
        <input type="text" id="username" name="username" required>
        <br>
        <label for="email">E-mail:</label>
        <input type="email" id="email" name="email" required>
        <br>
        <button type="submit">Enviar</button>
    </form>
</body>