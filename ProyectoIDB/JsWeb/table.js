// URL base para todas las peticiones al servidor
const BASE_URL = 'http://localhost:8080/idbProject';

// Esperar a que el DOM esté completamente cargado antes de ejecutar el código
document.addEventListener('DOMContentLoaded', function () {
    // Recuperar datos almacenados en el localStorage del navegador
    const userId = localStorage.getItem('userId');
    const userPassword = localStorage.getItem('userPassword');
    const savedSchoolName = localStorage.getItem('schoolName');

    // Obtener referencias a elementos del DOM que usaremos frecuentemente
    const studentsTable = document.getElementById('students-table');
    const studentsBody = document.getElementById('students-body');
    const loadingMessage = document.getElementById('loading');
    const errorMessage = document.getElementById('error-message');
    const schoolNameElement = document.getElementById('schoolName');

    // FUNCION PARA MOSTRAR EL NOMBRE DE LA ESCUELA
    function loadSchoolName(userId, userPassword) {
        console.log('Iniciando carga del nombre de la escuela...');

        fetch(`${BASE_URL}/show/school?user=${userId}&password=${userPassword}`)
            .then(response => {
                console.log('Status:', response.status);
                if (!response.ok) {
                    throw new Error('Error al cargar el nombre de la escuela');
                }
                return response.text();
            })
            .then(schoolName => {
                console.log('Nombre recibido:', schoolName);
                schoolNameElement.textContent = schoolName;
                localStorage.setItem('schoolName', schoolName);
            })
            .catch(error => {
                console.error('Error:', error);
                errorMessage.textContent = error.message;
                errorMessage.style.display = 'block';
            });
    }

    // Mostrar el nombre de la escuela si existe en localStorage
    if (schoolNameElement && savedSchoolName) {
        schoolNameElement.textContent = savedSchoolName;
    }

    // FUNCION PARA MOSTRAR LA LISTA DE ESTUDIANTES POR USUARIO
    function loadStudents() {
        // Verificar que existan las credenciales necesarias
        if (!userId || !userPassword) {
            errorMessage.textContent = 'No se han encontrado credenciales de usuario';
            errorMessage.style.display = 'block';
            loadingMessage.style.display = 'none';
            return;
        }

        fetch(`${BASE_URL}/show=all?user=${userId}&password=${userPassword}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al cargar los estudiantes');
                }
                return response.json();
            })
            .then(students => {
                loadingMessage.style.display = 'none';
                studentsTable.style.display = 'table';
                studentsBody.innerHTML = '';
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//                      FUNCIONES CRUD ALUMNO
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                // CREAR
                //  filas de la tabla para cada estudiante
                students.forEach((student) => {
                    const row = `
                    <tr>
                        <td>${student.surname}</td>
                        <td>${student.name}</td>
                        <td>${student.age}</td>
                        <td>${student.email}</td>
                        <td>${student.city}</td>
                        <td>${student.grade}</td>
                        <td>${student.gradeDate}</td>
                        <td>${student.timestamp}</td>
                        <td class="actions">
                            <button 
                                class="btn edit-btn" 
                                data-bs-toggle="modal" 
                                data-bs-target="#editModal"
                                data-id="${student.idAlumn}"
                                data-name="${student.name}"
                                data-surname="${student.surname}"
                                data-age="${student.age}"
                                data-email="${student.email}"
                                data-city="${student.city}"
                                data-grade="${student.grade}"
                                data-gradeDate="${student.gradeDate}"
                            >✏️</button>
                            
                            <button 
                                class="btn add-btn"
                                data-bs-toggle="modal" 
                                data-bs-target="#addModal"
                                data-id="${student.idAlumn}"
                                data-name="${student.name}"
                                data-surname="${student.surname}"
                                data-age="${student.age}"
                                data-email="${student.email}"
                                data-city="${student.city}"
                                data-grade="${student.grade}"
                                data-gradeDate="${student.gradeDate}"    
                            >+</button>

                            <button 
                                class="btn delete-btn" 
                                data-bs-toggle="modal" 
                                data-bs-target="#deleteModal" 
                                data-id="${student.idAlumn}"
                            >🗑️</button>
                        </td>
                    </tr>
                    `;
                    studentsBody.insertAdjacentHTML('beforeend', row);
                });

                // ELIMINAR
                // Evento para eliminar un estudiante
                document.getElementById("btn-delete").addEventListener("click", function() {
                    const idAlumn = document.getElementById("deleteStudentId").value;
                    fetch(`${BASE_URL}/-alu/${idAlumn}`, {
                        method: "DELETE"
                    })
                    .then(response => {
                        if(!response.ok) {
                            throw new Error("Error al eliminar los datos");
                        }
                        window.location.href = "http://127.0.0.1:5500/JsWeb/table.html";
                    })
                    .catch(error => {
                        console.error("Error", error);
                        alert("Error al eliminar al estudiante");
                    });
                });

                //EDITAR
                //  Configurar los eventos para editar estudiantes
                document.querySelectorAll(".edit-btn").forEach(button => {
                    button.addEventListener("click", function () {
                        // Llenar el modal con los datos del estudiante
                        document.getElementById("editStudentId").value = this.getAttribute("data-id");
                        document.getElementById("editNombre").value = this.getAttribute("data-name");
                        document.getElementById("editApellidos").value = this.getAttribute("data-surname");
                        document.getElementById("editEdad").value = this.getAttribute("data-age");
                        document.getElementById("editEmail").value = this.getAttribute("data-email");
                        document.getElementById("editCiudad").value = this.getAttribute("data-city");
                        document.getElementById("editGrado").value = this.getAttribute("data-grade");
                        document.getElementById("editFechaInicio").value = this.getAttribute("data-gradeDate");
                    });
                });

                // Evento para guardar los cambios de edición
                document.getElementById("btn-edit").addEventListener("click", function () {
                    const idAlumn = document.getElementById("editStudentId").value;
                    const alumnsDto = {
                        name: document.getElementById("editNombre").value,
                        surname: document.getElementById("editApellidos").value,
                        age: document.getElementById("editEdad").value,
                        email: document.getElementById("editEmail").value,
                        city: document.getElementById("editCiudad").value,
                        grade: document.getElementById("editGrado").value,
                        gradeDate: document.getElementById("editFechaInicio").value
                    };

                    fetch(`${BASE_URL}/upt=alu/${idAlumn}`, {
                        method: "PUT",
                        headers: {
                            "Content-Type": "application/json"
                        },
                        body: JSON.stringify(alumnsDto)
                    })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error("Error al actualizar los datos");
                        }
                        window.location.href = "http://127.0.0.1:5500/JsWeb/table.html";
                    })
                    .catch(error => {
                        console.error("Error:", error);
                        alert("Error al actualizar el estudiante");
                    });
                });

                // CREAR
                // Evento para añadir un nuevo estudiante
                document.getElementById("btn-add").addEventListener("click", function () {
                    const alumnsDto = {
                        name: document.getElementById("addNombre").value,
                        surname: document.getElementById("addApellidos").value,
                        age: document.getElementById("addEdad").value,
                        email: document.getElementById("addEmail").value,
                        city: document.getElementById("addCiudad").value,
                        grade: document.getElementById("addGrado").value,
                        gradeDate: document.getElementById("addFechaInicio").value,
                        idSchool: localStorage.getItem('userInfo')
                    };
                  
                    fetch(`${BASE_URL}/+alu`, {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json"
                        },
                        body: JSON.stringify(alumnsDto)
                    })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error("Error al crear los datos");
                        }
                        window.location.href = "http://127.0.0.1:5500/JsWeb/table.html";
                    })
                    .catch(error => {
                        console.error("Error:", error);
                        alert("Error al crear el estudiante");
                    });
                });

                // Configuración de eventos para botones de navegación
                document.getElementById('logout_btn').addEventListener('click', function () {
                    localStorage.clear();
                    window.location.href = '/JsWeb/login.html';
                });

                document.getElementById('modify_school_btn').addEventListener('click', function () {
                    alert('Funcionalidad de modificar escuela pendiente de implementación');
                });

                document.getElementById('delete_school_btn').addEventListener('click', function () {
                    alert('Funcionalidad de eliminar escuela pendiente de implementación');
                });
            })
            .catch(error => {
                console.error('Error:', error);
                errorMessage.textContent = error.message;
                errorMessage.style.display = 'block';
                loadingMessage.style.display = 'none';
            });
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//                      FUNCIONES CRUD ESCUELA
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// Función para modificar la escuela
document.getElementById('modify_school_btn').addEventListener('click', function () {
    // Crear modal para modificar escuela
    const modalHTML = `
        <div class="modal fade" id="modifySchoolModal" tabindex="-2" aria-labelledby="modifySchoolModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="modifySchoolModalLabel">Modificar Escuela</h4>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="modifySchoolForm">
                            <div class="mb-4">
                                <label for="modifyUser" class="form-label">Usuario</label>
                                <input type="text" class="form-control" id="modifyUser" required>
                            </div>
                            <div class="mb-4">
                                <label for="modifyPassword" class="form-label">Contraseña</label>
                                <input type="password" class="form-control" id="modifyPassword" required>
                            </div>
                            <div class="mb-4">
                                <label for="modifySchoolName" class="form-label">Nombre de la Escuela</label>
                                <input type="text" class="form-control" id="modifySchoolName" required>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <button type="button" class="btn btn-primary" id="confirmModifySchool">Guardar Cambios</button>
                    </div>
                </div>
            </div>
        </div>
    `;

    // Añadir el modal al DOM si no existe
    if (!document.getElementById('modifySchoolModal')) {
        document.body.insertAdjacentHTML('beforeend', modalHTML);
    }

    // Mostrar el modal
    const modal = new bootstrap.Modal(document.getElementById('modifySchoolModal'));
    modal.show();

    // Manejar el evento de confirmación de modificación
    document.getElementById('confirmModifySchool').addEventListener('click', function () {
        // Obtener el ID de la escuela del localStorage
        const idSchool = localStorage.getItem('userInfo');

        // Crear el objeto con los datos de la escuela
        const schoolData = {
            user: document.getElementById('modifyUser').value,
            password: document.getElementById('modifyPassword').value,
            schoolName: document.getElementById('modifySchoolName').value
        };

        // Llamada a la API para modificar la escuela
        fetch(`${BASE_URL}/upt=sch/${idSchool}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(schoolData)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al modificar la escuela');
            }
            return response.json();
        })
        .then(() => {
            alert('Escuela modificada correctamente');
            modal.hide();
            // Recargar la página para mostrar los cambios
            window.location.reload();
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al modificar la escuela');
        });
    });
});

// Función para eliminar la escuela
document.getElementById('delete_school_btn').addEventListener('click', function () {
    // Crear modal de confirmación
    const modalHTML = `
        <div class="modal fade" id="deleteSchoolModal" tabindex="-2" aria-labelledby="deleteSchoolModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="deleteSchoolModalLabel">Confirmar Eliminación</h4>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <p>¿Estás seguro de que deseas eliminar esta escuela? Esta acción no se puede deshacer.</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <button type="button" class="btn btn-danger" id="confirmDeleteSchool">Eliminar</button>
                    </div>
                </div>
            </div>
        </div>
    `;

    // Añadir el modal al DOM si no existe
    if (!document.getElementById('deleteSchoolModal')) {
        document.body.insertAdjacentHTML('beforeend', modalHTML);
    }

    // Mostrar el modal
    const modal = new bootstrap.Modal(document.getElementById('deleteSchoolModal'));
    modal.show();

    // Manejar el evento de confirmación de eliminación
    document.getElementById('confirmDeleteSchool').addEventListener('click', function () {
        // Obtener el ID de la escuela del localStorage
        const idSchool = localStorage.getItem('userInfo');

        // Llamada a la API para eliminar la escuela
        fetch(`${BASE_URL}/-sch/${idSchool}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al eliminar la escuela');
            }
            return response.json();
        })
        .then(() => {
            alert('Escuela eliminada correctamente');
            // Limpiar localStorage y redirigir al login
            localStorage.clear();
            window.location.href = '/JsWeb/login.html';
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al eliminar la escuela');
        });
    });
});

//
//------------------------------------------------------------------------------------------------------------------------------------------
    // Iniciar la carga de datos si existen las credenciales
    if (userId && userPassword) {
        loadSchoolName(userId, userPassword);
        loadStudents();
    }
});