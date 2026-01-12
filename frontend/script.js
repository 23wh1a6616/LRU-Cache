const API = "http://localhost:8080/cache";

function setCapacity() {
  const cap = document.getElementById("capacity").value;
  fetch(`${API}/capacity?capacity=${cap}`, { method: "POST" })
    .then(() => loadCache());
}

function put() {
  const key = document.getElementById("key").value;
  const value = document.getElementById("value").value;
  fetch(`${API}/put?key=${key}&value=${value}`, { method: "POST" })
    .then(() => loadCache());
}

function getValue() {
  const key = document.getElementById("getKey").value;
  fetch(`${API}/get?key=${key}`)
    .then(() => loadCache());
}

function loadCache() {
  fetch(`${API}/all`)
    .then(res => res.json())
    .then(data => {
      const div = document.getElementById("cache");
      div.innerHTML = "";

      data.forEach((item, index) => {
        const box = document.createElement("div");
        box.className = "cache-box";
        box.innerHTML = `
          ${item.key}<br>
          <small>${item.value}</small>
        `;
        div.appendChild(box);
      });
    });
}

