const button = document.getElementsByTagName("button"); // cogo el elemento del boton
const url = "http://localhost:8080/apiWeb/getUser?msisdn=" // pongo la url de login del back 

//tras coger el primer boton del html (el unico que hay) escuchamos el evento click y ejecutamso la funcion
button[0].addEventListener("click", (event) => {
    login(event);
});


function login(event) {
    let msisdn = document.getElementById("msisdn").value;// cogo el valor del input del login
    event.preventDefault(); // prevenimos el efecto de recargar del boton
    if (msisdn === "") { // comprobamos que el valor este vacio
        messageError("Enter the phone number, do not leave it blank.")
    } else { // si tenemos datos en el msisdn
        callApi(msisdn);
    }
}

async function callApi(msisdn) {
    let response = await fetch(url + msisdn); //hacemos la llamada a la url 
    if (response.ok) {// miramos si la respuesta es correcta, es decir que la respuesta es 200
        let responseBody = await response.json();// cogemos el json del body de la respuesta
        sessionStorage.setItem("iduser", responseBody.id); // guardamos en la session de js
        window.location = "http://127.0.0.1:5500/home.html"; // redirijimos a la pagina principal
    } else {
        messageError("This number is not subscribed");
    }
}

function messageError(message) {
    let error = document.getElementById("error");// cogemos la p del error
    error.hidden = false;// lo mostramos en la pagina
    error.innerHTML = message;// metemos el texto dentro de la p
}