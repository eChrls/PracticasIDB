<?php

session_start();// iniciamos la sesion
if (!isset($_SESSION["idUser"])) { // comprobamos si el usuario se ha logeado
    header("Location: index.php"); // en caso negativo lo redirijiomos a la pagina de login
}



$url = "http://host.docker.internal:8080/apiWeb/addComment";// url a la que llamamos

if (isset($_POST["email"]) && isset($_POST["phone"]) && isset($_POST["subject"]) && isset($_POST["message"])) {// comprobamos que se han enviado los datos del formulario

    if ($_POST["email"] == "" || $_POST["phone"] == "" || $_POST["subject"] == "" || $_POST["message"] == "") { // comprobamos que no hay campos vacios
        $error = "All fields are required"; // generamos el mensaje de error

    } else {
        $obj = array("email" => $_POST["email"], "phone" => $_POST["phone"], "subject" => $_POST["subject"], "comment" => $_POST["message"], "idUser" => $_SESSION["idUser"]); //generamos un array con todos los datos
        $json = json_encode($obj);// pasamos ese array a un objeto json

        $curl = curl_init();// uniciamos la funcion de las llamadas

        curl_setopt($curl, CURLOPT_URL, $url);// añadimos la url al curl
        curl_setopt($curl, CURLOPT_POST, 1); // le decimos que en este caso va a ser un metodo POST
        curl_setopt($curl, CURLOPT_HEADER, false); //le decimos que nos da igual el header que nos devuelva
        curl_setopt($curl, CURLOPT_POSTFIELDS, $json); //que le vamos a pasar el json por el cuerpo de la llamada
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1); //que esto va a devolver un resultado
        curl_setopt($curl, CURLOPT_HTTPHEADER, array('Content-Type:application/json')); // y que el tipo que le enviamos es un json

        $result = curl_exec($curl);//ejecutamos la llamada y ahi mismo nos da la respuesta
        $httpcode = curl_getinfo($curl, CURLINFO_HTTP_CODE); // cogemos el estado http de la llamada 

        curl_close($curl);// cerramos curl para liberarlo de la memoria

        if (isset($httpcode) && $httpcode == 200) { // miramos si existe le http de la respuesta y si ah sido bueno es decir 200
            $success = "The comment has been added successfully"; //generamos el mensaje de exito
        } else {
            $error = "Internal error"; //generamos el mesnaje de fail
        }
    }


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

<body class="vh-100">
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
    <main class="bg-secondary h-50 d-flex justify-content-center align-self-center flex-column">
        <?php
        if (isset($error)) {// si hay respuesta pero es negativa mostramos el error
            echo "<p id='error' class='text-center text-danger'>" . $error;
        } else { // comprobamos si no hay error o si lo la respuesta ha sido un 200 para ocultar el error
            echo "<p id='error' class='text-center text-danger' hidden>";
        }
        ?>
        </p>
        <form action="" method="post" class="d-flex justify-content-center align-self-center flex-column w-50">

            <label for="email" class="form-label mt-2">Email</label>
            <input type="email" name="email" id="email" class="form-control"
                value="<?php if (isset($error) && isset($_POST["email"]))
                    echo $_POST['email'] // si ha habido un error pero el usuario ha puesto texto aqui se volvera a mostrar ?>"> 

                <label for="phone" class="form-label mt-2">Phone</label>
                <input type="tel" name="phone" id="phone" class="form-control"
                    value="<?php if (isset($error) && isset($_POST["phone"]))
                    echo $_POST['phone'] // si ha habido un error pero el usuario ha puesto texto aqui se volvera a mostrar?>">

                <label for="subject" class="form-label mt-2">Subject</label>
                <input type="text" name="subject" id="subject" class="form-control"
                    value="<?php if (isset($error) && isset($_POST["subject"]))
                    echo $_POST['subject'] // si ha habido un error pero el usuario ha puesto texto aqui se volvera a mostrar?>">

                <label for="message" class="form-label mt-2">Message</label>
                <textarea name="message"
                    id="message"><?php if (isset($error) && isset($_POST["message"]))
                    echo $_POST['message'] // si ha habido un error pero el usuario ha puesto texto aqui se volvera a mostrar?></textarea>
                <?php
                if (!isset($httpcode) || isset($httpcode) && $httpcode != 200) {// si no hay llamada o no ha ido bien la respuesta ocultamos el mensaje de correcto
                    echo "<p id='success' class='text-success' hidden>";
                } else {// si la llamada ha ido bien se muestra el mensaje de correcto
                    echo "<p id='success' class='text-success'>" . $success;
                }
                ?>
            </p>
            <button class="btn btn-outline-info w-25 mt-3 m-auto"> send</button>
        </form>
    </main>
    <footer class="h-25 d-flex justify-content-center align-self-cente flex-column">
        <p class="text-center m-auto"> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras auctor porttitor
            dolor, sed malesuada lorem
            gravida eget. Sed congue enim eget accumsan vulputate. Fusce quis rhoncus libero. Donec fermentum felis sed
            sodales sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae;
            Cras ac lectus rhoncus purus maximus vehicula. Nullam sagittis nibh ligula, quis molestie nisi lacinia sed.
            Integer ultricies ut risus quis molestie. Curabitur in venenatis risus. Nunc in tellus non dolor tincidunt
            vehicula. In mollis elit et diam aliquam maximus. Praesent at cursus diam. Morbi lacinia vehicula nisl.
            Suspendisse sed vehicula neque.</p>
        <p class="text-center m-auto">Copyright © 2025</p>
    </footer>
    <!-- js de boostrap-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>

</html>