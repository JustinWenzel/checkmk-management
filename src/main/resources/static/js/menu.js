const closeMark = document.getElementById("close-sidebar");
const sidebar = document.querySelector(".components");

closeMark.addEventListener("click", function() {
    sidebar.classList.toggle("closed"); // Adds .closed property which is used in menu.css
})