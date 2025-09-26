document.addEventListener("DOMContentLoaded", () => {
  const tabla = document.querySelector("#table-clientes tbody");

  fetch("") //Direccion de la api
    .then(response => response.json())
    .then(data => {
      data.forEach(cliente => {
        const fila = document.createElement("tr");

        fila.innerHTML = `
          <td>${cliente.id}</td>
          <td>${cliente.nombre}</td>
          <td>${telefono.telefono}</td>
        `;

        tabla.appendChild(fila);
      });
    })
    .catch(error => console.error("Error al conectar con la API:", error));
});
