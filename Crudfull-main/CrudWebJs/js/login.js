const buttonLogin = document.getElementById("login"); // boton del login


buttonLogin.addEventListener("click", (event)=>{
    login(event);
})

function login(event){
    event.preventDefault();// prevenimos el efecto de recargar del boton
    let user = document.getElementById("name").value; // obtenemos el valor del nombre que ha puesto en el form de login
    let password = document.getElementById("password").value; // obtenemos el valor de la contraseña que ha puesto en el form de login
    const url = "http://localhost:8080/crud/loginSchool?name="+user+"&password="+password; //construimos la url para hacer la llamada 
    if(user =="" || password==""){ // si esta alguno vacio mandamos un mensaje de error
        messageError("Enter the user or password, do not leave it blank.")
    }else{ // si esta todos los datos bien hacemos la llamada
        callApi(url);
    }
    
}

async function callApi(urlcall){

    let response = await fetch(urlcall,{
        method : 'post',// el metodo de la llamada
    }); // hacemos la llamada a la url

    if(response.ok){
        let responseBody = await response.text();// cogemos el json del body de la respuesta
        sessionStorage.setItem("token", responseBody); // guardamos en la session de js
        window.location = "http://127.0.0.1:5500/home.html"; // redirijimos a la pagina principal
    }else{ // si ha ido mal ponemso un mensaje de error
        messageError("user or password not valid")
    }
}


function messageError(message) {
    let error = document.getElementById("error");// cogemos la p del error
    error.hidden = false;// lo mostramos en la pagina
    error.innerHTML = message;// metemos el texto dentro de la p
}