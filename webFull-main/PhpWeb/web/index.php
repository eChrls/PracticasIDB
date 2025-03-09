<?php
session_start();

$url = "http://host.docker.internal:8080/apiWeb/getUser?msisdn=";// url a la que llamamos

if (isset($_POST["msisdn"])) { // si existe el post de msisdn es decir se ha enviado el formulario y hay un input con name msisdn coge el valor

    $msisdn = trim($_POST["msisdn"]);//quitamos los espacios en blanco por si el usuario es tontito

    if (empty($msisdn)) { // miramos si el usuario no ha puesto nada en el input
        $emptyMsisdn = "Enter the phone number, do not leave it blank.";
    } else {
        
        $curl = curl_init(); // uniciamos la funcion de las llamadas
        $url .= $msisdn; // le añadimos el msisdn a la url

        curl_setopt($curl, CURLOPT_URL, $url); // añadimos la url al curl
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true); // le decimso al curl que nos va a devolver informacion
        curl_setopt($curl, CURLOPT_HEADER, false); //le decimos que nos da igual el header que nos devuelva

        
        $response = curl_exec($curl); //ejecutamos la llamada y ahi mismo nos da la respuesta
        $httpcode = curl_getinfo($curl, CURLINFO_HTTP_CODE); // cogemos el estado http de la llamada 

        curl_close($curl); // cerramos curl para liberarlo de la memoria

        if (isset($httpcode) && $httpcode == 200) { // miramos si existe le http de la respuesta y si ah sido bueno es decir 200
            $jsonResponse = json_decode($response, true); //transformamos la respuesta en un json
            $id = $jsonResponse["id"]; // cogemos el id del json (que es el id de la base de datos del usuario)
            $_SESSION["idUser"] = $id; // guardamos el id en la sesion
            header("Location: http://localhost/home.php"); // mandamos al usuario a la siguente pagina
            exit(); // ya no va a segur ejecutando nada mas
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
            </ul>
        </nav>
    </header>
    <main class="bg-secondary h-50 d-flex justify-content-center align-self-center flex-column">
        <?php if(!((isset($httpcode) && $httpcode != 200) || isset($emptyMsisdn))){// comprobamos si no hay error o si lo la respuesta ha sido un 200 para ocultar el error
            echo "<p id='error' class='text-center text-danger' hidden>";
        }else{// si hay respuesta pero es negativa mostramos el error
            echo "<p id='error' class='text-center text-danger'> ";
        } ?>
            <?php if (isset($httpcode) && $httpcode != 200) // si la respuesta ha sido mala mostramos mensaje de error
                echo "This number is not subscribed" ?>
            <?php if (isset($emptyMsisdn)) // si la respuesta esta vacia mostramos mensaje de error
                echo "Enter the phone number, do not leave it blank" ?>
            </p>
            <form action="" method="post" class="d-flex justify-content-center align-self-center flex-column w-50">

                <label for="msisdn" class="form-label">Msisdn</label>
                <input type="text" name="msisdn" id="msisdn" class="form-control" value="<?php if (isset($httpcode) && $httpcode !=200 && isset($_POST["msisdn"]))
                    echo $_POST['msisdn'] // si ha habido un error pero el usuario ha puesto texto aqui se volvera a mostrar?>">

                <div id="passwordHelpBlock" class="form-text mt-2">
                    Add the phone number with the prefix and the plus
                </div>
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