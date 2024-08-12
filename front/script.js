function getQueryParam(name) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(name);
}

function displayGreeting(user) {
    const greetingElement = document.getElementById('greeting');
    
    if (user.name) {
        greetingElement.innerText = 'Привет, ' + user.name + '!';
    } else if (user.username) {
        greetingElement.innerText = 'Привет, ' + user.username + '!';
    } else {
        greetingElement.innerText = 'Привет!';
    }
}

window.onload = function() {
    const username = getQueryParam('username');

    if (username) {
        fetch(`https://tournamentbotbackend.onrender.com/api/user?username=${username}`)
            .then(response => response.json())
            .then(user => {
                displayGreeting(user);
            })
            .catch(error => {
                console.error('Error fetching user data:', error);
                displayGreeting({});
            });
    } else {
        displayGreeting({});
    }
}

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
    // cell4.innerHTML = '<button onclick="editRow(this)">Изменить</button> <button onclick="deleteRow(this)">Удалить</button>';
    cell4.innerHTML = '<i class="fa-solid fa-pen-to-square" onclick="editRow(this)"></i>  <i class="fa-solid fa-trash" onclick="deleteRow(this)"></i>';

    document.getElementById('input_name').value = '';
    document.getElementById('input_team').value = '';

}

function deleteRow(icon) {
    const row = icon.closest('tr');
    row.parentNode.removeChild(row);
    updateRowNumbers();
}

function editRow(icon) {
    const row = icon.closest('tr');
    const nameCell = row.cells[1];
    const teamCell = row.cells[2];

    const name = nameCell.textContent;
    const team = teamCell.textContent;

    nameCell.innerHTML = `<input type="text" class="edit_name" value="${name}">`;
    teamCell.innerHTML = `<input type="text" class="edit_team" value="${team}">`;

    const editIcon = row.querySelector('.fa-pen-to-square');
    const deleteIcon = row.querySelector('.fa-trash');

    editIcon.className = 'fa-solid fa-check';
    deleteIcon.className = 'fa-solid fa-x';

    editIcon.onclick = function() {
        saveRow(this);
    };
    deleteIcon.onclick = function() {
        cancelEdit(this);
    };
}

function saveRow(icon) {
    const row = icon.closest('tr');
    const nameCell = row.cells[1];
    const teamCell = row.cells[2];

    const nameInput = nameCell.querySelector('input');
    const teamInput = teamCell.querySelector('input');

    nameCell.textContent = nameInput.value;
    teamCell.textContent = teamInput.value;

    const editIcon = row.querySelector('.fa-check');
    const deleteIcon = row.querySelector('.fa-x');

    editIcon.className = 'fa-solid fa-pen-to-square';
    deleteIcon.className = 'fa-solid fa-trash';

    editIcon.onclick = function() {
        editRow(this);
    };
    deleteIcon.onclick = function() {
        deleteRow(this);
    };
}

function cancelEdit(icon) {
    const row = icon.closest('tr');
    const nameCell = row.cells[1];
    const teamCell = row.cells[2];

    const name = nameCell.querySelector('input').value;
    const team = teamCell.querySelector('input').value;

    nameCell.textContent = name;
    teamCell.textContent = team;

    const editIcon = row.querySelector('.fa-check');
    const deleteIcon = row.querySelector('.fa-x');

    editIcon.className = 'fa-solid fa-pen-to-square';
    deleteIcon.className = 'fa-solid fa-trash';

    editIcon.onclick = function() {
        editRow(this);
    };
    deleteIcon.onclick = function() {
        deleteRow(this);
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

fetch('https://tournamentbotbackend.onrender.com/api/user')
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        console.log('User data:', data);
    })
    .catch(error => {
        console.error('There has been a problem with your fetch operation:', error);
    });
