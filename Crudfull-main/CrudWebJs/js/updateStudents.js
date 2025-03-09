const buttonUpdateStudent = document.getElementById("buttonUpdateStudent"); // boton para actualizar el estudiante
const urlUpdateStudent = "http://localhost:8080/crud/updateStudent/" // url pata actualizar el usuario

buttonUpdateStudent.addEventListener("click",event =>{
    updateStudent(event)
})

function updateStudent(event){
    event.preventDefault();// prevenimos el efecto de recargar del boton
    let inputs = document.getElementsByClassName("update");
    let empty = false; //añadimos una variable flat
    const requestUpdate = new Map(); //generamos un map para despues hacerlo JSON para la llamada
    for (let index = 1; index < inputs.length; index++) { // recorremos los inputs para coger los valores 
        let element = inputs[index]; // obtenemos el elemento por orden
        if(element.value ==""){ // si esta vacio cambiamos el flat
            empty = true
        }else{ // si no esta vacio lo añadimos al array
            requestUpdate.set(element.name,element.value)
        }
    }

    if(empty){ // hay algo vacio añadimos un mensaje de error
        messageErrorUpdateStudent("All fields are required")
    }else{ //hacemos la llamada
        callUpdateStudent(requestUpdate,inputs[0].value);
    }
}

async function callUpdateStudent(request,id){
    const obj = Object.fromEntries(request); // lo convertimos en String (una forma extraña de convertir en String)
    const json = JSON.stringify(obj); // pasamos ese String a un formato json

    let url = urlUpdateStudent+id; // añadimos la id en la url 
    let response = await fetch(url,{
        method: "PUT", // metodo de la llamada
        body: json, // json de la llamada dodne esta los datos de ctualizacion del usuario
        headers: {
            "token": sessionStorage.getItem("token"), // token de confirmacion
            "Content-Type": "application/json", // esto es el tipo de header para que sepa que lo que hay en el body es un json
        }
    });

    if(response.ok){
        window.location = "http://127.0.0.1:5500/home.html"; // redirijimos al login para recargar la tabla
    }else{
        messageErrorUpdateStudent("Error update student, please try again later");
    }
}

function messageErrorUpdateStudent(message){
    let error = document.getElementById("errorUpdateS");// cogemos la p del error
    error.hidden = false;// lo mostramos en la pagina
    error.innerHTML = message;// metemos el texto dentro de la p
}

function putValueInForm(elemento){
    let img = elemento.children[0]; // obtenemos el hijo del elemento 
    let values = document.getElementsByClassName(img.id); //obtenemos los datos del usuario en la tabla que tiene como clase el id del usuario
    let inputs = document.getElementsByClassName("update"); // obtenemos los inputs que esta en la pagina para actalizar

    for (let index = 0; index < values.length-1; index++) {
        inputs[index].value = values[index].innerHTML;  //recorremos y metemos en los value de los input el valor de los usuarios      
    }
}