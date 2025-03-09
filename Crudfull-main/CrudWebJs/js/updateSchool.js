const checkSchool = document.getElementById("checkSchool"); // boton para chequear que ese usuario existe
const UpdateSchool = document.getElementById("UpdateSchool"); // boton para que actualice la contraseña
const urlCheck = "http://localhost:8080/crud/findSchool?name="; //url para chequear que el usuario existe en la base de datos
const urlUpdate = "http://localhost:8080/crud/updateSchool/";

checkSchool.addEventListener("click", (event)=>{
    checkUser(event);
})

UpdateSchool.addEventListener("click", (event)=>{
    updateUser(event);
})

function updateUser(event){
    event.preventDefault();// prevenimos el efecto de recargar del boton
    let user = document.getElementById("nameChek").value; // obtenemso el valor del nombre dle usuario
    let password = document.getElementById("passwordUpdate").value; // obtenemos la contraseña actualizada
    const request = new Map(); //generamos un map para despues hacerlo JSON para la llamada
    if(user =="" || password ==""){ // si esta vacio ponemos un mensaje de error
        messageErrorUpdate("Enter the user or password, do not leave it blank.");
    }else{
        request.set("name",user); // añadimos el nombre al array
        request.set("password",password); // añadimos la contraseña al array
        const obj = Object.fromEntries(request); // lo convertimos en String (una forma extraña de convertir en String)
        const json = JSON.stringify(obj); // pasamos ese String a un formato json
        let url = urlUpdate+localStorage.getItem("idUser"); // ponemos el id del usuario en la url para hacer la llamada correctamente 
        callApiUpdate(json,url); // y hacemos la llamada a la api
    }
}

async function callApiUpdate(json,url) {
    let response = await fetch(url,{
        method : 'put',// el metodo de la llamada
        body: json, // el body que es el json que hemos generado ya que es todos los datos del comentario
        headers: {
            "Content-Type": "application/json", // esto es el tipo de header para que sepa que lo que hay en el body es un json
          }
    }); // hacemos la llamada a la url

    if(response.ok){
        localStorage.removeItem("idUser"); // removemos el Item que no vamos a usar mas 
        window.location = "http://127.0.0.1:5500"; // redirijimos a la pagina inicial de nuevo para que se logee otra vez
    }else{
        messageErrorUpdate("internal error");
    }
}

function checkUser(event){
    event.preventDefault();// prevenimos el efecto de recargar del boton
    let user = document.getElementById("nameChek").value; // obtenemos el valor del nombre del usuario
    let url = urlCheck+user; // añadimos el nombre a la url 
    callApiCheck(url); // y hacemos la llamada a la url

}

async function callApiCheck(url){
    let response = await fetch(url,{
        method : 'get',// el metodo de la llamada
    }); // hacemos la llamada a la url

    if(response.ok){
        let elementUser = document.getElementById("nameChek"); // obtenemos el elemento dodne se pone el nombre
        let password = document.getElementById("passwordUpdate"); // obtenemos el elemento input de poner la contraseña
        let passwordLabel = document.getElementById("labelPassword"); // obtenemos el elemento label de poner la contraseña
        passwordLabel.hidden= false; // mostramos el label de la contraseña
        password.hidden = false; // mostramos el input de la contraseña
        checkSchool.hidden = true;  // ocultamos el boton de comprobar
        UpdateSchool.hidden = false // mostramos el boton de actualizar 
        elementUser.classList.add("form-control-plaintext"); // añadimos que el input solo se muestre para que el usuario no lo cambie
        elementUser.readOnly = true; //  añadimos que el input solo se muestre para que el usuario no lo cambie

        let responseBody = await response.text();// cogemos el json del body de la respuesta
        localStorage.setItem("idUser", responseBody); // guardamos en la localStorage de js
    }else{ // si la llamada ha ido mal ponemos un mnesjae de error
        messageErrorUpdate("user not found");
    }
}

function messageErrorUpdate(message) {
    let error = document.getElementById("errorCheckUpdate");// cogemos la p del error
    error.hidden = false;// lo mostramos en la pagina
    error.innerHTML = message;// metemos el texto dentro de la p
}