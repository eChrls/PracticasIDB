const buttonCreate = document.getElementById("buttonCreate"); // boton para crear la escuela
const url = "http://localhost:8080/crud/insertSchool"; // url para insertar la escuela

buttonCreate.addEventListener("click", (event)=>{
    insert(event);
})

function insert(event){
    event.preventDefault();// prevenimos el efecto de recargar del boton
    const request = new Map(); //generamos un map para despues hacerlo JSON para la llamada
    let user = document.getElementById("nameInsert").value; //cogemos el usuario que ha puesto en el form
    let password = document.getElementById("passwordInsert").value; // cogemso la contraseña que ha puesto en el formulario
    if(user =="" || password ==""){ // comprobamos si esta vacio para devolver un error
        messageErrorInsert("Enter the user or password, do not leave it blank.");
    }else{ 
        request.set("name",user); // lo añadimos al array
        request.set("password",password); // lo añadimos al array
        const obj = Object.fromEntries(request); // lo convertimos en String (una forma extraña de convertir en String)
        const json = JSON.stringify(obj); // pasamos ese String a un formato json
        callApiInsert(json);
    }
}

async function callApiInsert(json){
    let response = await fetch(url,{
        method : 'post',// el metodo de la llamada
        body: json, // el body que es el json que hemos generado ya que es todos los datos de insertar
        headers: {
            "Content-Type": "application/json", // esto es el tipo de header para que sepa que lo que hay en el body es un json
          }
    }); // hacemos la llamada a la url

    if(response.ok){
        let responseBody = await response.text();// cogemos el json del body de la respuesta
        sessionStorage.setItem("token", responseBody); // guardamos en la session de js
        window.location = "http://127.0.0.1:5500/home.html"; // redirijimos a la pagina principal
    }else{ // si va mal devolvemois un mensaje de error
        messageError("user or password not valid")
    }
}

function messageErrorInsert(message) {
    let error = document.getElementById("error");// cogemos la p del error
    error.hidden = false;// lo mostramos en la pagina
    error.innerHTML = message;// metemos el texto dentro de la p
}