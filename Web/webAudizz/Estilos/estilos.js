        //Escucha el evento submit del form
        document.getElementById('boton').addEventListener('click', function(event) {
        event.preventDefault(); // Previene la recarga de la página por defecto
        console.log;
        // Obtiene el valor del número de teléfono desde el campo de entrada
        const phoneNumber = document.getElementById('msisdn').value;
            // Utiliza fetch para enviar una solicitud POST al servidor
        const nombre = fetch('http://localhost:8080/webapi/getUser?msisdn=' + phoneNumber, {
            method: 'POST'
        //Convierte el objeto que contiene el número de teléfono a un string JSON.
        })
        .then(response => response.ok ? window.location.href="http://127.0.0.1:5500/rock.html" : showError())
        
    });

        function showError(){
            document.getElementById("error").innerHTML = "El telefono no esta en la base de datos";
            document.getElementById("error").hidden=false; 
        }