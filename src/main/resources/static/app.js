// Same origin as the Spring Boot app, so no CORS setup is needed.
// Running this file from your own PC instead? Use the full URL, e.g.
// "https://spring-boot-employee-crud-api.onrender.com/api/employees" (and enable CORS in Spring).
const API = "/api/employees";
const $ = (s) => document.querySelector(s);
let list = [], editing = null, delId = null;

const esc = (s) => String(s ?? "").replace(/[&<>"']/g, (c) => ({ "&": "&amp;", "<": "&lt;", ">": "&gt;", '"': "&quot;", "'": "&#39;" }[c]));

function toast(msg, bad) {
  const t = $("#toast");
  t.textContent = msg;
  t.className = "toast show" + (bad ? " bad" : "");
  clearTimeout(toast.t);
  toast.t = setTimeout(() => (t.className = "toast"), 3500);
}

function setStatus(text, kind) {
  $("#status").className = "status " + kind;
  $("#status span").textContent = text;
}

async function call(path, opts = {}) {
  const res = await fetch(API + path, { headers: { "Content-Type": "application/json" }, ...opts });
  const txt = await res.text();
  let data = null;
  try { data = txt ? JSON.parse(txt) : null; } catch { data = txt; }
  if (!res.ok) throw new Error((data && data.message) || (data && data.error) || "Request failed (" + res.status + ")");
  return data;
}

async function load() {
  const slow = setTimeout(() => setStatus("Waking up the server. The free plan can take up to a minute.", "wait"), 3000);
  try {
    list = await call("");
    setStatus("Connected to the live API", "ok");
    render();
  } catch (e) {
    setStatus("Can't reach the server. Refresh to try again.", "err");
    toast(e.message, true);
  } finally {
    clearTimeout(slow);
  }
}

function render() {
  const q = $("#q").value.trim().toLowerCase();
  const k = $("#sort").value;
  const rows = list
    .filter((e) => `${e.firstName} ${e.lastName} ${e.email}`.toLowerCase().includes(q))
    .sort((a, b) => (k === "id" ? a.id - b.id : String(a[k]).localeCompare(String(b[k]))));
  $("#count").textContent = `${rows.length} of ${list.length} employees`;
  $("#empty").hidden = rows.length > 0;
  $("#empty p").textContent = list.length ? "No employees match your search." : "No employees yet. Add your first one.";
  $("tbody").innerHTML = rows
    .map((e) => {
      const name = esc(e.firstName) + " " + esc(e.lastName);
      return `<tr><td><span class="av" aria-hidden="true">${esc((e.firstName || "?")[0])}${esc((e.lastName || "")[0] || "")}</span><b>${name}</b></td>
<td>${esc(e.email)}</td><td class="id">${e.id}</td>
<td class="row-actions"><button class="icon" data-edit="${e.id}" aria-label="Edit ${name}">Edit</button><button class="icon danger" data-del="${e.id}" aria-label="Delete ${name}">Delete</button></td></tr>`;
    })
    .join("");
}

function openForm(e) {
  editing = e || null;
  $("#formTitle").textContent = e ? "Edit employee" : "Add employee";
  $("#fn").value = e?.firstName ?? "";
  $("#ln").value = e?.lastName ?? "";
  $("#em").value = e?.email ?? "";
  $("#formDlg").showModal();
}

$("#addBtn").onclick = () => openForm();
$("#bulkBtn").onclick = () => { $("#bulkText").value = ""; $("#bulkDlg").showModal(); };
$("#q").oninput = render;
$("#sort").onchange = render;

$("tbody").addEventListener("click", (ev) => {
  const b = ev.target.closest("button");
  if (!b) return;
  if (b.dataset.edit) openForm(list.find((x) => x.id == b.dataset.edit));
  if (b.dataset.del) {
    const e = list.find((x) => x.id == b.dataset.del);
    delId = e.id;
    $("#delName").textContent = `${e.firstName} ${e.lastName} will be removed permanently.`;
    $("#delDlg").showModal();
  }
});

document.addEventListener("click", (ev) => {
  if (ev.target.closest("[data-close]")) ev.target.closest("dialog").close();
  else if (ev.target.tagName === "DIALOG") ev.target.close(); // click on backdrop
});

$("#form").addEventListener("submit", async (ev) => {
  ev.preventDefault();
  const body = { firstName: $("#fn").value.trim(), lastName: $("#ln").value.trim(), email: $("#em").value.trim() };
  ev.submitter.disabled = true;
  try {
    if (editing) {
      const u = await call("", { method: "PUT", body: JSON.stringify({ ...body, id: editing.id }) });
      list = list.map((x) => (x.id === u.id ? u : x));
      toast("Saved changes");
    } else {
      const c = await call("", { method: "POST", body: JSON.stringify(body) });
      list.push(c);
      toast("Added " + c.firstName);
    }
    $("#formDlg").close();
    render();
  } catch (e) {
    toast(e.message, true);
  } finally {
    ev.submitter.disabled = false;
  }
});

$("#bulkForm").addEventListener("submit", async (ev) => {
  ev.preventDefault();
  let arr;
  try {
    arr = JSON.parse($("#bulkText").value);
    if (!Array.isArray(arr) || !arr.length) throw 0;
  } catch {
    return toast("Paste a JSON array like [{...}, {...}]", true);
  }
  ev.submitter.disabled = true;
  try {
    const saved = await call("/bulk", { method: "POST", body: JSON.stringify(arr) });
    list.push(...saved);
    render();
    $("#bulkDlg").close();
    toast(`Added ${saved.length} employees`);
  } catch (e) {
    toast(e.message, true);
  } finally {
    ev.submitter.disabled = false;
  }
});

$("#delForm").addEventListener("submit", async (ev) => {
  ev.preventDefault();
  ev.submitter.disabled = true;
  try {
    await call("/" + delId, { method: "DELETE" });
    list = list.filter((x) => x.id !== delId);
    $("#delDlg").close();
    render();
    toast("Deleted employee");
  } catch (e) {
    toast(e.message, true);
  } finally {
    ev.submitter.disabled = false;
  }
});

load();
