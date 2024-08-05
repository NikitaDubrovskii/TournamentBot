document.getElementById('dataForm').addEventListener('submit', function(event) {
    event.preventDefault();
    addRow();
});

function addRow() {
    const table = document.getElementById('dataTable').getElementsByTagName('tbody')[0];
    const newRow = table.insertRow();
    
    const rowCount = table.rows.length;

    const cell1 = newRow.insertCell(0);
    const cell2 = newRow.insertCell(1);
    const cell3 = newRow.insertCell(2);
    const cell4 = newRow.insertCell(3);

    cell1.textContent = rowCount;
    cell2.textContent = document.getElementById('input_name').value;
    cell3.textContent = document.getElementById('input_team').value;
    cell4.innerHTML = '<button onclick="editRow(this)">Изменить</button> <button onclick="deleteRow(this)">Удалить</button>';

    document.getElementById('input_name').value = '';
    document.getElementById('input_team').value = '';

}

function deleteRow(button) {
    const row = button.closest('tr');
    row.parentNode.removeChild(row);
    updateRowNumbers();
}

function editRow(button) {
    const row = button.closest('tr');
    const nameCell = row.cells[1];
    const teamCell = row.cells[2];

    const name = nameCell.textContent;
    const team = teamCell.textContent;

    nameCell.innerHTML = `<input type="text" class="edit_name" value="${name}">`;
    teamCell.innerHTML = `<input type="text" class="edit_team" value="${team}">`;
    
    button.textContent = 'Сохранить';
    button.onclick = function() {
        saveRow(this);
    };
}

function saveRow(button) {
    const row = button.closest('tr');
    const nameCell = row.cells[1];
    const teamCell = row.cells[2];

    const nameInput = nameCell.querySelector('input');
    const teamInput = teamCell.querySelector('input');

    nameCell.textContent = nameInput.value;
    teamCell.textContent = teamInput.value;
    
    button.textContent = 'Изменить';
    button.onclick = function() {
        editRow(this);
    };
}

function updateRowNumbers() {
    const rows = document.querySelectorAll('#dataTable tbody tr');
    rows.forEach((row, index) => {
        row.cells[0].textContent = index + 1;
    });
}

document.getElementById('save_table').addEventListener('click', function() {
    const nameInput = document.getElementById('input_name');
    const teamInput = document.getElementById('input_team');

    const table = document.getElementById('dataTable');

    table.style.backgroundColor = rgb(19, 30, 116);

    event.preventDefault();
});


document.getElementById("clear_table").addEventListener("click", function() {
    const table = document.getElementById("dataTable");

    const rows = table.getElementsByTagName("tr");
    for (let i = rows.length - 1; i > 0; i--) {
        table.deleteRow(i);
    }
});


const teamNameCell = document.getElementById("teamName");
const playerNameCell = document.getElementById("playerName");

const teamName = teamNameCell.textContent;
const playerName = parseInt(gamesPlayedCell.textContent);

console.log("Команда:", teamName);
console.log("Игры:", gamesPlayed);
