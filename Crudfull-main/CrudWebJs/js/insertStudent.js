const buttonInsertStudent = document.getElementById("buttonInsertStudent"); // boton que esta par insertar estudiante
const InserStudentUrl = "http://localhost:8080/crud/insertStudents"; // url que hace que se inserte el usuario

buttonInsertStudent.addEventListener("click",event=>{
    insertNewStudent(event)
})

function insertNewStudent(event){
    event.preventDefault();// prevenimos el efecto de recargar del boton
    let insertInputs = document.getElementsByClassName("insert");
    const request = new Map(); //generamos un map para despues hacerlo JSON para la llamada
    let empty = false; //flat para comprobar si esta vacio o no
    for (let index = 0; index < insertInputs.length; index++) { // bucle para recorrer todos los inputs de dentro
        
        let element = insertInputs[index]; // obtenemos el elemento de index por orden
        if(element.value !=""){ // si no esta vacio lo metemos en el array
            request.set(element.name,element.value) //metemos la key que es el name del input y como valor el value de dicho input
        }else{
            empty = true
        }
    }

    if(empty){ // si esta vacio añadimos el error
        messageErrorInsertStudent("All fields are required");
    }else{ // si no esta vacio hacemos la llamada
        callApiInsertStudent(request);
    }

}

async function callApiInsertStudent(request) {
    const obj = Object.fromEntries(request); // lo convertimos en String (una forma extraña de convertir en String)
    const json = JSON.stringify(obj); // pasamos ese String a un formato json

    let response = await fetch(InserStudentUrl,{
        method: "POST",// el metodo de la llamada
        body: json,// el body que es el json que hemos generado ya que es todos los datos de insertar
        headers: {
            "token": sessionStorage.getItem("token"), // token que necesita la llamada que es el token que genera en el login
            "Content-Type": "application/json", // esto es el tipo de header para que sepa que lo que hay en el body es un json
        }
    })

    if(response.ok){
        window.location = "http://127.0.0.1:5500/home.html"; // redirijimos al login para recargar la tabla
    }else{ // si esta mal devolvemos un error
        messageErrorInsertStudent("Error insert student, please try again later");
    }
}

function messageErrorInsertStudent(message){
    let error = document.getElementById("errorInsertS");// cogemos la p del error
    error.hidden = false;// lo mostramos en la pagina
    error.innerHTML = message;// metemos el texto dentro de la p
}