if(!sessionStorage.getItem("iduser")){// miramos si el numero esta en la session ya que si no esta es que no se ha logeado
    window.location = "http://127.0.0.1:5500"; // como no esta logeado pues lo mandamos al login
}