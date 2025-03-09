<?php
// Iniciamos la sesión para manejar el token de autenticación
session_start();

/************************************************
 * CONFIGURACIÓN INICIAL Y VALIDACIONES
 ************************************************/
// URL base para todas las peticiones a la API Spring Boot
define('BASE_URL', 'http://localhost:8080/idbProject');

// Verificamos si existe una sesión activa con token
if (!isset($_SESSION["token"])) {
    header("Location: http://localhost/PhPweb/login.php");
    exit();
}

// Extraemos la información del token
$token = $_SESSION["token"];
$tokenData = json_decode($token, true);
$idSchool = isset($tokenData["idSchool"]) ? $tokenData["idSchool"] : null;

// Validamos que exista el ID de la escuela
if(!$idSchool){
    echo "No se pudo obtener la escuela";
    exit();
}

/************************************************
 * FUNCIONES AUXILIARES PARA LLAMADAS API
 ************************************************/
/**
 * Función para realizar llamadas a la API
 * @param string $endpoint - Endpoint de la API
 * @param string $method - Método HTTP (GET, POST, PUT, DELETE)
 * @param array $data - Datos a enviar (opcional)
 * @return array - Respuesta de la API y código HTTP
 */
function callApi($endpoint, $method, $data = null) {
    $curl = curl_init();
    $url = BASE_URL . $endpoint;
    
    $options = [
        CURLOPT_URL => $url,
        CURLOPT_RETURNTRANSFER => true,
        CURLOPT_CUSTOMREQUEST => $method,
        CURLOPT_HTTPHEADER => [
            'token: ' . $_SESSION["token"],
            'Content-Type: application/json'
        ]
    ];
    
    // Si hay datos para enviar, los añadimos a la petición
    if ($data && ($method === "POST" || $method === "PUT")) {
        $options[CURLOPT_POSTFIELDS] = json_encode($data);
    }
    
    curl_setopt_array($curl, $options);
    
    $response = curl_exec($curl);
    $httpCode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
    curl_close($curl);
    
    return [
        'response' => $response,
        'httpCode' => $httpCode
    ];
}

/************************************************
 * FUNCIONES CRUD PARA ESTUDIANTES
 ************************************************/
/**
 * Obtiene todos los estudiantes
 */
function getStudents() {
    $result = callApi('/show=all', 'GET');
    if ($result['httpCode'] === 200) {
        return json_decode($result['response'], true);
    }
    return false;
}

/**
 * Añade un nuevo estudiante
 */
function addStudent($studentData) {
    return callApi('/+alu', 'POST', $studentData);
}

/**
 * Actualiza un estudiante existente
 */
function updateStudent($idAlumn, $studentData) {
    return callApi("/upt=alu/{$idAlumn}", 'PUT', $studentData);
}

/**
 * Elimina un estudiante
 */
function deleteStudent($idAlumn) {
    return callApi("/-alu/{$idAlumn}", 'DELETE');
}

/************************************************
 * FUNCIONES CRUD PARA ESCUELA
 ************************************************/
/**
 * Obtiene el nombre de la escuela
 */
function getSchoolName() {
    $result = callApi('/show/school', 'GET');
    if ($result['httpCode'] === 200) {
        return $result['response'];
    }
    return false;
}

/**
 * Actualiza los datos de la escuela
 */
function updateSchool($schoolData) {
    global $idSchool;
    return callApi("/upt=sch/{$idSchool}", 'PUT', $schoolData);
}

/**
 * Elimina la escuela
 */
function deleteSchool() {
    global $idSchool;
    $result = callApi("/-sch/{$idSchool}", 'DELETE');
    if ($result['httpCode'] === 200) {
        session_destroy();
        header("Location: http://localhost/PhPweb/login.php");
        exit();
    }
    return false;
}

/************************************************
 * MANEJADOR DE PETICIONES POST
 ************************************************/
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $response = ['success' => false, 'message' => ''];
    
    // Switch para manejar diferentes tipos de acciones
    switch($_POST['action']) {
        case 'add_student':
            // Preparamos los datos del estudiante
            $studentData = [
                'name' => $_POST['name'],
                'surname' => $_POST['surname'],
                'age' => $_POST['age'],
                'email' => $_POST['email'],
                'city' => $_POST['city'],
                'grade' => $_POST['grade'],
                'gradeDate' => $_POST['gradeDate'],
                'idSchool' => $idSchool
            ];
            
            $result = addStudent($studentData);
            $response['success'] = ($result['httpCode'] === 200);
            $response['message'] = $response['success'] ? 
                'Estudiante añadido correctamente' : 'Error al añadir estudiante';
            break;
            
        case 'update_student':
            $studentData = [
                'name' => $_POST['name'],
                'surname' => $_POST['surname'],
                'age' => $_POST['age'],
                'email' => $_POST['email'],
                'city' => $_POST['city'],
                'grade' => $_POST['grade'],
                'gradeDate' => $_POST['gradeDate']
            ];
            
            $result = updateStudent($_POST['idAlumn'], $studentData);
            $response['success'] = ($result['httpCode'] === 200);
            $response['message'] = $response['success'] ? 
                'Estudiante actualizado correctamente' : 'Error al actualizar estudiante';
            break;
            
        case 'delete_student':
            $result = deleteStudent($_POST['idAlumn']);
            $response['success'] = ($result['httpCode'] === 200);
            $response['message'] = $response['success'] ? 
                'Estudiante eliminado correctamente' : 'Error al eliminar estudiante';
            break;
            
        case 'update_school':
            $schoolData = [
                'user' => $_POST['user'],
                'password' => $_POST['password'],
                'schoolName' => $_POST['schoolName']
            ];
            
            $result = updateSchool($schoolData);
            $response['success'] = ($result['httpCode'] === 200);
            $response['message'] = $response['success'] ? 
                'Escuela actualizada correctamente' : 'Error al actualizar escuela';
            break;
            
        case 'delete_school':
            $result = deleteSchool();
            $response['success'] = ($result['httpCode'] === 200);
            $response['message'] = $response['success'] ? 
                'Escuela eliminada correctamente' : 'Error al eliminar escuela';
            break;
    }
    
    // Enviamos la respuesta como JSON
    header('Content-Type: application/json');
    echo json_encode($response);
    exit();
}

/************************************************
 * CARGA INICIAL DE DATOS
 ************************************************/
// Cargamos los datos iniciales necesarios para la página
$schoolName = getSchoolName();
$students = getStudents();
?>