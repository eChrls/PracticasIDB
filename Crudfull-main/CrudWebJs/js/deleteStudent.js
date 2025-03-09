const inputHidden = document.getElementById("idStudentDetelete"); // obtenemos el input que necesitamos meterle el id del estudiante
const buttonDeleteStudent = document.getElementById("deleteStudentB"); //boton para elimianr el estudiante
const urlDeleteStudent = "http://localhost:8080/crud/deleteStudent/"; // url para elimianr el usuario

buttonDeleteStudent.addEventListener("click", (event) =>{// cuando haga click en el boton de borrar haga la funcion
deleteStudent(event)
})

 async function deleteStudent(event){
    event.preventDefault();// prevenimos el efecto de recargar del boton
    let id = document.getElementById("idStudentDetelete").value; // obtenemos el id del estudiante
    let url = urlDeleteStudent + id; // alñadimos el id en la url
    let response = await fetch(url,{
        method: "DELETE",
        headers: {
            "token" : sessionStorage.getItem("token")
        }
    }) // hacemos la llamada de eliminar estudiante

    if(response.ok){
        window.location = "http://127.0.0.1:5500/home.html"; // redirijimos al login para recargar la tabla
    }else{
        errorMessageDeleteS("Error deleting student, please try again later"); // si ha ido mal mostramos mensaje de error
    }   

}

function errorMessageDeleteS(message){
    let error = document.getElementById("errorDeleteS");// cogemos la p del error
    error.hidden = false;// lo mostramos en la pagina
    error.innerHTML = message;// metemos el texto dentro de la p
}
/**
 * esto sirve para que en la tabla tenemos la papelera y en la class de esa imagen el id del usuario
 * pero al abrir el popUp lo metemos como input hidden en el from para que asi pueda coger el id al hacer la llamada
 * 
 * @param {*} elemento 
 */
function putInputHiddenValue(elemento){
    let img = elemento.children[0]; // cogemos el elemento hijo
    inputHidden.value = img.className; // le metemos el valor de la clase al input hidden
}