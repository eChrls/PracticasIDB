<?php
session_start(); //iniciamos la session
$conexion = new PDO("mysql:host=host.docker.internal:3306;dbname=apicrud", "root", ""); // nos conectamos a la base de datos 
$conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION); //añadimos las propiedades de error


if (isset($_POST["user"]) && isset($_POST["password"])) {//comprobamos que se ha enviado la informacion del formulario
    try {
        $query = $conexion->prepare("SELECT * FROM school_user WHERE name=? AND password=?"); // generamos la sentancia sql
        $query->execute([$_POST["user"], $_POST["password"]]); // añadimos los parametros y ejecutamos la sentencia sql
        $user = $query->fetch(); // obtenemos el resultado de la sentencia sql
        if(count($user)<1) { //si hay menos de uno es que no lo ha encontrado por lo tanto no se puede logear
            $error = "not found";
        }else{ // si lo ha encontrado guardamos el id en la session y lo pasamos a la pagina principal
            $_SESSION["id"] = $user["id"];
            header("Location: home.php");
        }
    } catch (\Throwable $th) { // si hay algun error al ejecutar la consulta devolvemos un error
        $error = "internal error";
    }
}


?>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>

<body class="min-vh-100">
    <main class="d-flex flex-column justify-content-center align-items-center align-self-center min-vh-100">
        <?php  if(isset($error)) echo "<p>".$error."</p>"; // si hay algun error se devuelve   ?>
        <form action="" method="post">
            <label for="user"> user:</label>
            <input type="text" id="user" name="user" class="form-control" value="<?php if(isset($_POST["user"])) echo $_POST["user"]; ?>">
            <label for="password"> password:</label>
            <input type="password" id="password" name="password" class="form-control" value="<?php if(isset($_POST["password"])) echo $_POST["password"]; ?>">
            <button class="btn btn-primary">send</button>
            <a href="registration.php">Don't have an account? Sign up</a>
            <a href="updateuser.php">I forgot my password</a>
        </form>

    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>

</html>