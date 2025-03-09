<?php
$conexion = new PDO("mysql:host=host.docker.internal:3306;dbname=apicrud", "root", "");// nos conectamos a la base de datos 
$conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);//añadimos las propiedades de error

if (isset($_POST["nameCheck"])) { // miramos si se ha enviado los datos de chequeo 
    try {
        $query = $conexion->prepare("SELECT * FROM school_user where name=?");// generamos la sentencia sql
        $query->execute([$_POST["nameCheck"]]); // añadimos lo parametros y la ejecutamos 
        $user = $query->fetch(); // obtenemos lo que haya de la consulta
        if (count($user) < 1) { //si hay menos de uno es que no lo ha encontrado por lo tanto no se puede logear
            $error = "not found";
        } else {
            $checked = $_POST["nameCheck"]; // le añadimso el valor del nombre a la variable checked
        }
    } catch (\Throwable $th) {// si hay algun error al ejecutar la consulta devolvemos un error
        $error = "internal error";
    }
}

if (isset($_POST["name"]) && isset($_POST["password"])) { // comprobamos si se ha enviado el segundo formulario 
    try {
        $query = $conexion->prepare("UPDATE school_user SET password=? WHERE name=?"); // creamos la consulta sql de actualizacion
        $query->execute([$_POST["password"], $_POST["name"]]);//añadimos lo parametros y la ejecutamos 
        header("Location: index.php"); // enviamos al usuario al login
    } catch (\Throwable $th) {// si hay algun error al ejecutar la consulta devolvemos un error
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
        <?php if (!isset($checked)) { // si no esta chequeado mostramos el primero formulario?>
            <form action="" method="post">
                <label for="nameCheck">name</label>
                <input type="text" name="nameCheck" id="nameCheck" class="form-control">
                <button class="btn btn-primary">check</button>
            </form>
        <?php } else { ?>
            <form action="" method="post">
                <label for="name">name</label>
                <input type="text" name="name" id="name" value="<?php echo $checked ?>" readonly class="form-control">
                <label for="password">password</label>
                <input type="password" name="password" id="password" class="form-control">
                <button class="btn btn-primary">update</button>
            </form>
        <?php } ?>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>

</html>