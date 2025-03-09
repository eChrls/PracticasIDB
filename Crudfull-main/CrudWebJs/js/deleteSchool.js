const buttonDeleteS = document.getElementById("deleteSchool");// obtenemos el boton para eliminar la escuela
const urlDeleteS = "http://localhost:8080/crud/deleteSchool"; // ponemos la url de eleimianr escuela

buttonDeleteS.addEventListener("click", event =>{ //añadimso el evento de hacer click al boton anterior
    deleteSchool(event)
})

async function deleteSchool(event){
    event.preventDefault();// prevenimos el efecto de recargar del boton
    let response = await fetch(urlDeleteS,{
        method: "DELETE",
        headers: {
            "token" : sessionStorage.getItem("token")
        }
    }) //hacemos la llamada de eliminar la escuela

    if(response.ok){
        sessionStorage.removeItem("token");//si ha ido todo bien eliminamos la session
        window.location = "http://127.0.0.1:5500"; // redirijimos al login
    }else{
        errorMessageDeleteS("Error deleting school, please try again later");//como ha ido mal devolvemos un mensaje de error
    }
    
}

function errorMessageDeleteS(message){
    let error = document.getElementById("errorDelete");// cogemos la p del error
    error.hidden = false;// lo mostramos en la pagina
    error.innerHTML = message;// metemos el texto dentro de la p
}