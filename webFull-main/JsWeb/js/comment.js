const button = document.getElementsByTagName("button"); // cogo el elemento del boton
const url = "http://localhost:8080/apiWeb/addComment" // pongo la url de login del back 

//tras coger el primer boton del html (el unico que hay) escuchamos el evento click y ejecutamso la funcion
button[0].addEventListener("click", (event) => {
    addComment(event);
});

function addComment(event){
    event.preventDefault();// prevenimos el efecto de recargar del boton
    let empty = false // parametro que busca si ha vacios 

    const request = new Map(); //generamos un map para despues hacerlo JSON para la llamada
    request.set("idUser",sessionStorage.getItem("iduser")); // Añadimos el id del usuario que guardamos anteriormente en la session

    let message = document.getElementById("message").value; // cogemos el valor del mensaje

    if(message =="") empty= true // si esta vacio lo pone a true
    request.set("comment",message); // añadimos el valor del mensaje 

    let inputs = document.getElementsByTagName("input");//cogemos todos los inputs de la pagina 
    for(let i=0;i<inputs.length;i++){// recoremos los inputs ya que hay mas de un input
        if(inputs[i] =="") empty= true // si esta vacio lo pone a true
        request.set(inputs[i].id,inputs[i].value);// por cada input añadimos el valor en el map
    }
    
    if(!empty){ // si no hay ninguno vacio es decir hay datos se envia a la llamada
         callApi(request);
    }else{ //en caso de que haya alguno vacio se envia un mensaje de error
        callerror("All fields are required");
    }
   

}

async function callApi(request){
    
    const obj = Object.fromEntries(request); // lo convertimos en String (una forma extraña de convertir en String)
    const json = JSON.stringify(obj); // pasamos ese String a un formato json
 
    let response = await fetch(url,{
        method : 'post',// el metodo de la llamada
        body: json, // el body que es el json que hemos generado ya que es todos los datos del comentario
        headers: {
            "Content-Type": "application/json", // esto es el tipo de header para que sepa que lo que hay en el body es un json
          }

    }); // hacemos la llamada a la url

    if(response.ok){// comprobamos que la llamada ha salido bien
        callsuccess();
    }else{
        callerror("Internal Error");
    }
}

function callerror(message){
    let error = document.getElementById("error");// cogemos la p del error
    error.hidden = false;// lo mostramos en la pagina
    error.innerHTML = message;// metemos el texto dentro de la p
}

function callsuccess(){
    let success = document.getElementById("success"); // cogemos la p del success
    success.hidden= false;// lo mostramos en la pagina
    success.innerHTML = "The comment has been added successfully";// metemos el texto dentro de la p
}