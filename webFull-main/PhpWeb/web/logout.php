<?php
session_start();// iniciamos la sesion

session_destroy(); // destruimos la sesion
header("Location: index.php"); // redirigimos al usuario a la pagina de login
?>