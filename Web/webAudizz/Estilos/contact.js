       const button = document.getElementsByTagName('button');
       const url = "http://localhost:8080/webapi/addComment"

       button[0].addEventListener("click", (event) =>{
        addComment(event);
       });

       function addComment(event){
        event.preventDefault();
        let empty = false

        const request = new Map();
        request.set("idComentario", sessionStorage.getItem("idComentario"));

        let message = document.getElementById("message").value;

        if(message=="") empty = true
        request.set("comment",message);

        let textarea = document.getElementsByTagName("textarea");
        for(let i = 0; i<textarea.length; i++){
            if(textarea[i] == "") empty=true
            request.set(textarea[i].id, inputs[i].value);
        }

        if(!empty){
            callApi(request);
        }else{
            callError("All fields are requiered");
        }

       }

       async function callApi(request){
        const obj = Object.fromEntries(request);
        const json = JSON.stringify(obj);

        let response = await fetch (url, {
            method : "post", 
            body: json, 
            headers : {
                "Content-Type" : "application / json",
            }
        })

        if(response.ok){
            callSuccess();
        }else{
            callError("Internal error");
        }
       }

       function callError(message){
        let error = document.getElementById("error");
       }
        
        
        
        // //Escucha el evento submit del form
        // document.getElementById('boton').addEventListener('click', function(event) {
        //     event.preventDefault(); // Previene la recarga de la página por defecto
        //     console.log;
        //     // Obtiene el valor del número de teléfono desde el campo de entrada
        //     const phoneNumber = document.getElementById('msisdn').value;
        //     //this.getElementsByTagName (es array)
        //         // Utiliza fetch para enviar una solicitud POST al servidor
        //     const nombre = fetch('http://localhost:8080/webapi/getUser?msisdn=' + phoneNumber, {
        //         method: 'POST'
        //     //Convierte el objeto que contiene el número de teléfono a un string JSON.
        //     })
        //     .then(response => response.ok ? window.location.href="http://127.0.0.1:5500/rock.html" : showError())
            
        // });
    
        //     function showError(){
        //         document.getElementById("error").innerHTML = "El telefono no esta en la base de datos";
        //         document.getElementById("error").hidden=false; 
        //     }