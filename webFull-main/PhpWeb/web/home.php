<?php
session_start();// iniciamos la sesion

if (!isset($_SESSION["idUser"])) { // comprobamos si el usuario se ha logeado
    header("Location: index.php"); // en caso negativo lo redirijiomos a la pagina de login
}

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>content</title>
     <!-- Link para el boostrap-->
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
     integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<script src="js/checkLogin.js"></script>
<body>
    <header class="mb-3">
        <nav>
            <ul class="nav nav-pills nav-justified ">
                <li class="nav-item m-auto">
                    <a class="nav-link" href="/"><img src="images/PHP-logo.svg.png" alt="header" width="100"
                            height="100"></a>
                </li>
                <li class="nav-item m-auto">
                    <a class="nav-link text-info" href="/home.php">Content</a>
                </li>
                <li class="nav-item m-auto">
                    <a class="nav-link text-info" href="/comment.php">Comment</a>
                </li>
                <li class="nav-item m-auto">
                    <a class="nav-link text-info" href="/logout.php">Logout</a>
                </li>
            </ul>
        </nav>
    </header>
</body>
</html>