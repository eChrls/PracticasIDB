<?php
session_start(); //iniciamos la session

$conexion = new PDO("mysql:host=host.docker.internal:3306;dbname=apicrud", "root", "");// nos conectamos a la base de datos 
$conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);//añadimos las propiedades de error

if (isset($_POST["idDelete"])) {// miramos si se ha dado a la confirmacion de eliminar alumno 
    try {
        $queryDelete = $conexion->prepare("DELETE FROM student WHERE id=?"); // creamos la consulta sql de eliminar alumno 
        $queryDelete->execute([$_POST["idDelete"]]); // añadimso los parametros y la ejecutamos 
    } catch (\Throwable $th) {// si hay algun error al ejecutar la consulta devolvemos un error
        $error = "internal error to delete";
    }
}

if (
    isset($_POST["name"]) && isset($_POST["email"]) && isset($_POST["phone"]) && isset($_POST["address"]) && isset($_POST["city"])
    && isset($_POST["age"]) && isset($_POST["course"]) && isset($_POST["dateInit"])
) { // vemos que se ha enviado el formulario de insertar

    if (
        $_POST["name"] == "" || $_POST["email"] == "" || $_POST["phone"] == "" || $_POST["address"] == "" || $_POST["city"] == ""
        || $_POST["age"] == "" || $_POST["course"] == "" || $_POST["dateInit"] == ""
    ) { // comprobamos si hay algo vacio para mostrar error
        $error = "You can't leave any empty";
    } else {
        try {
            //creamos la sentencia sql de insertar
            $queryDelete = $conexion->prepare("INSERT INTO student(address,age,city,course,date_init,email,name,phone,school_user_id) values (?,?,?,?,?,?,?,?,?)");
            // añadimso los parametros y la ejecutamos 
            $queryDelete->execute([$_POST["address"], $_POST["age"], $_POST["city"], $_POST["course"], $_POST["dateInit"], $_POST["email"], $_POST["name"], $_POST["phone"], $_SESSION["id"]]);
        } catch (\Throwable $th) {// si hay algun error al ejecutar la consulta devolvemos un error
            $error = "error to insert new student";
        }

    }

}

if (
    isset($_POST["id_update_form"]) && isset($_POST["name_update_form"]) && isset($_POST["email_update_form"]) && isset($_POST["phone_update_form"]) && isset($_POST["address_update_form"]) && isset($_POST["city_update_form"])
    && isset($_POST["age_update_form"]) && isset($_POST["course_update_form"]) && isset($_POST["dateInit_update_form"])
) { // vemos que se ha enviado el formulario de actualizar

    if (
        $_POST["name_update_form"] == "" || $_POST["email_update_form"] == "" || $_POST["phone_update_form"] == "" || $_POST["address_update_form"] == "" || $_POST["city_update_form"] == ""
        || $_POST["age_update_form"] == "" || $_POST["course_update_form"] == "" || $_POST["dateInit_update_form"] == ""
    ) { // comprobamos si hay algo vacio para mostrar error
        $error = "You can't leave any empty";
    } else {
        try {
            $queryUpdate = $conexion->prepare("UPDATE student SET name=?, email=?, phone=?, address=?, city=?, age=?, course=?,date_init=? WHERE id=?"); // generamos al consulta sql de actualizar
            $queryUpdate->execute([$_POST["name_update_form"], $_POST["email_update_form"], $_POST["phone_update_form"], 
            $_POST["address_update_form"], $_POST["city_update_form"], $_POST["age_update_form"], $_POST["course_update_form"], 
            $_POST["dateInit_update_form"], $_POST["id_update_form"]]); // añadimos los parametros y la ejecutamos 
        } catch (\Throwable $th) {// si hay algun error al ejecutar la consulta devolvemos un error
            $error = "internal error to update the user";
        }

    }
}

if(isset($_POST["deleteSchool"])){// vemos si se ha enviado el formulario de eliminar escuela
     $queryDeleteSchool = $conexion->prepare("DELETE FROM school WHERE id=?"); // preparamos la consulta sql de eliminar
     $queryDeleteSchool->execute([$_SESSION["id"]]);// añadimos los parametros y la ejecutamos 
     session_destroy(); // destruimos la session
     header("Location: index.php"); // lo enviamos al login
}

if(isset($_POST["exit"])){ // vemos si se ha enviado lo de borrar session
    session_destroy();// destruimos la session
    header("Location: index.php");// lo enviamos al login
}

$query = $conexion->prepare("SELECT * FROM student WHERE school_user_id = ?"); // generamos la consulta sql de obtener los usuarios
$query->execute([$_SESSION["id"]]);// añadimos los parametros y la ejecutamos 
$resultAllSchool = $query->fetchAll(PDO::FETCH_ASSOC); // obtenemos todos los usuarios en un array asociativo 

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

<body>

    <?php if (isset($error)) // si hay algun error lo mostramos 
            echo "<p>" . $error . "</p>"; ?>

    <form action="" method="post">
        <input type="hidden" name="addStudent">
        <button class="btn btn-success"> add </button>
    </form>

    <form action="" method="post">
        <input type="hidden" name="deleteSchool">
        <button class="btn btn-danger">delete School</button>
    </form>

    <form action="" method="post">
        <input type="hidden" name="exit">
        <button class="btn btn-warning">log out</button>
    </form>

    <table class="table table-dark table-striped table-bordered">
        <thead>
            <?php
            foreach ($resultAllSchool[0] as $key => $value) {// recorremos el array de la primera posicion solamente para sacar las key
                if ($key != "school_user_id") { // quitamos la asociacion
                    echo "<td>" . $key . "</td>"; // generamos la celda de la tabla con los datos
                }
            }
            ?>
            <td>actions</td>
        </thead>
        <?php
        for ($i = 0; $i < count($resultAllSchool); $i++) { // recorremos cada estudiantes
            echo "<tr>"; // añadimos la linea de la tabla 
            foreach ($resultAllSchool[$i] as $key => $value) { // recorremos cada valor del usuario 
                if ($key != "school_user_id") { // quitamos la asociacion
                    echo "<td>" . $value . "</td>"; // ponemos en la celda el valor 
                }
            }
            ?>

            <td>
                <form action="" method="post">
                    <input type="hidden" name="idDeleteCheck" value="<?php echo $resultAllSchool[$i]["id"]; // añadimos el id de cada alumno ?> ">
                    <button class="btn btn-trasparent">
                        <img src="images/delete.png" alt="delete">
                    </button>
                </form>

                <form action="" method="post">
                    <?php
                    foreach ($resultAllSchool[$i] as $key => $value) {
                        if ($key != "school_user_id") {
                            // añadimos cada input hidden de todos los valores del usuario
                            echo "<input type='hidden' id='" . $key . "_update' name='" . $key . "_update' value='" . $value . "'>";
                        }
                    }
                    ?>
                    <button class="btn btn-trasparent">
                        <img src="images/tool.png" alt="update">
                    </button>
                </form>
            </td>
            <?php
            echo "</tr>";
        }
        ?>

    </table>
<?php
if(isset($_POST["addStudent"])){ // si le ha dado al boton de añadir mostramos este formulario 

?>
    <form action="" method="post" class="d-flex flex-row flex-nowrap justify-content-around">
        <label for="name" class="me-2">name: </label>
        <input type="text" name="name" id="name" class="insert form-control w-25 me-4">
        <label for="email" class="me-2">email: </label>
        <input type="email" name="email" id="emial" class="insert form-control w-25 me-4">
        <label for="phone" class="me-2">phone: </label>
        <input type="tel" name="phone" id="phone" class="insert form-control w-25 me-4">
        <label for="address" class="me-2">address: </label>
        <input type="text" name="address" id="address" class="insert form-control w-25 me-4">
        <label for="city" class="me-2">city: </label>
        <input type="text" name="city" id="city" class="insert form-control w-25 me-4">
        <label for="age" class="me-2">age: </label>
        <input type="number" name="age" id="age" class="insert form-control w-25 me-4">
        <label for="course" class="me-2">course: </label>
        <select name="course" id="course" class="insert form-select w-25 me-4">
            <option value="1">childish</option>
            <option value="2">elementary</option>
            <option value="3">intermediate</option>
            <option value="4">advance</option>
        </select>
        <label for="date" class="me-2">date: </label>
        <input type="date" name="dateInit" id="dateInit" class="insert form-control w-25 me-4">
        <button class="btn btn-secondary" id="buttonInsertStudent"> send</button>
    </form>

    <?php
}


    if (isset($_POST["idDeleteCheck"])) { // si le ha dado al icono de borrar mostramos este mensaje
        ?>
        <p>Are you sure you want to delete this student with the id ( <?php echo $_POST["idDeleteCheck"]; ?>)?</p>
        <form action="" method="post">
            <input type="hidden" name="idDelete" value="<?php echo $_POST["idDeleteCheck"]; ?> ">
            <button class="btn btn-danger">delete</button>
        </form>
        <form action="" method="post">
            <button class="btn btn-secondary">back</button>
        </form>
        <?php
    }


    if (isset($_POST["id_update"])) { // si le ha dado al boton de actualizar mostramos este formulario
        ?>
        <h2>Update form</h2>
        <form action="" method="post" class="d-flex flex-row flex-nowrap justify-content-around">
            <input type="hidden" name="id_update_form" value="<?php echo $_POST["id_update"] ?>">
            <label for="name_update_form" class="me-2">name: </label>
            <input type="text" name="name_update_form" id="name_update_form" class="insert form-control w-25 me-4"
                value="<?php echo $_POST["name_update"]; ?>">
            <label for="email_update_form" class="me-2">email: </label>
            <input type="email" name="email_update_form" id="emial_update_form" class="insert form-control w-25 me-4"
                value="<?php echo $_POST["email_update"]; ?>">
            <label for="phone_update_form" class="me-2">phone: </label>
            <input type="tel" name="phone_update_form" id="phone_update_form" class="insert form-control w-25 me-4"
                value="<?php echo $_POST["phone_update"]; ?>">
            <label for="address_update_form" class="me-2">address: </label>
            <input type="text" name="address_update_form" id="address_update_form" class="insert form-control w-25 me-4"
                value="<?php echo $_POST["address_update"]; ?>">
            <label for="city_update_form" class="me-2">city: </label>
            <input type="text" name="city_update_form" id="city_update_form" class="insert form-control w-25 me-4"
                value="<?php echo $_POST["city_update"]; ?>">
            <label for="age_update_form" class="me-2">age: </label>
            <input type="number" name="age_update_form" id="age_update_form" class="insert form-control w-25 me-4"
                value="<?php echo $_POST["age_update"]; ?>">
            <label for="course_update_form" class="me-2">course: </label>
            <select name="course_update_form" id="course_update_form" class="insert form-select w-25 me-4">
                <option value="1" <?php if ($_POST["course_update"] == 1)
                    echo "selected" ?>>childish</option>
                    <option value="2" <?php if ($_POST["course_update"] == 2)
                    echo "selected" ?>>elementary</option>
                    <option value="3" <?php if ($_POST["course_update"] == 3)
                    echo "selected" ?>>intermediate</option>
                    <option value="4" <?php if ($_POST["course_update"] == 4)
                    echo "selected" ?>>advance</option>
                </select>
                <label for="date_update_form" class="me-2">date: </label>
                <input type="date" name="dateInit_update_form" id="dateInit_update_form" class="insert form-control w-25 me-4"
                    value="<?php echo $_POST["date_init_update"]; ?>">
            <button class="btn btn-secondary"> update</button>
        </form>

        <?php
    }
    ?>



</body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
    crossorigin="anonymous"></script>

</html>