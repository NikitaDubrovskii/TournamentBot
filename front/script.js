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

document.getElementById('save_table').addEventListener('click', function() { 
    const tournamentName = document.getElementById('input_tournment').value; 
    const tournamentType = document.querySelector('#tournType select').value; 
    const participants = []; 
 
    const rows = document.querySelectorAll('#dataTable tbody tr'); 
    rows.forEach(row => { 
        const username = row.cells[1].innerText; 
        const teamName = row.cells[2].innerText; 
        participants.push({ username, teamName }); 
    }); 
 
    const data = { 
        name: tournamentName, 
        format: tournamentType.toLowerCase(), 
        participants: participants 
    }; 
 
    fetch('https://tournamentbotbackend.onrender.com/api/tournament/add', { 
        method: 'POST', 
        headers: { 
            'Content-Type': 'application/json' 
        }, 
        body: JSON.stringify(data) 
    }) 
    .then(response => response.text()) 
    .then(text => {
        try {
            let jsonResponse = JSON.parse(text); 
            console.log('Success:', jsonResponse); 

            updateGameTable(); // Добавим вызов функции GET запроса здесь

        } catch (error) {
            console.error('Ответ не является допустимым JSON:', text); 
        }
    })
    .catch((error) => { 
        console.error('Error:', error); 
    }); 
});


function updateGameTable() {
    fetch('https://tournamentbotbackend.onrender.com/api/standing/get')
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById('game_table').getElementsByTagName('tbody')[0];
            tableBody.innerHTML = ''; // Очистить существующие строки таблицы перед добавлением новых
            
            data.forEach((item, index) => {
                const row = tableBody.insertRow();
                
                const cell1 = row.insertCell(0);
                const cell2 = row.insertCell(1);
                const cell3 = row.insertCell(2);
                const cell4 = row.insertCell(3);
                const cell5 = row.insertCell(4);
                const cell6 = row.insertCell(5);
                const cell7 = row.insertCell(6);
                const cell8 = row.insertCell(7);

                cell1.textContent = index + 1;
                cell2.innerHTML = `
                    <div class="table_what">${item.teamName}</div>
                    <div class="table_who">@${item.participantName}</div>
                `;
                cell3.textContent = item.played || 0;
                cell4.textContent = item.wins || 0;
                cell5.textContent = item.draws || 0;
                cell6.textContent = item.losses || 0;
                cell7.textContent = item.goalsFor || 0;
                cell8.textContent = item.points || 0;
            });
        })
        .catch(error => {
            console.error('Error fetching standings:', error);
        });
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
    cell4.innerHTML = '<i class="fa-solid fa-trash" onclick="deleteRow(this)"></i>';

    document.getElementById('input_name').value = '';
    document.getElementById('input_team').value = '';

}

function deleteRow(icon) {
    const row = icon.closest('tr');
    row.parentNode.removeChild(row);
    updateRowNumbers();
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


    