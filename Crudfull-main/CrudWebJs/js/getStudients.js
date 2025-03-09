const getAllUrl = "http://localhost:8080/crud/getStudent"; // url que obtienes todos los estudainte de una escuela

const tbody = document.getElementsByTagName("tbody"); // cogemos el cuerpo de la tabla 

async function crateTableData(){

    let response = await fetch(getAllUrl,{
        headers: {
            "token": sessionStorage.getItem("token")
        }
    }); // hacemos la llamada a la url para obtener todos los usuarios
    let jsonArray = response.json(); // obtenemos el json de la llamada
    
    jsonArray.then(json =>{
        for(let i=0; i<json.length;i++){ // al ser una lista de estudiants hay que recorrerla
            let tr = document.createElement("tr"); // creamos un elemento tr que es la linea

            let jsonObtain = createTablaDataJson(json,i,tr)


            let tdIcon = document.createElement("td");// creamos un td para meter los iconos de accion
            let tdIconWithDelete = createDeleteIcon(tdIcon,jsonObtain);
            let tdIconWithUpdate = createUpdateIcon(tdIconWithDelete,jsonObtain);
            
            
            tr.appendChild(tdIconWithUpdate);
            tbody[0].appendChild(tr)
            

        }
    }) // una vez obtenido creamos la tabla
}
/**
 * creamos un boton de eliminar con todo para que se habara el modal y funcione el boton de bororar alumno
 * 
 * @param {element} tdIcon 
 * @param {json} jsonObtain 
 * @returns 
 */
function createDeleteIcon(tdIcon,jsonObtain){
    let button = document.createElement("button");// creamos un boton para el icono
    button.setAttribute("class", "btn btn-link buttonDeleteStudent")//le metemos la clases de boostra y la clase que usaremos mas adelante
    button.setAttribute("type","button");// poner el tipo al botton
    button.setAttribute("data-bs-toggle","modal"); // añadimos que es un modal de boostrap
    button.setAttribute("data-bs-target","#deleteStudent"); // aqui se pone el id del modal que va a abrir cuando haga click
    button.setAttribute("onClick","putInputHiddenValue(this)"); //funcion que sirve para añadir el id
    let image = document.createElement("img"); //creamos la etiqueta de img
    image.setAttribute("src","images/delete.png"); // añadimos la imagen de borrar
    image.setAttribute("class",jsonObtain["id"]); //añadimos la clase que es el id del usuario para la funcion
    button.appendChild(image);// metemos la imagen dentro del boton
    tdIcon.appendChild(button) //metemos el boton dentro del td
    return tdIcon; // devolvemos la etiqueta entera
}

/**
 * metemos el icono de actualizar con toda la funcionalidad para abrir el modal y aparecer los datos en el input
 * 
 * @param {element} tdIconWithDelete 
 * @param {json} jsonObtain 
 * @returns 
 */
function createUpdateIcon(tdIconWithDelete,jsonObtain){
    let button = document.createElement("button"); // creamos el boton para el icono
    button.setAttribute("class", "btn btn-link buttonUpdateStudent") // le metemos la clases de boostra y la clase que usaremos mas adelante
    button.setAttribute("type","button");// poner el tipo al botton
    button.setAttribute("data-bs-toggle","modal");// añadimos que es un modal de boostrap
    button.setAttribute("data-bs-target","#updateStudent");// aqui se pone el id del modal que va a abrir cuando haga click
    button.setAttribute("onClick","putValueInForm(this)");//funcion que sirve para añadir los valores del usuario que hay que modificar
    let image = document.createElement("img"); //creamos la etiqueta de img
    image.setAttribute("src","images/tool.png"); // añadimos la imagen de actualizar
    image.setAttribute("id",jsonObtain["id"]); // añadimos el id para coger los datos
    button.appendChild(image); // añadimos la imagen al boton
    tdIconWithDelete.appendChild(button) //ld metemos dentro del td que ya tiene el boton 
    return tdIconWithDelete; // devolvemos la etiqueta entera
}

/**
 * creamos la linea de los datos del estudiante
 * 
 * @param {json} json 
 * @param {int} i 
 * @param {element} tr 
 * @returns 
 */
function createTablaDataJson(json,i,tr){
    let jsonObtain = json[i]; // obtenemos los datos del estudiante
    for(property in jsonObtain){ // recorremos el json por cada clave de dentro
        let valor = jsonObtain[property]; // obtenemos el dato de la clave del array
        if( property != "schoolUser"){ //el dato que descartamos es el de la escula del usuario
            let td = document.createElement("td"); //creamos un tr
            td.innerHTML = valor; // le metemos el valor del dato del json en el tr
            td.setAttribute("class", jsonObtain["id"]) //le añadimos la id del usuario por classe que sera util mas adelante
            tr.appendChild(td); // metemos el td dentro del tr
            tbody[0].appendChild(tr) // y ese tr lo metemos dentro del body de la tabla
        }
    }
    return jsonObtain // y por ulto devolvemos el json del estudiante
}

crateTableData()
