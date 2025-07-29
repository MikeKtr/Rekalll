const fileInput = document.getElementById("fileInput");
const fileNameDisplay = document.getElementById("fileName");
const statusText = document.getElementById("status");

fileInput.addEventListener("change", () => {
  if (fileInput.files.length > 0) {
    fileNameDisplay.textContent = `📄 Wybrano: ${fileInput.files[0].name}`;
  } else {
    fileNameDisplay.textContent = "Nie wybrano pliku";
  }
});

document.getElementById("uploadForm").addEventListener("submit", function (e) {
  e.preventDefault();

  if (fileInput.files.length === 0) {
    statusText.textContent = "⚠️ Wybierz plik przed wysłaniem.";
    statusText.style.color = "#d32f2f";
    return;
  }

  const formData = new FormData();
  formData.append("plik", fileInput.files[0]);

  statusText.textContent = "⏳ Wysyłanie...";
  statusText.style.color = "#555";

  fetch("/upload", {
    method: "POST",
    body: formData
  })
    .then(response => {
      if (response.ok) {
        statusText.textContent = "✅ Plik został wysłany pomyślnie.";
        statusText.style.color = "#388e3c";
      } else {
        statusText.textContent = "❌ Wystąpił błąd podczas wysyłania.";
        statusText.style.color = "#d32f2f";
      }
    })
    .catch(error => {
      console.error("Błąd:", error);
      statusText.textContent = "❌ Błąd sieci lub serwera.";
      statusText.style.color = "#d32f2f";
    });
});
