const hamburger = document.getElementById("hamburger");
const menu = document.getElementById("menu");

hamburger.addEventListener("click", () => {
  menu.classList.toggle("active");
});



document.addEventListener("DOMContentLoaded", function () {
  const container = document.querySelector(".services-scroll");

  document.querySelector(".scroll-btn.left").addEventListener("click", () => {
    container.scrollBy({ left: -300, behavior: "smooth" });
  });

  document.querySelector(".scroll-btn.right").addEventListener("click", () => {
    container.scrollBy({ left: 300, behavior: "smooth" });
  });
});