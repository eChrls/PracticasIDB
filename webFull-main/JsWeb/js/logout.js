const logout = document.getElementById("LoginOut");

logout.addEventListener("click", (event) => {
    logoutf(event);
});

function logoutf(event){
    sessionStorage.removeItem("iduser");
    window.location = "http://127.0.0.1:5500";
}