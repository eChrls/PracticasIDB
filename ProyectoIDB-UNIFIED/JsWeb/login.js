//Definimos la URL del servidor SpringBoot
const BASE_URL = 'http://localhost:8080/idbProject';

//Esperamos a que todo el contenido de la página cargue antes de añadir los eventos
document.addEventListener('DOMContentLoaded', () => {
    //Al hacer submit en el formulario llamamos a => LOGIN
    document.querySelector('form').addEventListener('submit', handleLogin);

    //Al hacer click en crear una nueva cuenta, llamamos a la funcion createAccount
    document.getElementById('createAccountButton').addEventListener('click', createAccount);

    //Al hacer click en cambiar contraseña llamamos a la función handlePasswordUpdate
    document.getElementById('OlvidasteContraseñaBoton').addEventListener('click', handlePasswordUpdate);

    //Verificamos si hay una sesion activa para redirigir al usuario si ya ha iniciado sesion:
    checkSession();
});
//Funcion que verifica si hay sesión activa: 
function checkSession() {
    //Obtenemos informacion del usuario guardada en localStorage
    const userInfo = localStorage.getItem('userInfo');

    //Si hay informacion de usuario significa que ya está logueado, redirigimos.
    if (userInfo) {
        window.location.href = 'table.html';
    }
}
//Funcion que maneja el inicio de sesión: 
async function handleLogin(event) {
    //evitamos que el form se envie de manera tradicional (sin recargar)
    event.preventDefault();

    //Obtenemos los valores que el usuario escrubió en los campos del formulario principal
    const user = document.getElementById('user').value;
    const password = document.getElementById('password').value;


    //-----------------------LOGIN------------------------------------------------
    //Peticion post a la URL del LOGIN
    try {

        const response = await fetch(`${BASE_URL}/login?user=${user}&password=${password}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json' //le decimos al servidor que enviamos un json
            }
        }
        );
        //Esperamos la respuesta del servidor y la convertimos a json
        const data = await response.json();

        if (response.ok) {
            //localStorage es como una bbdd en el servidor. Almacena datos del usuario.
            //Nos permite guardar datos que persisten aunque cerremos la pagina. 
            localStorage.setItem('userInfo', data);
            // Cuando el login sea exitoso
            localStorage.setItem('userId', user);
            localStorage.setItem('userPassword', password);

            window.location.href = "table.html";

        } else {
            alert('Usuario o contraseña incorrectos');

        }

    } catch (error) {

        console.error('Error: ', error);
    }

}

//-----------------------CREAR CUENTA------------------------------------------------
//Funcion para crear cuenta. 

async function createAccount(event) {
    event.preventDefault();

    const schoolData = {
        schoolName: document.getElementById('schoolName').value,
        user: document.getElementById('newUsername').value,
        password: document.getElementById('newPassword').value,
    };

    console.log(schoolData);

    //Peticion POST al servidor para crear el nuevo usuario de Escuela
    try {
        const response = await fetch(`${BASE_URL}/+sch`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
                 //Conversion del objeto a json
  
            //Conversion del objeto a json
            body: JSON.stringify(schoolData)
        }
        );

        //control de salida
        if (response.ok) {

            // window.location.href = 'login.html';

            //Cerramos y limpiamos el modal
            const modal = bootstrap.Modal.getInstance(document.getElementById('createAccount'));
        	modal.hide();
        	// Limpiamos los campos del formulario
        	document.getElementById('schoolName').value = '';
        	document.getElementById('newUsername').value = '';
        	document.getElementById('newPassword').value = '';


        } else {
            alert('Error al crear cuenta');
        }
    } catch (error) {
        alert('Error al crear cuenta');
        console.error('ERROR', error);
    }
}

   //-----------------------CAMBIAR CONTRASEÑA------------------------------------------------
   // Función para actualizar la contraseña
   async function handlePasswordUpdate(event){
    event.preventDefault();

    //Recogemos los valores introducidos
	const usuario = document.getElementById('usuario').value;
    const newPassword = document.getElementById('pass').value;

    try{
        //Solicitud PUT para cambiar contraseña
        const response = await fetch(`${BASE_URL}/upt=pass`, {
            method: 'put',
            headers: {
                'Content-Type': 'application/json',
        },
        //Conversion del objeto a json
        body: JSON.stringify({
            "user": usuario,
            "password": newPassword
            })
        });
        // Control de salida
        if (response.ok) {
            alert('Contraseña actualizada con éxito');
            //Cerramos el modal
            const modal = bootstrap.Modal.getInstance(document.getElementById('OlvidasteContraseña'));
            modal.hide();

            //Limpiamos los campos del form
            document.getElementById('usuario').value = '';
            document.getElementById('pass').value = '';
        }else{
            alert('Error al actualizar contraseña');
        }
    }catch (error){
        console.log(error);
        alert('Error al actualizar contraseña');
        console.error("Error al actualizar contraseña")
    }



}

  //-----------------------CERRAR SESION------------------------------------------------
  function logout(){
    //Eliminamos la informacion del usuario
    localStorage.removeItem('userInfo');
    //Redireccionamos a la pagina de login
    window.location.href = 'index.html';
  }
